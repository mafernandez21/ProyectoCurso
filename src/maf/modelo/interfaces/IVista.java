/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maf.modelo.interfaces;

import java.awt.event.ActionListener;
import java.util.HashMap;
import maf.vista.PanelContenedor;

/**
 *
 * @author Martin Alejandro
 */
public interface IVista {

    public void inicializar();

    public void setControlador(ActionListener controlador);

    public ActionListener getControlador();

    public void setMetaDatos(HashMap hmMetaDatos);

    public HashMap getMetaDatos();

    /**
     * Recupera los datos de la GUI y los envia al HashMap de datos que contiene la vista
     */
    public void recuperarDatosDeGUI();
    
    public void setDatos(HashMap hmDatos);

    public HashMap getDatos();

    public void construirVista();
    
    public void actualizarDatosDeVista(HashMap hmDatos);
    
    public void setTituloVentana(String sTitulo);

    public void setListaDeAtributos(PanelContenedor[] paneles);
    
    public PanelContenedor[] getListaDeAtributos();
    
    public void centrar();

    public void mostrar();

    public void ocultar();

    public void cerrar();
}
