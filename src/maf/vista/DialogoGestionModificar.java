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
public class DialogoGestionModificar extends DialogoGestionAlta{
    
    //<editor-fold defaultstate="collapsed" desc="Atributos">

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public DialogoGestionModificar(JFrame ventanaPrincipal, boolean modal) {
        super(ventanaPrincipal, modal);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    public void ConstruirVista() {
        super.ConstruirVista();
        this.panelDeBotones.setEtiquetaBoton("ACTUALIZAR", 0);
        this.panelDeBotones.setComandoBoton("ACTUALIZAR", 0);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método Main">
    public static void main(String[] args){
        //TODO-Aquí va la lógica para iniciar la clase
    }
    //</editor-fold>



}

