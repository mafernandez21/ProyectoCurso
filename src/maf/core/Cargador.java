/*
 * Este proyecto pertenece a Martín Alejandro Fernández.
 * Cualquier edición del siguiente archivo, sin autorización
 * no esta permitida.
 */
package maf.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class Cargador {

    public enum MensajeResultados {
        ERROR_IO("No se pudo acceder al dispositivo de almacenamiento"),
        ERROR_HASHMAP("Datos corruptos"),
        ERROR_CARGA_MODULO("El módulo no se pudo cargar");

        private String sMensaje;

        private MensajeResultados(String sMensaje) {
            this.sMensaje = sMensaje;
        }

        public String verMensaje() {
            return this.sMensaje;
        }

    };

    HashMap hmConfiguracion = new HashMap();

    public HashMap getConfiguracion() {
        return hmConfiguracion;
    }

    public void cargarInfoSistema(String sArchivo) {
        Properties pCfgSistema = new Properties();
        InputStream isCfgSistema = null;
        HashMap hmInfo = new HashMap();

        try {
            //Cargamos el archivo de configuracion
            isCfgSistema = new FileInputStream(sArchivo);
            pCfgSistema.load(isCfgSistema);
            //Obtenemos los módulos que el sistema va a poder utilizar
            Core.mostrarMensajeConsola("Cargando info de sistema...");// + cfgSistema.getProperty("modulos"));
            hmInfo.put("NOMBRESISTEMA", pCfgSistema.getProperty("nombresistema"));
            hmInfo.put("VERSIONSISTEMA", pCfgSistema.getProperty("versionsistema"));
            hmInfo.put("AUTORSISTEMA", pCfgSistema.getProperty("autor"));
        } catch (IOException ex) {
            Core.mostrarMensajeError(Cargador.MensajeResultados.ERROR_IO.sMensaje + " (" + ex.getMessage() + ")");
        } finally {
            if (isCfgSistema != null) {
                try {
                    this.hmConfiguracion.put("INFOSISTEMA", hmInfo);
                    isCfgSistema.close();
                } catch (IOException e) {
                    Core.mostrarMensajeError(Cargador.MensajeResultados.ERROR_IO.sMensaje + "(" + e.getMessage() + ")");
                } catch (StringIndexOutOfBoundsException ex) {
                    Core.mostrarMensajeError(Cargador.MensajeResultados.ERROR_HASHMAP.sMensaje + "(" + ex.getMessage() + ")");
                }
            }
        }
    }

    public void cargarModulos(String sArchivo) {
        Properties pCfgSistema = new Properties();
        InputStream isCfgSistema = null;
        HashMap hmModulos = new HashMap();
        String sMod = "";

        try {
            //Cargamos el archivo de configuracion
            isCfgSistema = new FileInputStream(sArchivo);
            pCfgSistema.load(isCfgSistema);
            //Obtenemos los módulos que el sistema va a poder utilizar
            Core.mostrarMensajeConsola("Cargando módulos...");// + cfgSistema.getProperty("modulos"));
            String sModulos[] = pCfgSistema.getProperty("modulos").split(",");
            int n = 0;
            //Verificamos si los módulos existen
            for (String s : sModulos) {
                try {
                    Class c = Class.forName(s);
                    n++;
                    sMod = sMod.concat(s).concat(",");
                } catch (ClassNotFoundException ex) {
                    Core.mostrarMensajeError(Cargador.MensajeResultados.ERROR_CARGA_MODULO.sMensaje + "(" + s + ")");
                }
            }
            hmModulos.put("MODULOS", sMod.substring(0,sMod.length() - 1));
            Core.mostrarMensajeConsola(n + "/" + sModulos.length + " módulos cargados");
        } catch (IOException ex) {
            Core.mostrarMensajeError(Cargador.MensajeResultados.ERROR_IO.sMensaje + " (" + ex.getMessage() + ")");
        } finally {
            if (isCfgSistema != null) {
                try {
                    this.hmConfiguracion.put("MODULOS", hmModulos);
                    isCfgSistema.close();
                } catch (IOException e) {
                    Core.mostrarMensajeError(Cargador.MensajeResultados.ERROR_IO.sMensaje + "(" + e.getMessage() + ")");
                } catch (StringIndexOutOfBoundsException ex) {
                    Core.mostrarMensajeError(Cargador.MensajeResultados.ERROR_HASHMAP.sMensaje + "(" + ex.getMessage() + ")");
                }
            }
        }
    }

    public void cargarBD(String sArchivo) {
        Properties pCfgSistema = new Properties();
        InputStream isCfgSistema = null;
        HashMap hmDatosBD = new HashMap();
        String sMod = "";
        try {
            //Cargamos el archivo de configuracion
            isCfgSistema = new FileInputStream(sArchivo);
            pCfgSistema.load(isCfgSistema);
            Core.mostrarMensajeConsola("Configurando acceso a la Base de Datos");
            hmDatosBD.put("BDSERVIDOR", pCfgSistema.getProperty("servidor"));
            hmDatosBD.put("BDPUERTO", pCfgSistema.getProperty("puerto"));
            hmDatosBD.put("BDDRIVER", pCfgSistema.getProperty("driver"));
            hmDatosBD.put("BDPROTOCOLO", pCfgSistema.getProperty("protocolo"));
            hmDatosBD.put("BDBD", pCfgSistema.getProperty("bd"));
            hmDatosBD.put("BDUSUARIO", pCfgSistema.getProperty("usuario"));
            hmDatosBD.put("BDCLAVE", pCfgSistema.getProperty("clave"));
//            BaseDeDatos.inicializar(hmMetaDatosBD);
//            if(BaseDeDatos.getInstancia().pruebaDeConexion()){
//                Consola.enviar(BaseDeDatos.MENSAJE_CONEXION_TEST_OK);
//            }else
//            {
//                Consola.enviar(BaseDeDatos.MENSAJE_CONEXION_TEST_ERROR);
//            }
        } catch (IOException ex) {
            Core.mostrarMensajeError(Cargador.MensajeResultados.ERROR_IO.sMensaje + "(" + ex.getMessage() + ")");
        } finally {
            if (isCfgSistema != null) {
                try {
                    this.hmConfiguracion.put("BD", hmDatosBD);
                    isCfgSistema.close();
                } catch (IOException e) {
                    Core.mostrarMensajeError(Cargador.MensajeResultados.ERROR_IO.sMensaje + "(" + e.getMessage() + ")");
                }
            }
        }
    }

    public static void main(String[] args) {
        Cargador cargador = new Cargador();
        cargador.cargarModulos("configuracionPorDefecto.properties");
        cargador.cargarBD("configuracionPorDefecto.properties");
        HashMap md = cargador.getConfiguracion();
        Core.mostrarMensajeConsola("STOP");
    }

}
