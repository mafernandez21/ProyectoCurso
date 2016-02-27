/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import maf.core.Core.Categoria;
import maf.core.Core.TipoFactura;
import maf.core.Core.TipoIVA;

/**
 *
 * @author Martin Alejandro
 */
public class PanelContenedorCombo extends PanelContenedor {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private JComboBox cboValores;
    private JLabel lblEtiqueta;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Contructores">
    /**
     * Creates new form PanelAtributoBoton
     */
    public PanelContenedorCombo() {

    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Implementaciones">
    @Override
    public void inicializar() {
        super.inicializar();
        this.lblEtiqueta = new JLabel();
        this.cboValores = new JComboBox();
        this.lblEtiqueta.setPreferredSize(new Dimension(100, 25));
        this.cboValores.setPreferredSize(new Dimension(250, 25));
        this.add(this.lblEtiqueta);
        this.add(this.cboValores);
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

        if (oValor instanceof Categoria) {
            this.lblEtiqueta.setText("CATEGORIA");
            this.cboValores.setModel(new DefaultComboBoxModel(Categoria.values()));
        }

        if (oValor instanceof TipoIVA) {
            this.lblEtiqueta.setText("TIPOIVA");
            this.cboValores.setModel(new DefaultComboBoxModel(TipoIVA.values()));
        }

        if (oValor instanceof TipoFactura) {
            this.lblEtiqueta.setText("TIPOFACTURA");
            this.cboValores.setModel(new DefaultComboBoxModel(TipoFactura.values()));
        }
        if (oValor != null) {
            this.cboValores.setSelectedItem(oValor);
        } else {
            this.cboValores.setSelectedIndex(0);
        }

    }

    @Override
    public Object getValor() {
        return this.cboValores.getSelectedItem();
    }

    @Override
    public void bloquear(boolean bBloqueo) {
        this.cboValores.setEnabled(!bBloqueo);
    }
    //</editor-fold>
}
