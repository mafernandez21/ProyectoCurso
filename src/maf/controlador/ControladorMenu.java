/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import maf.core.Core;
import maf.vista.Acerca;
import maf.vista.Dialogo;
import maf.vista.DialogoAltaFactura;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class ControladorMenu implements ActionListener {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private JFrame ventana;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public ControladorMenu(JFrame ventana) {
        this.ventana = ventana;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Método Main">
    public static void main(String[] args) {
        //TODO-Aquí va la lógica para iniciar la clase
    }
    //</editor-fold>

    @Override
    public void actionPerformed(ActionEvent e) {
        String sAccion = e.getActionCommand().toLowerCase();
        switch (sAccion) {
            case "salir":
                if (Core.preguntar("Desea Salir")) {
                    this.ventana.dispose();
                }
                break;
            case "acerca de...":
            case "contenido":
                Acerca da=new Acerca(this.ventana,true);
                da.setTitle(sAccion);
                da.setVisible(true);
                break;
            case "nueva_factura":
                ControladorGestion controladorFactura = new ControladorGestion();
                controladorFactura.setModelo("maf.modelo.Factura");
                controladorFactura.creaNuevoObjeto();
                controladorFactura.getObjetoGestionado().inicializar();
                
                Dialogo nuevaFactura = new DialogoAltaFactura(this.ventana,true,controladorFactura);
                nuevaFactura.setTituloVentana(sAccion);
                controladorFactura.setVista(nuevaFactura);
                nuevaFactura.inicializar();
                nuevaFactura.mostrar();
            case "listar_facturas":
            case "imprimir":
            default:
                Core.mostrarMensajeError("Función " + sAccion + " no implementada");
                break;
        }
    }

}
