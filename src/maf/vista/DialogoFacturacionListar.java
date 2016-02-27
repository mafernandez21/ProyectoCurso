/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maf.vista;

import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JFrame;
import maf.modelo.interfaces.IVista;

/**
 *
 * @author Wolf
 */
public class DialogoFacturacionListar extends Dialogo implements IVista {
    
    private PanelContenedorGrilla listadoFactura;
    private PanelBotones botonesDeVentana;
    private ActionListener controlador;

    public DialogoFacturacionListar(JFrame ventanaPrincipal, boolean modal, ActionListener controlador) {
        super(ventanaPrincipal, modal);
        this.listadoFactura = new PanelContenedorGrilla();
        this.botonesDeVentana = new PanelBotones();
        this.controlador = controlador;
    }

    public PanelContenedorGrilla getListadoFactura() {
        return listadoFactura;
    }

    public void setListadoFactura(PanelContenedorGrilla listadoFactura) {
        this.listadoFactura = listadoFactura;
    }

    public PanelBotones getBotonesDeVentana() {
        return botonesDeVentana;
    }

    public void setBotonesDeVentana(PanelBotones botonesDeVentana) {
        this.botonesDeVentana = botonesDeVentana;
    }

    @Override
    public void inicializar() {
        super.inicializar(); // aqui se llama a la funcion inicializar de Dialogo, donde se arma el borderlayout
        this.construirVista();
        
        this.listadoFactura.inicializar();
        this.getPanelCentral().add(this.listadoFactura);
        
        String sBotones3[] = {"Ver","Cancelar"};
        this.botonesDeVentana.inicializar(this.controlador, sBotones3, true);
        this.getPanelInferior().add(this.botonesDeVentana);
        
        this.pack();
    
    }

    @Override
    public void setListaDeAtributos(PanelContenedor[] paneles) {

    }

    @Override
    public PanelContenedor[] getListaDeAtributos() {
        return null;
    
    }

    @Override
    public void recuperarDatosDeGUI() {
    
    }

    @Override
    public void construirVista() {
    
    }

    @Override
    public void actualizarDatosDeVista(HashMap hmDatos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
