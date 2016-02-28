/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import maf.controlador.ControladorGestion;
import maf.controlador.ControladorListadoProductos;
import maf.core.Core;
import maf.modelo.DetalleFactura;
import maf.modelo.Producto;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class DialogoFacturacionAltaDetalle extends DialogoGestion {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private PanelContenedor[] paneles;
    private ActionListener controladorListadoProductos;
    private JLabel lblDescripcionDeProducto;
    private HashMap hmMetaDatos;
    private HashMap hmDatos;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public DialogoFacturacionAltaDetalle(JFrame ventanaPrincipal, boolean modal, ActionListener controladorListadoProductos) {
        super(ventanaPrincipal, modal);
        this.setPanelGrilla(new PanelContenedorGrilla());
//        this.getPanelBotonesComandos = new PanelBotones();
        this.setPanelBotonesComandos(new PanelBotones());
        //this.controladorListadoProductos = controladorListadoProductos;
        this.lblDescripcionDeProducto = new JLabel("Descripción de producto...");
        //this.prepararTblTablaDatos();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Implementaciones">
    //</editor-fold>
    @Override
    public void inicializar() {

    }

    @Override
    public void recuperarDatosDeGUI() {
        try {
            for (PanelContenedor p : this.getListaDeAtributos()) {
                String key = p.getEtiqueta().toUpperCase();
                Object o = p.getValor();
                if (!String.valueOf(o).equals("")) {
                    this.getDatos().put(key, o);
                }
            }
            String key = "PRECIOUNITARIO";
            Object o = ((Producto) this.getDatos().get("PRODUCTO")).getPrecio();
            this.getDatos().put(key, o);

            key = "IMPORTEIVA";
            o = (((Producto) this.getDatos().get("PRODUCTO")).getTipoIVA().getValor()) * (((Producto) this.getDatos().get("PRODUCTO")).getPrecio()) * (Integer.parseInt(String.valueOf(this.getDatos().get("CANTIDAD"))));
            this.getDatos().put(key, o);

            key = "TOTALLINEA";
            double ti = (Double.parseDouble(String.valueOf(this.getDatos().get("IMPORTEIVA"))));
            o = ti + (((Producto) this.getDatos().get("PRODUCTO")).getPrecio()) * (Integer.parseInt(String.valueOf(this.getDatos().get("CANTIDAD"))));

            this.getDatos().put(key, o);
        } catch (NumberFormatException e) {
            Core.mostrarMensajeError(e.getMessage());
        }
    }

    @Override
    public void actualizarDatosDeVista() {
        ControladorGestion c=(ControladorGestion)this.getControlador();
        DetalleFactura df=(DetalleFactura)c.getObjeto();
        Producto p=df.getProducto();
        this.lblDescripcionDeProducto.setText(String.valueOf(p.getDescripcion()));
    }

    @Override
    public void setListaDeAtributos(PanelContenedor[] paneles) {
        this.paneles = paneles;
    }

    @Override
    public PanelContenedor[] getListaDeAtributos() {
        return this.paneles;
    }

    @Override
    public void construirVista() {
        this.setListaDeAtributos(new PanelContenedor[2]);

        this.getListaDeAtributos()[0] = new PanelContenedorTexto();
        this.getListaDeAtributos()[0].inicializar();
        this.getListaDeAtributos()[0].setEtiqueta("Cantidad");

        this.getListaDeAtributos()[1] = new PanelContenedorBotonControlado();
        this.getListaDeAtributos()[1].inicializar();
        this.getListaDeAtributos()[1].setEtiqueta("Producto");

        ControladorListadoProductos clp = new ControladorListadoProductos();
        clp.setGestorOriginal((ControladorGestion) this.getControlador());

        this.getListaDeAtributos()[1].setValor(clp);

        this.getListaDeAtributos()[1].add(this.lblDescripcionDeProducto);

        this.getPanelCentral().setLayout(new GridLayout(this.getListaDeAtributos().length, 1));

        for (PanelContenedor p : this.getListaDeAtributos()) {
            this.getPanelCentral().add(p);
        }

        String sBotones[] = {"Agregar", "Volver"};

        this.getPanelBotonesComandos().inicializar(this.getControlador(), sBotones, true);

        this.getPanelInferior().add(this.getPanelBotonesComandos());

        this.pack();
    }
    
}

    