/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maf.modelo.interfaces;

import java.awt.event.ActionListener;
import java.util.HashMap;

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

    public void recuperarDatosDeCampos();
    
    public void setDatos(HashMap hmDatos);

    public HashMap getDatos();

    public void ConstruirVista();
    
    public void setTituloVentana(String sTitulo);

    public void centrar();

    public void mostrar();

    public void ocultar();

    public void cerrar();
}
