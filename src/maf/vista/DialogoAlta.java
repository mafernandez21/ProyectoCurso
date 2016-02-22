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
import maf.core.Core.Categoria;
import maf.core.Core.TipoFactura;
import maf.core.Core.TipoIVA;
import maf.modelo.interfaces.IVistaReflex;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class DialogoAlta extends Dialogo implements IVistaReflex {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    PanelContenedor[] paneles;
    PanelContenedorBotones panelDeBotones;
    FormularioPrincipal ventanaPrincipal;
    ActionListener controlador;
    HashMap hmDatos;
//    HashMap hmMetaDatos;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public DialogoAlta(JFrame ventanaPrincipal, boolean modal, ActionListener controlador) {
        super(ventanaPrincipal, modal);
        this.controlador = controlador;
        this.panelDeBotones = new PanelContenedorBotones();
//        this.hmDatos=new HashMap();
//        this.hmMetaDatos=new HashMap();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    public void inicializar() {
        super.inicializar();
        this.setUpIngreso();

        this.getPanelCentral().setLayout(new GridLayout(this.getListaDeAtributos().length, 1));

        for (PanelContenedor p : this.getListaDeAtributos()) {
            this.getPanelCentral().add(p);
        }

        String botones[] = new String[2];
        botones[0] = "Guardar";
        botones[1] = "Volver";
        this.panelDeBotones.inicializar(this.controlador, botones, true);

        this.getPanelInferior().add(this.panelDeBotones);

        this.pack();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método Main">
    public static void main(String[] args) {
        //TODO-Aquí va la lógica para iniciar la clase
    }
    //</editor-fold>

    @Override
    public void setUpIngreso() {
        String sEtiqueta[] = String.valueOf(this.getMetaDatos().get("ATRIBUTOS")).split(",");
        String sAtributo[] = String.valueOf(this.getMetaDatos().get("TIPOATRIBUTOS")).split(",");
        this.setListaDeAtributos(new PanelContenedor[sAtributo.length]);

        for (int i = 0; i < sAtributo.length; i++) {
            switch (sAtributo[i]) {
                case "java.lang.String":
                case "int":
                case "double":
                    this.getListaDeAtributos()[i] = new PanelContenedorTexto();
                    this.getListaDeAtributos()[i].inicializar();
                    this.getListaDeAtributos()[i].setEtiqueta(sEtiqueta[i]);
                    this.getListaDeAtributos()[i].setValor("");
                    break;
                case "maf.core.Core$Categoria":
                    this.getListaDeAtributos()[i] = new PanelContenedorCombo();
                    this.getListaDeAtributos()[i].inicializar();
                    this.getListaDeAtributos()[i].setEtiqueta(sEtiqueta[i]);
                    this.getListaDeAtributos()[i].setValor(Categoria.CONSUMIDOR_FINAL);

                    break;
                case "maf.core.Core$TipoIVA":
                    this.getListaDeAtributos()[i] = new PanelContenedorCombo();
                    this.getListaDeAtributos()[i].inicializar();
                    this.getListaDeAtributos()[i].setEtiqueta(sEtiqueta[i]);
                    this.getListaDeAtributos()[i].setValor(TipoIVA._21);
                    break;
                case "maf.core.Core$TipoFactura":
                    this.getListaDeAtributos()[i] = new PanelContenedorCombo();
                    this.getListaDeAtributos()[i].inicializar();
                    this.getListaDeAtributos()[i].setEtiqueta(sEtiqueta[i]);
                    this.getListaDeAtributos()[i].setValor(TipoFactura.B);
                    break;
            }
        }
    }

    @Override
    public void setListaDeAtributos(PanelContenedor[] paneles) {
        this.paneles = paneles;
    }

    @Override
    public PanelContenedor[] getListaDeAtributos() {
        return this.paneles;
    }

    
    
    
//    @Override
//    public void setContenedorDeAtributos(JPanel panel) {
//        this.setPanelCentral(panel);
//    }
//
//    @Override
//    public JPanel getContenedorDeAtributos() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void setPanelBotones(PanelBotones botones) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public PanelBotones getPanelBotones() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void setContenedorDeBotones(JPanel panel) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public JPanel getContenedorDeBotones() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public HashMap getDatos() {
        
        for(PanelContenedor p:this.getListaDeAtributos()){
            String key=p.getEtiqueta().toUpperCase();
            Object o=p.getValor();
            super.getDatos().put(key, o);
        }
        
        
        
        
        
        
        
        
        return super.getDatos(); //To change body of generated methods, choose Tools | Templates.
    }
}
