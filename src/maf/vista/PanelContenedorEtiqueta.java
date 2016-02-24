/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author Martin Alejandro
 */
public class PanelContenedorEtiqueta extends PanelContenedor {

    protected JLabel lblEtiqueta;

    /**
     * Creates new form PanelAtributoBoton
     */
    public PanelContenedorEtiqueta() {

    }

    @Override
    public void inicializar() {
        super.inicializar();
        this.lblEtiqueta = new JLabel();
        this.lblEtiqueta.setPreferredSize(new Dimension(100,25));
        this.add(this.lblEtiqueta);
    }

    @Override
    public void setEtiqueta(String sTexto) {
        this.lblEtiqueta.setText(sTexto);
    }

    @Override
    public String getEtiqueta() {
        return this.lblEtiqueta.getText();
    }

    @Override
    public void setValor(Object oValor) {
        this.lblEtiqueta.setText(String.valueOf(oValor));
    }

    @Override
    public Object getValor() {
        if (!this.lblEtiqueta.getText().equals("")) {
            return this.lblEtiqueta.getText();
        } else {
            return null;
        }
    }

    @Override
    public void bloquear(boolean bBloqueo) {
        this.lblEtiqueta.setEnabled(!bBloqueo);
    }
    
    
}
