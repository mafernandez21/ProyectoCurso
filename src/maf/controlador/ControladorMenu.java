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
import maf.vista.DialogoGestion;
import maf.vista.DialogoListaFacturas;

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
    private ControladorGestion gestores[];

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public ControladorMenu(JFrame ventana, ControladorGestion gestores[]) {
        this.ventana = ventana;
        this.gestores = gestores;
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
        //Core.mostrarMensaje("Controlador (" + this.getClass().getSimpleName() + ") de menú capturó " + e.getActionCommand());
        String sAccion = e.getActionCommand().toUpperCase();

        ControladorGestion gestor = this.seleccionarControlador(sAccion);
        

        switch (sAccion) {
            case "SALIR":
                if (Core.preguntar("Desea Salir")) {
                    this.ventana.dispose();
                }
                break;
            case "ACERCA DE...":
            case "CONTENIDO":
                Acerca da = new Acerca(this.ventana, true);
                da.setTitle(sAccion);
                da.setVisible(true);
                break;

            case "NUEVA_FACTURA":
                //ControladorGestion controladorFactura = new ControladorGestion();
                //controladorFactura.setModelo("maf.modelo.Factura");
                gestor.inicializar();
                gestor.creaNuevoObjeto();
                gestor.getObjeto().inicializar();

                Dialogo nuevaFactura = new DialogoAltaFactura(this.ventana, true, gestor);
                nuevaFactura.setTituloVentana(sAccion);
                gestor.setVista(nuevaFactura);
                nuevaFactura.inicializar();
                nuevaFactura.mostrar();
                break;

            case "LISTAR_FACTURAS":
                //ControladorGestion controladorListadoFactura = new ControladorGestion();
                //controladorListadoFactura.setModelo("maf.modelo.Factura");
                gestor.inicializar();
                gestor.creaNuevoObjeto();
                gestor.getObjeto().inicializar();

                Dialogo listaFactura = new DialogoListaFacturas(this.ventana, true, gestor);
                listaFactura.setTituloVentana(sAccion);
                gestor.setVista(listaFactura);
                listaFactura.inicializar();
                listaFactura.mostrar();
                break;

            case "PERSONA":
            case "CLIENTE":
            case "PRODUCTO":
                //ControladorGestion gestor = this.seleccionarControlador(sAccion);
                //Genero una vista de Gestion
                gestor.inicializar();
                Dialogo vGestor = new DialogoGestion(null, true);
                //Enlazo la vista con el controlador CONTROLADOR <--> VISTA_GESTION
                vGestor.setControlador(gestor);
                gestor.setVista(vGestor);
                //Preparo la VISTA_GESTION y la muestro
                gestor.getMetaDatosDeObjeto();
                //Inicializo la VISTA_ALTA
                //Le envio los MetaDatos de acuerdo al modelo de Gestión
                //Construyo la VISTA_ALTA y la ,muestro
                gestor.getVista().setTituloVentana(gestor.getNombre());
                gestor.getVista().inicializar();
                gestor.setMetaDatosVista();
                gestor.setDatosVista();
                gestor.getVista().ConstruirVista();
                ((DialogoGestion)vGestor).actualizarTablaDatos(gestor.getGrupoDeDatos());
                gestor.getVista().centrar();
                gestor.getVista().mostrar();
                break;

            default:
                Core.mostrarMensajeError("Función " + sAccion + " no implementada");
                break;
        }
    }

    private ControladorGestion seleccionarControlador(String sAccion) {
        for (ControladorGestion c : this.gestores) {
            if (sAccion.toUpperCase().contains(c.getNombre().toUpperCase())) {
                return c;
            }
        }
        return null;
    }
    
    
    
}
