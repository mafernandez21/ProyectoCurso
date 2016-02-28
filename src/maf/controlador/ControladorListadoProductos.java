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
import maf.modelo.DetalleFactura;
import maf.modelo.ObjetoBase;
import maf.modelo.Producto;
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
public class ControladorListadoProductos implements ActionListener {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private IVista vista;
    private ObjetoBase objetoDeListado;
    private ControladorGestion gestorListado;
    private ControladorGestion gestorOriginal;
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

    public ControladorGestion getGestorOriginal() {
        return gestorOriginal;
    }

    public void setGestorOriginal(ControladorGestion gestorOriginal) {
        this.gestorOriginal = gestorOriginal;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Implementaciones">
    @Override
    public void actionPerformed(ActionEvent e) {
        Core.mostrarMensaje("Controlador (" + this.getClass().getSimpleName() + ") de Gestión capturó " + e.getActionCommand());
        String sAccion = e.getActionCommand().toUpperCase();
        switch (sAccion) {
            case "SETPRODUCTO":
                this.mostrarListadoDeProductos();
                break;
            case "SELECCIONAR":
                this.retornarObjeto();
//                String s = ((Producto) ((DetalleFactura) this.getGestorOriginal().getObjeto()).getProducto()).getDescripcion();
//                s += ", Precio: $" + ((Producto) ((DetalleFactura) this.getGestorOriginal().getObjeto()).getProducto()).getPrecio();
//                s +=  ", IVA:" + ((Producto) ((DetalleFactura) this.getGestorOriginal().getObjeto()).getProducto()).getTipoIVA().toString();
//                s +=  ", Stock: " + String.valueOf((((Producto) ((DetalleFactura) this.getGestorOriginal().getObjeto()).getProducto()).getStock()));
//                this.hmDatos.put("ETIQUETA", s);
                this.getGestorOriginal().getVista().actualizarDatosDeVista();
                break;
            case "OK":
            case "CERRAR":
            case "VOLVER":
            case "CANCELAR":
                this.getVista().cerrar();
                break;
            default:
                Core.mostrarMensajeError("La función " + sAccion + " no está implementada por el controlador " + this.getClass().getSimpleName());
                break;
        }
    }
    //</editor-fold>

    private void mostrarListadoDeProductos() {
        //Reservo la VISTA_GESTION
        ControladorGestion c = new ControladorGestion();
        c.setModelo("maf.modelo.Producto");
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
        ((DetalleFactura) this.getGestorOriginal().getObjeto()).setProducto((Producto)this.getGestorListado().getObjeto());
        this.getVista().cerrar();
    }

}
