/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class ControladorListados implements ActionListener {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    public static final int LISTADO_CLIENTES = 0;
    public static final int LISTADO_DETALLES = 1;
    public static final int LISTADO_FACTURAS = 2;
    public static final int LISTADO_GENERICO = 3;
    private IVista vista;
    private ObjetoBase objetoDeListado;
    private ControladorGestion gestorListado;
    private ControladorGestion gestorOriginal;
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
            case "SETCLIENTE":
                this.lanzarListado(ControladorListados.LISTADO_CLIENTES);
                break;
            case "SELECCIONAR":
                this.retornarObjeto(ControladorListados.LISTADO_CLIENTES);
                break;
            case "SETDETALLE":
                
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

    private void lanzarListado(int iTipoListado) {
        //Reservo la VISTA_GESTION
        ControladorGestion c = new ControladorGestion();
        switch (iTipoListado) {
            case ControladorListados.LISTADO_CLIENTES:
                c.setModelo("maf.modelo.Cliente");
                break;
            case ControladorListados.LISTADO_DETALLES:
                c.setModelo("maf.modelo.Producto");
                break;
            case ControladorListados.LISTADO_FACTURAS:
                c.setModelo("maf.modelo.Factura");
                break;
            default:
                break;
        }
        c.inicializar();
        c.creaNuevoObjeto();
        c.inicializarObjeto();
        Dialogo vListado = new DialogoGestionListar(null, true);
        //Enlazo la vista con el controlador CONTROLADOR <--> VISTA_GESTION
        //vListado.setControlador(c);
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
        c.getVista().ConstruirVista();

        this.setObjeto(c.getObjeto());
        this.setGestorListado(c);

        ((DialogoGestionListar) vListado).actualizarTablaDatos(c.getGrupoDeDatos());
        c.getVista().centrar();
        c.getVista().mostrar();
    }

    private void retornarObjeto(int iTipoListado) {

        this.getGestorListado().getVista().recuperarDatosDeGUI();
        this.getGestorListado().getDatosDeVista();
        this.getGestorListado().setDatosAObjeto();

        switch (iTipoListado) {
            case ControladorListados.LISTADO_CLIENTES:
                ((Factura) this.getGestorOriginal().getObjeto()).setCliente((Cliente) this.getGestorListado().getObjeto());
                break;
            case ControladorListados.LISTADO_DETALLES:
                //c.setModelo("maf.modelo.Producto");
                break;
            case ControladorListados.LISTADO_FACTURAS:
                //c.setModelo("maf.modelo.Factura");
                //this.setVistaAux((Dialogo) this.getVista());
                break;
            default:
                break;

        }

        this.getVista().cerrar();

    }

}
