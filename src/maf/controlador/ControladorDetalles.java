/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import maf.core.Core;
import maf.modelo.interfaces.IVista;
import maf.vista.Dialogo;
import maf.vista.DialogoFacturacionAltaDetalle;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class ControladorDetalles implements ActionListener {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private IVista vista;
    private ControladorGestion gestorAlternativo;
    private ControladorGestion gestorOriginal;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public IVista getVista() {
        return vista;
    }

    public void setVista(IVista vista) {
        this.vista = vista;
    }

    public ControladorGestion getGestorAlternativo() {
        return gestorAlternativo;
    }

    public void setGestorAlternativo(ControladorGestion gestorAlternativo) {
        this.gestorAlternativo = gestorAlternativo;
    }

    public ControladorGestion getGestorOriginal() {
        return gestorOriginal;
    }

    public void setGestorOriginal(ControladorGestion gestorOriginal) {
        this.gestorOriginal = gestorOriginal;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Implementaciones">
    //</editor-fold>
    @Override
    public void actionPerformed(ActionEvent e) {
        Core.mostrarMensaje("Controlador (" + this.getClass().getSimpleName() + ") de Gestión capturó " + e.getActionCommand());
        String sAccion = e.getActionCommand().toUpperCase();
        switch (sAccion) {
            case "SETDETALLE":
                Core.mostrarMensaje("MOSTRAR ALTA DE UN DETALLE");
                this.seleccionarAlgoritmoABM(sAccion);
                break;
            case "GUARDAR":
                break;
            case "OK":
            case "CERRAR":
            case "VOLVER":
            case "CANCELAR":
                this.getVista().cerrar();
                break;
            default:
                Core.mostrarMensajeError("La función " + sAccion + " no está implementada");
                break;
        }
    }

    private void seleccionarAlgoritmoABM(String sAccion) {

        ControladorGestion c = new ControladorGestion();
        c.setModelo("maf.modelo.DetalleFactura");
        c.inicializar();

        Dialogo vistaInput = new DialogoFacturacionAltaDetalle(null, true);
        vistaInput.setTituloVentana("Nuevo " + c.getNombre());
        //Enlazo la vista con el controlador
        vistaInput.setControlador(c);
        c.setVista(vistaInput);
        //Preparo la vista
        c.getVista().inicializar();
        c.creaNuevoObjeto();
        c.getObjeto().inicializar();
        c.getObjeto().prepararMetaDatos();
        c.getMetaDatosDeObjeto();
        //Le envio los MetaDatos de acuerdo al modelo de Gestión
        c.getDatosDeObjeto();
        c.setMetaDatosVista();
        //Construyo la VISTA_ALTA y la ,muestro
        c.getVista().construirVista();
        c.getVista().centrar();
        c.getVista().mostrar();
    }
}
//
//    private void guardarObjeto() {
//        this.getVista().recuperarDatosDeGUI();
////Recupera los datos que están en la vista
//        this.getDatosDeVista();
////Si los datos pueden ser almacenados en el objeto los almacena y luego envia el objeto a la BD
//        if (this.setDatosAObjeto() && this.agregarObjeto()) {
//            this.getVistaAux().actualizarTablaDatos(this.getGrupoDeDatos());
//            BDEnMemoria.conectar().insertar(this.getObjeto());
//            Core.mostrarMensaje("Objeto guardado en la BD");
//            this.getVista().cerrar();
//        } else {
//            Core.mostrarMensaje("Los datos son erroeneos o están incompletos, por favor revise y reintente");
//        }
//    }
//
//    private void eliminarObjeto(boolean advertencia) {
//        if (advertencia) {
//            if (Core.preguntar("Desea eliminar el registro")) {
//                this.removerObjeto(this.getObjeto());
//                this.getVistaAux().actualizarTablaDatos(this.getGrupoDeDatos());
//                BDEnMemoria.conectar().borrar(this.getObjeto());
//                Core.mostrarMensaje("Objeto eliminado de la BD");
//            }
//        } else {
//            this.removerObjeto(this.getObjeto());
//            this.getVistaAux().actualizarTablaDatos(this.getGrupoDeDatos());
//        }
//    }
//
