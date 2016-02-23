/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */

package maf.vista;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;




/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public abstract class PanelContenedor extends JPanel{
    
    //<editor-fold defaultstate="collapsed" desc="Metodos Abstractos">
    public abstract void setEtiqueta(String sEtiqueta);

    public abstract String getEtiqueta();
    
    public abstract void setValor(Object oValor);
    
    public abstract Object getValor();
    
    public abstract void bloquear(boolean bBloqueo);
    //</editor-fold>

    public void inicializar() {
        GridLayout layout = new GridLayout(1, 2);
        layout.setHgap(10);
        layout.setVgap(10);
        this.setLayout(layout);
        this.setBackground(new Color(0, 204, 255));
    }
}
