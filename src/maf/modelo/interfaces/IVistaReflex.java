/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maf.modelo.interfaces;

import maf.vista.PanelContenedor;

/**
 *
 * @author Martin Alejandro
 */
public interface IVistaReflex extends IVista{

    void ConstruirVista();
    
    public void setListaDeAtributos(PanelContenedor[] paneles);
    
    public PanelContenedor[] getListaDeAtributos();

//    public void setContenedorDeAtributos(JPanel panel);
//    
//    public JPanel getContenedorDeAtributos();
//    
//    public void setPanelBotones (PanelBotones botones);
//    
//    public PanelBotones getPanelBotones();
//    
//    public void setContenedorDeBotones(JPanel panel);
//    
//    public JPanel getContenedorDeBotones();

}
