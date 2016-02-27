/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
public class DialogoFacturacionAlta extends Dialogo implements IVista {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private JPanel panelDatosCliente = new JPanel();
    private PanelContenedorEtiqueta lblFecha;
    private PanelContenedorEtiqueta lblNombreApellidoCliente;
    private PanelContenedorEtiqueta lblCategoriaCliente;
    private PanelBotones btnAgregarCliente;
    private PanelBotones agregarDetalle;
    private PanelContenedorGrilla listadoDetalles;
    private PanelContenedorEtiqueta subTotal;
    private PanelContenedorEtiqueta total;
    private PanelBotones botonesDeVentana;
    private ActionListener controladorListadoClientes;
    private ActionListener controladorDetalles;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public DialogoFacturacionAlta(JFrame ventanaPrincipal, boolean modal, ActionListener controladorListadoClientes, ActionListener controladorDetalles) {
        super(ventanaPrincipal, modal);
        this.lblFecha = new PanelContenedorEtiqueta();
        this.lblNombreApellidoCliente = new PanelContenedorEtiqueta();
        this.lblCategoriaCliente = new PanelContenedorEtiqueta();
        this.btnAgregarCliente = new PanelBotones();
        this.agregarDetalle = new PanelBotones();
        this.listadoDetalles = new PanelContenedorGrilla();
        this.subTotal = new PanelContenedorEtiqueta();
        this.total = new PanelContenedorEtiqueta();
        this.botonesDeVentana = new PanelBotones();
        this.controladorListadoClientes = controladorListadoClientes;
        this.controladorDetalles=controladorDetalles;
        this.panelDatosCliente = new JPanel();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public PanelContenedorEtiqueta getLblNombreApellidoCliente() {
        return lblNombreApellidoCliente;
    }

    public void setLblNombreApellidoCliente(PanelContenedorEtiqueta lblNombreApellidoCliente) {
        this.lblNombreApellidoCliente = lblNombreApellidoCliente;
    }

    public PanelBotones getBtnAgregarCliente() {
        return btnAgregarCliente;
    }

    public void setBtnAgregarCliente(PanelBotones btnAgregarCliente) {
        this.btnAgregarCliente = btnAgregarCliente;
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

    public ActionListener getControladorListadoClientes() {
        return this.controladorListadoClientes;
    }

    public void setControladorListadoClientes(ActionListener controladorListadoClientes) {
        this.controladorListadoClientes = controladorListadoClientes;
    }

    public ActionListener getControladorDetalles() {
        return controladorDetalles;
    }

    public void setControladorDetalles(ActionListener controladorDetalles) {
        this.controladorDetalles = controladorDetalles;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Implementaciones">
    @Override
    public void inicializar() {
        super.inicializar();
        this.construirVista();

        panelDatosCliente.setLayout(new GridLayout(3, 1));

        this.lblFecha.inicializar();
        SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy");
        
        this.lblFecha.setEtiqueta("Fecha:" + formato.format(new Date()));
        this.panelDatosCliente.add(this.lblFecha);

        this.lblNombreApellidoCliente.inicializar();
        this.lblNombreApellidoCliente.setEtiqueta("Nombre del Cliente");
        this.panelDatosCliente.add(this.lblNombreApellidoCliente);

        this.lblCategoriaCliente.inicializar();
        this.lblCategoriaCliente.setEtiqueta("Categoria del Cliente");
        this.panelDatosCliente.add(this.lblCategoriaCliente);

        this.getPanelSuperior().add(this.panelDatosCliente);
        
        
        String sBotones1[] = {"Seleccionar un Cliente"};
        this.btnAgregarCliente.inicializar(this.controladorListadoClientes, sBotones1, true);
        this.btnAgregarCliente.setComandoBoton("setCliente", 0);
        this.getPanelSuperior().add(this.btnAgregarCliente);

        this.getPanelCentral().setLayout(new GridLayout(4, 1));

        String sBotones2[] = {"Agregar Detalle"};
        this.agregarDetalle.inicializar(this.controladorDetalles, sBotones2, true);
        this.agregarDetalle.setComandoBoton("setDetalle", 0);
        this.getPanelCentral().add(this.agregarDetalle);

        this.getPanelCentral().add(this.listadoDetalles);
        this.getPanelCentral().add(this.subTotal);
        this.getPanelCentral().add(this.total);

        String sBotones3[] = {"Guardar","Cancelar"};
        this.botonesDeVentana.inicializar(this.getControlador(), sBotones3, true);
        this.getPanelInferior().add(this.botonesDeVentana);
        
        this.setSize(500, 400);
        this.pack();
    }

    @Override
    public void construirVista() {

    }

    @Override
    public void recuperarDatosDeGUI() {

    }

    @Override
    public void actualizarDatosDeVista(HashMap hmDatos) {
        this.lblNombreApellidoCliente.setEtiqueta("Nombre, Apellido: " + String.valueOf(hmDatos.get("ETIQUETA1")));
        this.lblCategoriaCliente.setEtiqueta("Categoria: " + String.valueOf(hmDatos.get("ETIQUETA2")));
    }

    @Override
    public void setListaDeAtributos(PanelContenedor[] paneles) {}

    @Override
    public PanelContenedor[] getListaDeAtributos() {return null;}
    //</editor-fold>
}
