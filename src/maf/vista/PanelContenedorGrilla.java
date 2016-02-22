/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class PanelContenedorGrilla extends PanelContenedor {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    
    private JTable tblGrilla;
    private JScrollPane panelGrilla;
    private JLabel lblEtiqueta;
    private JPanel panelFiltro;
    private JTextField txtFiltro;
    private JButton btnFiltro;

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public JTable getTblGrilla() {
        return tblGrilla;
    }

    public void setTblGrilla(JTable tblGrilla) {
        this.tblGrilla = tblGrilla;
    }

    public JLabel getLblEtiqueta() {
        return lblEtiqueta;
    }

    public void setLblEtiqueta(JLabel lblEtiqueta) {
        this.lblEtiqueta = lblEtiqueta;
    }

    public JTextField getTxtFiltro() {
        return txtFiltro;
    }

    public void setTxtFiltro(JTextField txtFiltro) {
        this.txtFiltro = txtFiltro;
    }

    public JButton getBtnFiltro() {
        return btnFiltro;
    }

    public void setBtnFiltro(JButton btnFiltro) {
        this.btnFiltro = btnFiltro;
    }

//</editor-fold>
    @Override
    public void inicializar() {
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        
        this.panelGrilla=new JScrollPane();
        
        this.panelFiltro=new JPanel();
        this.panelFiltro.setBackground(Color.RED);
        
        this.lblEtiqueta = new JLabel();
        this.txtFiltro = new JTextField("Filtrar por...");
        this.txtFiltro.setSize(50, 140);
        this.txtFiltro.setPreferredSize(new Dimension(500, 20));
        this.btnFiltro=new JButton("Filtrar");
        
        this.tblGrilla=new JTable();
        this.tblGrilla.setSize(new Dimension(
                this.txtFiltro.getSize().width+this.lblEtiqueta.getSize().width+this.btnFiltro.getSize().width,
                this.btnFiltro.getSize().height*4));
        this.tblGrilla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Columna 1", "Columna 2", "Columna 3", "Columna 4"
            }
        ));
                
        this.panelGrilla.setViewportView(this.tblGrilla);
       
        
        this.panelFiltro.add(this.lblEtiqueta);
        this.panelFiltro.add(this.txtFiltro);
        this.panelFiltro.add(this.btnFiltro);
        
        this.add(this.panelFiltro,BorderLayout.CENTER);
        
        this.add(this.panelGrilla,BorderLayout.SOUTH);
    }

    @Override
    public void setEtiqueta(String sEtiqueta) {
        this.lblEtiqueta.setText(sEtiqueta);
    }

    @Override
    public String getEtiqueta() {
        return this.lblEtiqueta.getText();
    }

    @Override
    public void setValor(Object oValor) {
        this.tblGrilla.setValueAt(oValor, this.tblGrilla.getSelectedRow(), this.tblGrilla.getSelectedColumn());
    }

    @Override
    public Object getValor() {
        return this.tblGrilla.getValueAt(this.tblGrilla.getSelectedRow(), this.tblGrilla.getSelectedColumn());
    }

}
