/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFrame;
import maf.bdmem.BDEnMemoria;
import maf.controlador.ControladorGestion;
import maf.core.Core.Categoria;
import maf.core.Core.TipoIVA;
import maf.modelo.Cliente;
import maf.modelo.DetalleFactura;
import maf.modelo.Factura;
import maf.modelo.Persona;
import maf.modelo.Producto;
import maf.vista.VentanaPrincipal;

/**
 * Clase principal que inicia el Sistema de Factuaración
 *
 * @author Martín Alejandro Fernández
 * <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 * @version 1.0
 */
public class MaxPower {

    public String[] getModulos() {
        String archivoDeConfiguracion = "configuracionPorDefecto.properties";
        Cargador cargador = new Cargador();
        HashMap metaDatosModulos;

        cargador.cargarModulos(archivoDeConfiguracion);

        metaDatosModulos = (HashMap) cargador.getConfiguracion().get("MODULOS");

        String sModulos[] = String.valueOf(metaDatosModulos.get("MODULOS")).split(",");

        return sModulos;
    }

    public ControladorGestion[] setUpControladores(String[] sModulos) {
        int numModulos = sModulos.length;

        ControladorGestion[] controladores = new ControladorGestion[numModulos];

        for (int i = 0; i < controladores.length; i++) {
            controladores[i] = new ControladorGestion();
            controladores[i].inicializar();
            controladores[i].setModelo(sModulos[i]);
            controladores[i].getMetaDatos();
            controladores[i].creaNuevoObjeto();
            controladores[i].inicializarObjeto();
        }
        return controladores;
    }

    public void poblar(int pobladores) {
        for (int i = 0; i < pobladores; i++) {
            Core.mostrarMensajeConsola("Poblando la Base de Datos en Memoria..." + ((i*100)/pobladores) + "%");
            HashMap hmDatos = new HashMap();
            hmDatos.put("ID", i);
            hmDatos.put("NOMBRE", "Nombre_" + i);
            hmDatos.put("APELLIDO", "Apellido_" + i);
            hmDatos.put("DNI", "1234567" + i);
            hmDatos.put("DOMICILIO", "Domicilio_" + i);
            hmDatos.put("LOCALIDAD", "Localidad_" + i);
            Persona p = new Persona();
            p.inicializar();
            p.prepararMetaDatos();
            p.setDatos(hmDatos);

            hmDatos.put("CUIT", "20-1234567" + i + "-" + i);
            hmDatos.put("CATEGORIA", Categoria.CONSUMIDOR_FINAL);
            Cliente c = new Cliente();
            c.inicializar();
            c.prepararMetaDatos();
            c.setDatos(hmDatos);

            hmDatos.put("DESCRIPCION", "Producto_" + i);
            hmDatos.put("PRECIO", (35.0 * (i + 1)));
            hmDatos.put("TIPOIVA", TipoIVA._21);
            hmDatos.put("STOCK", (100 * (i + 1)));
            Producto prod = new Producto();
            prod.inicializar();
            prod.prepararMetaDatos();
            prod.setDatos(hmDatos);

            BDEnMemoria.conectar().insertar(p);
            BDEnMemoria.conectar().insertar(c);
            BDEnMemoria.conectar().insertar(prod);

            ArrayList<DetalleFactura> df = new ArrayList<DetalleFactura>();
            
            for (Object op : BDEnMemoria.conectar().seleccionar("producto")) {
                DetalleFactura d=new DetalleFactura();
                d.inicializar();
                d.prepararMetaDatos();
                d.setCantidad((i+1) * BDEnMemoria.conectar().getNumeroDeRegistros());
                d.setProducto((Producto) op);
                d.setPrecioUnitario();
                d.setImporteiva();
                d.setTotallinea();
                df.add(d);
            }
            
            
            hmDatos.put("ID", (i+12));
            hmDatos.put("CLIENTE", c);
            hmDatos.put("FECHA", new Date());
            hmDatos.put("NUMERO", 1000 + i);
            hmDatos.put("DETALLES", df);
            hmDatos.put("TIPO", Core.TipoFactura.C);

            Factura f = new Factura();
            f.inicializar();
            f.prepararMetaDatos();
            f.setDatos(hmDatos);
            f.setTotalneto();
            f.setTotaliva();
            f.setTotal();
            
            BDEnMemoria.conectar().insertar(f);

        }
        Core.mostrarMensajeConsola("Poblando la Base de Datos en Memoria...OK");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaxPower sistemaMaxPower = new MaxPower();

        sistemaMaxPower.poblar(100);

        String sModulos[] = sistemaMaxPower.getModulos();

        JFrame ventanaPrincipal = new VentanaPrincipal(sistemaMaxPower.setUpControladores(sModulos));

        ventanaPrincipal.setLocationRelativeTo(null);

        ventanaPrincipal.setVisible(true);
    }

}
