/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

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

    private JComboBox cboValores;
    private JLabel lblEtiqueta;

    /**
     * Creates new form PanelAtributoBoton
     */
    public PanelContenedorCombo() {

    }

    @Override
    public void inicializar() {
        super.inicializar();
        this.lblEtiqueta = new JLabel();
        this.cboValores = new JComboBox();
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
        if(oValor instanceof Categoria){
            this.lblEtiqueta.setText("Categoria");
            this.cboValores.setModel(new DefaultComboBoxModel(Categoria.values()));
        }
    
        if(oValor instanceof TipoIVA){
            this.lblEtiqueta.setText("TipoIVA");
            this.cboValores.setModel(new DefaultComboBoxModel(TipoIVA.values()));
        }
        
        if(oValor instanceof TipoFactura){
            this.lblEtiqueta.setText("Tipo de Factura");
            this.cboValores.setModel(new DefaultComboBoxModel(TipoFactura.values()));
        }
        this.cboValores.setSelectedIndex(0);
    }

    @Override
    public Object getValor() {
        return this.cboValores.getSelectedItem();
    }
}
