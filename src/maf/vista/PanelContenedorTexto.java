/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import javax.swing.JTextField;

/**
 *
 * @author Martin Alejandro
 */
public class PanelContenedorTexto extends PanelContenedorEtiqueta {

    private JTextField txtTexto;

    /**
     * Creates new form PanelAtributoBoton
     */
    public PanelContenedorTexto() {
    }

    @Override
    public void inicializar() {
        super.inicializar();
        this.txtTexto = new JTextField();
        this.add(this.txtTexto);
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
        this.txtTexto.setText(String.valueOf(oValor));
    }

    @Override
    public Object getValor() {
        if (!this.txtTexto.getText().equals("")) {
            return this.txtTexto.getText();
        } else {
            return null;
        }
    }

    @Override
    public void bloquear(boolean bBloqueo) {
        super.bloquear(!bBloqueo);
        this.txtTexto.setEnabled(!bBloqueo);
    }
}
