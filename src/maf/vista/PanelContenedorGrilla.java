/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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

//</editor-fold>
    @Override
    public void inicializar() {
        BorderLayout layout = new BorderLayout();
        layout.setHgap(10);
        layout.setVgap(10);
        this.setLayout(layout);
        
        this.panelGrilla=new JScrollPane();
       
        this.tblGrilla=new JTable();
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
        
        this.add(this.panelGrilla,BorderLayout.SOUTH);
    }

    @Override
    public void setEtiqueta(String sEtiqueta) {
        
    }

    @Override
    public String getEtiqueta() {
        return null;
    }

    @Override
    public void setValor(Object oValor) {
        this.tblGrilla.setValueAt(oValor, this.tblGrilla.getSelectedRow(), this.tblGrilla.getSelectedColumn());
    }

    @Override
    public Object getValor() {
        return this.tblGrilla.getValueAt(this.tblGrilla.getSelectedRow(), this.tblGrilla.getSelectedColumn());
    }

    @Override
    public void bloquear(boolean bBloqueo) {
        this.tblGrilla.setEnabled(!bBloqueo);
    }
    
    

}
