/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */

package maf.vista;

import javax.swing.JButton;
import maf.controlador.ControladorListadoProductos;
import maf.modelo.DetalleFactura;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class PanelContenedorBotonControlado extends PanelContenedor{
    
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    JButton btnBotonControlado;
    ControladorListadoProductos controladorListado;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementaciones">
     @Override
    public void inicializar() {
        super.inicializar();
        this.btnBotonControlado=new JButton();
        this.add(this.btnBotonControlado);
    }
    
    @Override
    public void setEtiqueta(String sEtiqueta) {
        this.btnBotonControlado.setText(sEtiqueta);
    }

    @Override
    public String getEtiqueta() {
        return this.btnBotonControlado.getText();
    }

    @Override
    public void setValor(Object oValor) {
        this.controladorListado= ((ControladorListadoProductos)oValor);
        this.btnBotonControlado.addActionListener(this.controladorListado);
        this.btnBotonControlado.setActionCommand("SET"+this.getEtiqueta());
    }

    @Override
    public Object getValor() {
        return ((DetalleFactura)this.controladorListado.getGestorOriginal().getObjeto()).getProducto();
        //return null;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void bloquear(boolean bBloqueo) {
        this.btnBotonControlado.setEnabled(!bBloqueo);
    }
    //</editor-fold>
}
