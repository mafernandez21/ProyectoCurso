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
import maf.vista.Dialogo;
import maf.vista.DialogoFacturacionAlta;
import maf.vista.DialogoGestion;
import maf.vista.DialogoGestionListar;
import maf.vista.VentanaAcerca;

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

        ControladorGestion gestorFactura = new ControladorGestion();
        gestorFactura.setModelo("maf.modelo.Factura");

        ControladorGestion gestorDetalleFactura = new ControladorGestion();
        gestorDetalleFactura.setModelo("maf.modelo.DetalleFactura");

        switch (sAccion) {
            case "SALIR":
                if (Core.preguntar("Desea Salir")) {
                    this.ventana.dispose();
                }
                break;
            case "ACERCA DE...":
            case "CONTENIDO":
                VentanaAcerca da = new VentanaAcerca(this.ventana, true);
                da.setTitle(sAccion);
                da.setVisible(true);
                break;

            case "NUEVA_FACTURA":
                gestorFactura.inicializar();
                gestorFactura.creaNuevoObjeto();
                gestorFactura.inicializarObjeto();

                gestorDetalleFactura.inicializar();
                gestorDetalleFactura.creaNuevoObjeto();
                gestorDetalleFactura.inicializarObjeto();

                ControladorListadoClientes gestorListadoClientes = new ControladorListadoClientes();
                gestorListadoClientes.setGestorMaestro(gestorFactura);

                ControladorDetalles gestorDetalles = new ControladorDetalles();
                gestorDetalles.setGestorMaestro(gestorFactura);
                gestorDetalles.setGestorSecundario(gestorDetalleFactura);

                Dialogo vNuevaFactura = new DialogoFacturacionAlta(this.ventana, true, gestorListadoClientes, gestorDetalles);
                gestorDetalleFactura.setVista(vNuevaFactura);
                gestorDetalleFactura.setVistaAux(vNuevaFactura);
                
                vNuevaFactura.setControlador(gestorFactura);
                vNuevaFactura.setTituloVentana(sAccion);
                
                gestorFactura.setVistaAux(vNuevaFactura);
                gestorFactura.setVista(vNuevaFactura);
                
                vNuevaFactura.inicializar();
                vNuevaFactura.centrar();
                vNuevaFactura.mostrar();
                vNuevaFactura.cerrar();
                break;

            case "LISTAR_FACTURAS":
                ControladorListadoFacturas gestorListadoFacturas = new ControladorListadoFacturas();
                gestorListadoFacturas.setGestorMaestro(gestorFactura);

                ControladorGestion c = new ControladorGestion();
                c.setModelo("maf.modelo.Factura");
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
                c.getVista().setControlador(gestorListadoFacturas);
                gestorListadoFacturas.setVista(c.getVista());

                //El controlado de Gestion presenta la vista y los datos
                c.getVista().inicializar();
                c.setMetaDatosVista();
                c.setDatosVista();
                c.getVista().construirVista();

                gestorListadoFacturas.setObjeto(c.getObjeto());
                gestorListadoFacturas.setGestorListado(c);

                ((DialogoGestionListar) vListado).actualizarTablaDatos(c.getGrupoDeDatos());
                c.getVista().centrar();
                c.getVista().mostrar();

//                gestorFactura.inicializar();
//                gestorFactura.creaNuevoObjeto();
//                gestorFactura.getObjeto().inicializar();
//
//                Dialogo vListaFactura = new DialogoFacturasListar(this.ventana, true, gestorFactura);
//                vListaFactura.setTituloVentana(sAccion);
//                gestorFactura.setVista(vListaFactura);
//                vListaFactura.inicializar();
//                vListaFactura.centrar();
//                vListaFactura.mostrar();
//                vListaFactura.cerrar();
                break;

            case "PERSONA":
            case "CLIENTE":
            case "PRODUCTO":
                ControladorGestion gestor = this.seleccionarControlador(sAccion);
                if (gestor != null) {//Genero una vista de Gestion
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
                    gestor.getVista().construirVista();
                    ((DialogoGestion) vGestor).actualizarTablaDatos(gestor.getGrupoDeDatos());
                    gestor.getVista().centrar();
                    gestor.getVista().mostrar();
                } else {
                    Core.mostrarMensajeError("El módulo " + sAccion + " no fué cargado");
                }
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
