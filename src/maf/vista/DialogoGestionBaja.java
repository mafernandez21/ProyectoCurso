/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */

package maf.vista;

import javax.swing.JFrame;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class DialogoGestionBaja extends DialogoGestionAlta{

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public DialogoGestionBaja(JFrame ventanaPrincipal, boolean modal) {
        super(ventanaPrincipal, modal);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementaciones">
    @Override
    public void construirVista() {
        super.construirVista();
        for(PanelContenedor pc: this.getListaDeAtributos()){
            pc.bloquear(true);
        }
        this.panelDeBotones.setEtiquetaBoton("BORRAR", 0);
        this.panelDeBotones.setComandoBoton("BORRAR", 0);
    }
    //</editor-fold>
}
