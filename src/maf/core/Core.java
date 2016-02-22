/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.core;

import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class Core {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    public enum Categoria {
        RESPONSABLE_INSCRIPTO(true, "Responsable Inscripto"), CONSUMIDOR_FINAL(false, "Consumidor Final"), EXCENTO(false, "Excento");
        private final boolean discrimina;
        private final String sEtiqueta;

        private Categoria(boolean discrimina, String sEtiqueta) {
            this.sEtiqueta = sEtiqueta;
            this.discrimina = discrimina;
        }

        public boolean discrimina() {
            return this.discrimina;
        }

        @Override
        public String toString() {
            return sEtiqueta;
        }

    }

    public enum TipoFactura {
        A("Tipo A"), B("Tipo B"), C("Tipo C");
        private final String sEtiqueta;

        private TipoFactura(String sEtiqueta) {
            this.sEtiqueta = sEtiqueta;
        }

        @Override
        public String toString() {
            return sEtiqueta;
        }
    };

    public enum TipoIVA {
        _21(0.21, "21 %"), _0(0.0, "0%");
        private final double valor;
        private final String sEtiqueta;

        private TipoIVA(double valor, String sEtiqueta) {
            this.valor = valor;
            this.sEtiqueta = sEtiqueta;
        }

        public double getValor() {
            return this.valor;
        }

        @Override
        public String toString() {
            return sEtiqueta;
        }
    }

    public static final String NOMBRE_SISTEMA=Core.infoSistema()[0];
    public static final String VERSION_SISTEMA=Core.infoSistema()[1];
    public static final String AUTOR_SISTEMA=Core.infoSistema()[2];
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public static String[] infoSistema() {
        String ret[]=new String[3];
        String archivoDeConfiguracion = "configuracionPorDefecto.properties";
        Cargador cargador = new Cargador();
        HashMap metaDatosSistema;

        cargador.cargarInfoSistema(archivoDeConfiguracion);

        metaDatosSistema = (HashMap) cargador.getConfiguracion().get("INFOSISTEMA");

        ret[0] = String.valueOf(metaDatosSistema.get("NOMBRESISTEMA"));
        ret[1] = String.valueOf(metaDatosSistema.get("VERSIONSISTEMA"));
        ret[2] = String.valueOf(metaDatosSistema.get("AUTORSISTEMA"));
        return ret;
    }

    public static void mostrarMensaje(Object obj) {
        try{
            String sMensaje=obj.toString();
        System.out.println(sMensaje);
        JOptionPane.showMessageDialog(null, sMensaje, Core.NOMBRE_SISTEMA, JOptionPane.INFORMATION_MESSAGE);
        }catch(NullPointerException e){
              String sMensaje="Objeto NULL";
        System.out.println(sMensaje);
        JOptionPane.showMessageDialog(null, sMensaje, Core.NOMBRE_SISTEMA, JOptionPane.INFORMATION_MESSAGE);
          
        }
    }
    
    public static void mostrarMensaje(String sMensaje) {
        System.out.println(sMensaje);
        JOptionPane.showMessageDialog(null, sMensaje, Core.NOMBRE_SISTEMA, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarMensajeError(String sCausa) {
        System.err.println(sCausa);
        JOptionPane.showMessageDialog(null, "Lamentablemente se produjo un error, la causa : " + sCausa + " ", Core.NOMBRE_SISTEMA, JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarMensajeConsola(String sMensaje) {
        System.out.println(sMensaje);
    }

    public static boolean preguntar(String sPregunta) {
        sPregunta = "¿" + sPregunta + "?";
        System.out.println(sPregunta);
        return (JOptionPane.showConfirmDialog(null, sPregunta, Core.NOMBRE_SISTEMA, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
    //</editor-fold>
}
