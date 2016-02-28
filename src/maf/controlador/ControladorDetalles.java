/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import maf.core.Core;
import maf.modelo.DetalleFactura;
import maf.modelo.Factura;
import maf.modelo.ObjetoBase;
import maf.modelo.Producto;
import maf.modelo.interfaces.IVista;
import maf.vista.Dialogo;
import maf.vista.DialogoFacturacionAlta;
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
    private ActionListener gestorProducto;
    private ControladorGestion gestorMaestro;
    private ControladorGestion gestorSecundario;
    private ObjetoBase objetoDeListado;

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

    public ActionListener getGestorProducto() {
        return this.gestorProducto;
    }

    public void setGestorProducto(ActionListener gestorProducto) {
        this.gestorProducto = gestorProducto;
    }

    public ControladorGestion getGestorMaestro() {
        return gestorMaestro;
    }

    public void setGestorMaestro(ControladorGestion gestorMaestro) {
        this.gestorMaestro = gestorMaestro;
    }

    public ControladorGestion getGestorSecundario() {
        return gestorSecundario;
    }

    public void setGestorSecundario(ControladorGestion gestorSecundario) {
        this.gestorSecundario = gestorSecundario;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Implementaciones">
    //</editor-fold>
    @Override
    public void actionPerformed(ActionEvent e) {
        //Core.mostrarMensaje("Controlador (" + this.getClass().getSimpleName() + ") de Gestión capturó " + e.getActionCommand());
        String sAccion = e.getActionCommand().toUpperCase();
        switch (sAccion) {
            case "NUEVODETALLE":
                //Core.mostrarMensaje("MOSTRAR ALTA DE UN DETALLE");
                this.seleccionarAlgoritmoABM(sAccion);

                ((Factura) this.getGestorMaestro().getObjeto()).getDetalles().clear();
                for (ObjetoBase ob : this.getGestorSecundario().getGrupoDeDatos()) {
                    ((Factura) this.getGestorMaestro().getObjeto()).getDetalles().add((DetalleFactura) ob);
                }
                
                //((DialogoFacturacionAltaDetalle)this.getGestorMaestro().getVista()).setMetaDatos(this.gestorSecundario.getMetaDatosDeObjeto());
                //-- TODO Actualizar la tabla de detalles con los nuevos detalles en la vista de nuevaFactura
                ((DialogoFacturacionAlta)this.getGestorMaestro().getVista()).actualizarTablaDatos(this.getGestorSecundario().getGrupoDeDatos());
                //Core.mostrarMensaje("Terminó NUEVO DETALLE");
                //Core.mostrarMensaje(((Factura) this.getGestorMaestro().getObjeto()));
                break;
            case "AGREGAR":
                this.retornarObjeto();
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
        //ControladorGestion c = new ControladorGestion();
        //c.setModelo("maf.modelo.DetalleFactura");
        //c.inicializar();
        //this.setGestorSecundario(c);
        Dialogo vistaInput = new DialogoFacturacionAltaDetalle(null, true, this.getGestorSecundario());

        vistaInput.setTituloVentana("Nuevo " + this.getGestorSecundario().getNombre());
        //Enlazo la vista con el controlador
        vistaInput.setControlador(this.getGestorSecundario());
        this.getGestorSecundario().setVista(vistaInput);
        //Preparo la vista
        this.getGestorSecundario().getVista().inicializar();
        this.getGestorSecundario().creaNuevoObjeto();
        this.getGestorSecundario().getObjeto().inicializar();
        this.getGestorSecundario().getObjeto().prepararMetaDatos();
        this.getGestorSecundario().getMetaDatosDeObjeto();
        //Le envio los MetaDatos de acuerdo al modelo de Gestión
        this.getGestorSecundario().getDatosDeObjeto();
        this.getGestorSecundario().setMetaDatosVista();
        //Construyo la VISTA_ALTA y la ,muestro

        this.getGestorSecundario().setVistaAux((Dialogo) this.getGestorSecundario().getVista());

        this.getGestorSecundario().getVista().construirVista();
        this.getGestorSecundario().getVista().centrar();
        this.getGestorSecundario().getVista().mostrar();
    }

    private void retornarObjeto() {

        this.getGestorSecundario().getVista().recuperarDatosDeGUI();
        this.getGestorSecundario().getDatosDeVista();
        this.getGestorSecundario().setDatosAObjeto();
        ((DetalleFactura) this.getGestorSecundario().getObjeto()).setProducto((Producto) this.getGestorSecundario().getObjeto());

        this.getVista().cerrar();

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
