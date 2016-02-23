/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maf.vista;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import maf.modelo.interfaces.IVistaReflex;

/**
 *
 * @author Wolf
 */
public class DialogoListaFacturas extends Dialogo implements IVistaReflex {
    
    private PanelContenedorGrilla listadoFactura;
    private PanelBotones botonesDeVentana;
    private ActionListener controlador;

    public DialogoListaFacturas(JFrame ventanaPrincipal, boolean modal, ActionListener controlador) {
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
        this.ConstruirVista();
        
        this.listadoFactura.inicializar();
        this.getPanelCentral().add(this.listadoFactura);
        
        String sBotones3[] = new String[2];
        sBotones3[0] = "Ver";
        sBotones3[1] = "Cancelar";
        this.botonesDeVentana.inicializar(this.controlador, sBotones3, true);
        this.getPanelInferior().add(this.botonesDeVentana);
        
        this.pack();
    
    }
    
//    @Override
//    public void setUpIngreso() {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void setListaDeAtributos(PanelContenedor[] paneles) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PanelContenedor[] getListaDeAtributos() {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void recuperarDatosDeModelo() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ConstruirVista() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
