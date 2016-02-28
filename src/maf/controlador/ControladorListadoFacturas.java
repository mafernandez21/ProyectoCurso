/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import maf.core.Core;
import maf.modelo.Cliente;
import maf.modelo.Factura;
import maf.modelo.ObjetoBase;
import maf.modelo.interfaces.IVista;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class ControladorListadoFacturas implements ActionListener {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private IVista vista;
    private ObjetoBase objetoDeListado;
    private ControladorGestion gestorMaestro;
    private ControladorGestion gestorListado;
    private HashMap hmDatos = new HashMap();
    private HashMap hmMetaDatos = new HashMap();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public IVista getVista() {
        return vista;
    }

    public void setVista(IVista vista) {
        this.vista = vista;
    }

    public ObjetoBase getObjeto() {
        return this.objetoDeListado;
    }

    public void setObjeto(ObjetoBase objetoDeListado) {
        this.objetoDeListado = objetoDeListado;
    }

    public ControladorGestion getGestorListado() {
        return gestorListado;
    }

    public void setGestorListado(ControladorGestion gestorListado) {
        this.gestorListado = gestorListado;
    }

    public ControladorGestion getGestorMaestro() {
        return gestorMaestro;
    }

    public void setGestorMaestro(ControladorGestion gestorOriginal) {
        this.gestorMaestro = gestorOriginal;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Implementaciones">
    @Override
    public void actionPerformed(ActionEvent e) {
        Core.mostrarMensaje("Controlador (" + this.getClass().getSimpleName() + ") de Gestión capturó " + e.getActionCommand());
        String sAccion = e.getActionCommand().toUpperCase();
        switch (sAccion) {
            case "SELECCIONAR":
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
    //</editor-fold>

    private void retornarObjeto() {

        this.getGestorListado().getVista().recuperarDatosDeGUI();
        this.getGestorListado().getDatosDeVista();
        this.getGestorListado().setDatosAObjeto();
        if (this.getGestorListado().getObjeto().getClass() != Factura.class) {
            ((Factura) this.getGestorMaestro().getObjeto()).setCliente((Cliente) this.getGestorListado().getObjeto());
        } else {
            Core.mostrarMensaje(((Factura)this.getGestorListado().getObjeto()).toString2());
        }
        //this.getVista().cerrar();
    }

}
