/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import maf.modelo.interfaces.IVista;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class DialogoFacturasAlta extends Dialogo implements IVista {
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private JPanel Cliente;
    private PanelContenedorEtiqueta lblnombreCliente;
    private PanelBotones agregarCliente;
    private PanelBotones agregarDetalle;
    private PanelContenedorGrilla listadoDetalles;
    private PanelContenedorEtiqueta subTotal;
    private PanelContenedorEtiqueta total;
    private PanelBotones botonesDeVentana;
    private ActionListener controladorListado;
    //private ControladorGestion controlador;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public DialogoFacturasAlta(JFrame ventanaPrincipal, boolean modal, ActionListener controladorListado) {
        super(ventanaPrincipal, modal);
        this.Cliente = new JPanel();
        this.lblnombreCliente = new PanelContenedorEtiqueta();
        this.agregarCliente = new PanelBotones();
        this.agregarDetalle = new PanelBotones();
        this.listadoDetalles = new PanelContenedorGrilla();
        this.subTotal = new PanelContenedorEtiqueta();
        this.total = new PanelContenedorEtiqueta();
        this.botonesDeVentana = new PanelBotones();
        this.controladorListado=controladorListado;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public JPanel getCliente() {
        return Cliente;
    }

    public void setCliente(JPanel Cliente) {
        this.Cliente = Cliente;
    }

    public PanelContenedorEtiqueta getLblnombreCliente() {
        return lblnombreCliente;
    }

    public void setLblnombreCliente(PanelContenedorEtiqueta lblnombreCliente) {
        this.lblnombreCliente = lblnombreCliente;
    }

    public PanelBotones getAgregarCliente() {
        return agregarCliente;
    }

    public void setAgregarCliente(PanelBotones agregarCliente) {
        this.agregarCliente = agregarCliente;
    }

    public PanelBotones getAgregarDetalle() {
        return agregarDetalle;
    }

    public void setAgregarDetalle(PanelBotones agregarDetalle) {
        this.agregarDetalle = agregarDetalle;
    }

    public PanelContenedorGrilla getListadoDetalles() {
        return listadoDetalles;
    }

    public void setListadoDetalles(PanelContenedorGrilla listadoDetalles) {
        this.listadoDetalles = listadoDetalles;
    }

    public PanelContenedorEtiqueta getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(PanelContenedorEtiqueta subTotal) {
        this.subTotal = subTotal;
    }

    public PanelContenedorEtiqueta getTotal() {
        return total;
    }

    public void setTotal(PanelContenedorEtiqueta total) {
        this.total = total;
    }

    public PanelBotones getBotonesDeVentana() {
        return botonesDeVentana;
    }

    public void setBotonesDeVentana(PanelBotones botonesDeVentana) {
        this.botonesDeVentana = botonesDeVentana;
    }

    public ActionListener getControladorListado() {
        return this.controladorListado;
    }

    public void setControladorListado(ActionListener controladorListado) {
        this.controladorListado = controladorListado;
    }

    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Implementaciones">
    @Override
    public void inicializar() {
        super.inicializar();
        this.ConstruirVista();

        String sBotones1[] = new String[1];
        sBotones1[0] = "Seleccionar un Cliente";
        this.lblnombreCliente.inicializar();
        this.lblnombreCliente.setEtiqueta("Nombre del Cliente");
        this.getPanelSuperior().add(this.lblnombreCliente);
        this.agregarCliente.inicializar(this.controladorListado, sBotones1, true);
        this.agregarCliente.setComandoBoton("SETCliente", 0);

        this.getPanelSuperior().add(this.agregarCliente);

        this.getPanelCentral().setLayout(new GridLayout(4, 1));

        String sBotones2[] = new String[1];
        sBotones2[0] = "Agregar Detalle";
        this.agregarDetalle.inicializar(this.controladorListado, sBotones2, true);
        this.agregarDetalle.setComandoBoton("AgregarDetalle", 0);
        this.getPanelCentral().add(this.agregarDetalle);

        this.getPanelCentral().add(this.listadoDetalles);
        this.getPanelCentral().add(this.subTotal);
        this.getPanelCentral().add(this.total);

        String sBotones3[] = new String[2];
        sBotones3[0] = "Guardar";
        sBotones3[1] = "Cancelar";
        this.botonesDeVentana.inicializar(this.getControlador(), sBotones3, true);
        this.getPanelInferior().add(this.botonesDeVentana);
        this.pack();
    }

    @Override
    public void ConstruirVista() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void recuperarDatosDeGUI() {
        
    }
    //</editor-fold>
    
}
