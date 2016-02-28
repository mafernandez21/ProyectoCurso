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
import maf.vista.Dialogo;
import maf.vista.DialogoGestionListar;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class ControladorListadoClientes implements ActionListener {

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
            case "SETCLIENTE":
                this.mostrarListadoDeClientes();
                break;
            case "SELECCIONAR":
                this.retornarObjeto();
//                String s = ((Cliente) ((Factura) this.getGestorMaestro().getObjeto()).getCliente()).getNombre();
//                s += ", " + ((Cliente) ((Factura) this.getGestorMaestro().getObjeto()).getCliente()).getApellido();
//                this.hmDatos.put("ETIQUETA1", s);
//                s = ((Cliente) ((Factura) this.getGestorMaestro().getObjeto()).getCliente()).getCategoria().toString();
//                this.hmDatos.put("ETIQUETA2", s);
                this.getGestorMaestro().getVista().actualizarDatosDeVista();
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

    private void mostrarListadoDeClientes() {
        //Reservo la VISTA_GESTION
        ControladorGestion c = new ControladorGestion();
        c.setModelo("maf.modelo.Cliente");
        c.inicializar();
        c.creaNuevoObjeto();
        c.inicializarObjeto();
        Dialogo vListado = new DialogoGestionListar(null, true);
        //Enlazo la vista con el controlador CONTROLADOR <--> VISTA_GESTION
        c.setVista(vListado);
        //Preparo la VISTA_GESTION y la muestro
        c.getMetaDatosDeObjeto();
        //Le envio los MetaDatos de acuerdo al modelo de Gestión
        //Construyo la VISTA_ALTA y la ,muestro
        c.getVista().setTituloVentana("Listado de " + c.getNombre());
        //indico que la ventana de listado ahora tambien la controla el "controlador de listados"
        c.getVista().setControlador(this);
        this.setVista(c.getVista());
        //El controlado de Gestion presenta la vista y los datos
        c.getVista().inicializar();
        c.setMetaDatosVista();
        c.setDatosVista();
        c.getVista().construirVista();

        this.setObjeto(c.getObjeto());
        this.setGestorListado(c);

        ((DialogoGestionListar) vListado).actualizarTablaDatos(c.getGrupoDeDatos());
        c.getVista().centrar();
        c.getVista().mostrar();
    }

    private void retornarObjeto() {

        this.getGestorListado().getVista().recuperarDatosDeGUI();
        this.getGestorListado().getDatosDeVista();
        this.getGestorListado().setDatosAObjeto();
        ((Factura) this.getGestorMaestro().getObjeto()).setCliente((Cliente) this.getGestorListado().getObjeto());

        this.getVista().cerrar();

    }

}
