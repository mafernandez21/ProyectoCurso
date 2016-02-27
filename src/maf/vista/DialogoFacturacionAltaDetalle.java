/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */

package maf.vista;

import java.util.HashMap;
import javax.swing.JFrame;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class DialogoFacturacionAltaDetalle extends Dialogo {
    
    //<editor-fold defaultstate="collapsed" desc="Atributos">

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public DialogoFacturacionAltaDetalle(JFrame ventanaPrincipal, boolean modal) {
        super(ventanaPrincipal, modal);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementaciones">

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método Main">
    public static void main(String[] args){
        //TODO-Aquí va la lógica para iniciar la clase
    }
    //</editor-fold>


    @Override
    public void recuperarDatosDeGUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarDatosDeVista(HashMap hmDatos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setListaDeAtributos(PanelContenedor[] paneles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PanelContenedor[] getListaDeAtributos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void construirVista() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
