/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maf.vista;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import maf.modelo.interfaces.IVistaReflex;

/**
 *
 * @author Martín Alejandro
 */
public class DialogoAltaFactura extends Dialogo implements IVistaReflex {

    private JPanel Cliente;
    private PanelContenedorEtiqueta lblnombreCliente;
    private PanelContenedorBotones agregarCliente;
    private PanelContenedorBotones agregarDetalle;
    private PanelContenedorGrilla listadoDetalles;
    private PanelContenedorEtiqueta subTotal;
    private PanelContenedorEtiqueta total;
    private PanelContenedorBotones botonesDeVentana;
    private ActionListener controlador;

    public DialogoAltaFactura(JFrame ventanaPrincipal, boolean modal, ActionListener controlador) {
        super(ventanaPrincipal, modal);
        this.Cliente = new JPanel();
     this.lblnombreCliente = new PanelContenedorEtiqueta();
     this.agregarCliente = new PanelContenedorBotones();
     this.agregarDetalle = new PanelContenedorBotones();
     this.listadoDetalles = new PanelContenedorGrilla();
     this.subTotal = new PanelContenedorEtiqueta();
     this.total = new PanelContenedorEtiqueta();
     this.botonesDeVentana = new PanelContenedorBotones();
     this.controlador = controlador;
    }

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

    public PanelContenedorBotones getAgregarCliente() {
        return agregarCliente;
    }

    public void setAgregarCliente(PanelContenedorBotones agregarCliente) {
        this.agregarCliente = agregarCliente;
    }

    public PanelContenedorBotones getAgregarDetalle() {
        return agregarDetalle;
    }

    public void setAgregarDetalle(PanelContenedorBotones agregarDetalle) {
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

    public PanelContenedorBotones getBotonesDeVentana() {
        return botonesDeVentana;
    }

    public void setBotonesDeVentana(PanelContenedorBotones botonesDeVentana) {
        this.botonesDeVentana = botonesDeVentana;
    }

    
    
    @Override
    public void inicializar() {
        super.inicializar();
        this.setUpIngreso(); //To change body of generated methods, choose Tools | Templates.

        String sBotones1[] = new String[1];
        sBotones1[0] = "Agregar Cliente";
        this.lblnombreCliente.inicializar();
        this.lblnombreCliente.setEtiqueta("Nombre del Cliente");
        this.getPanelSuperior().add(this.lblnombreCliente);
        this.agregarCliente.inicializar(this.controlador, sBotones1, true);
        this.getPanelSuperior().add(this.agregarCliente);
        
        this.getPanelCentral().setLayout(new GridLayout(4, 1));
        
        String sBotones2[] = new String[1];
        sBotones2[0] = "Agregar Detalle";
        this.agregarDetalle.inicializar(controlador, sBotones2, true);
        this.getPanelCentral().add(this.agregarDetalle);
        
        this.getPanelCentral().add(this.listadoDetalles);
        this.getPanelCentral().add(this.subTotal);
        this.getPanelCentral().add(this.total);
        
        String sBotones3[] = new String[2];
        sBotones3[0] = "Guardar";
        sBotones3[1] = "Cancelar";
        this.botonesDeVentana.inicializar(this.controlador, sBotones3, true);
        this.getPanelInferior().add(this.botonesDeVentana);
        
        this.pack();
    }

    @Override
    public void setUpIngreso() {
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setListaDeAtributos(PanelContenedor[] paneles) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PanelContenedor[] getListaDeAtributos() {
        PanelContenedor pc[]=new PanelContenedor[1];
        pc[0]=this.lblnombreCliente;
    return pc;
    }
}
