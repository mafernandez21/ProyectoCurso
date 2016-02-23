/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.controlador;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import maf.core.Core;
import maf.modelo.ObjetoBase;
import maf.modelo.interfaces.IControladorGestion;
import maf.modelo.interfaces.IVista;
import maf.modelo.interfaces.IVistaReflex;
import maf.vista.Dialogo;
import maf.vista.DialogoGestion;
import maf.vista.DialogoGestionAlta;
import maf.vista.DialogoGestionBaja;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class ControladorGestion implements IControladorGestion {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private Class cModelo;
    private ObjetoBase objetoGestionado;
    private IVista vistaActiva;
    private IVista vistaAux;
    private HashMap hmMetaDatos;
    private HashMap hmDatos;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public ControladorGestion() {
    }

    public ControladorGestion(Class cModelo, IVista vistaActiva) {
        this.cModelo = cModelo;
        this.vistaActiva = vistaActiva;
    }

    public ControladorGestion(ObjetoBase objetoGestionado, IVista vistaActiva) {
        this.objetoGestionado = objetoGestionado;
        this.vistaActiva = vistaActiva;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    private void setVistaAux(Dialogo dialogo) {
        this.vistaAux = dialogo;
    }

    private DialogoGestion getVistaAux() {
        return (DialogoGestion) this.vistaAux;
    }

    @Override
    public void inicializar() {

        this.hmMetaDatos = new HashMap();
        this.hmDatos = new HashMap();
    }

    public Class getcModelo() {
        return this.cModelo;
    }

    public void setcModelo(Class cModelo) {
        this.cModelo = cModelo;
    }

    public ObjetoBase getObjetoGestionado() {
        return this.objetoGestionado;
    }

    public void setObjetoGestionado(ObjetoBase objetoGestionado) {
        this.objetoGestionado = objetoGestionado;
    }

    public IVista getVista() {
        return this.vistaActiva;
    }

    public void setVista(IVista vista) {
        this.vistaActiva = vista;
    }

    @Override
    public HashMap getMetaDatos() {
        return this.hmMetaDatos;
    }

    public void setMetaDatos(HashMap hmMetaDatos) {
        this.hmMetaDatos = hmMetaDatos;
    }

    @Override
    public HashMap getDatos() {
        return this.hmDatos;
    }

    @Override
    public void setDatos(HashMap hmDatos) {
        this.getDatos().putAll(hmDatos);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    public String getNombre() {
        String sNombre = "";
        if (this.cModelo != null) {
            sNombre = this.cModelo.getSimpleName();
        }
        return sNombre;
    }

    @Override
    public boolean setModelo(String sModelo) {
        try {
            this.cModelo = Class.forName(sModelo);
        } catch (ExceptionInInitializerError | ClassNotFoundException ex) {
            Core.mostrarMensajeError(ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Class getModelo() {
        return this.cModelo;
    }

    @Override
    public boolean creaNuevoObjeto() {
        try {
            Object nuevoObjeto = cModelo.getConstructor().newInstance();
            this.objetoGestionado = (ObjetoBase) nuevoObjeto;
            return true;
        } catch (NullPointerException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            Core.mostrarMensajeError("No se puede crear el Objeto de la clase");
            return false;
        }

    }

    @Override
    public void setObjeto(ObjetoBase objeto) {
        if (objeto != null) {
            this.objetoGestionado = objeto;
        } else {
            Core.mostrarMensajeError("Objeto a gestionar definido incorrectamente");
        }
    }

    @Override
    public ObjetoBase getObjeto() {
        return this.objetoGestionado;
    }

    @Override
    public void inicializarObjeto() {
        if (this.objetoGestionado != null) {
            this.objetoGestionado.inicializar();
            this.getMetaDatos().putAll(this.getMetaDatosDeObjeto());
        } else {
            Core.mostrarMensajeError("No hay definido un objeto para gestionar");
        }
    }

    @Override
    public HashMap getMetaDatosDeObjeto() {
        this.getMetaDatos().putAll(this.getObjetoGestionado().getMetaDatos());
        return this.getObjetoGestionado().getMetaDatos();
    }

    @Override
    public HashMap getDatosDeObjeto() {
        this.getDatos().putAll(this.getObjetoGestionado().getDatos());
        return this.getObjetoGestionado().getDatos();
    }

    @Override
    public boolean setDatosAObjeto() {
        return this.getObjetoGestionado().setDatos(this.getDatos());
    }

    @Override
    public void inicializarVista() {
        this.getVista().inicializar();
    }

    @Override
    public void setMetaDatosVista() {
        this.getVista().getMetaDatos().putAll(this.getMetaDatos());
    }

    @Override
    public void getDatosDeVista() {
        this.getDatos().putAll(this.getVista().getDatos());
    }

    @Override
    public void setDatosVista() {
        this.getVista().setDatos(this.getDatos());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Método Main">
    public static void main(String[] args) {
        //TODO-Aquí va la lógica para iniciar la clase
    }
    //</editor-fold>

    @Override
    public void actionPerformed(ActionEvent e) {
        Core.mostrarMensaje("Controlador (" + this.getNombre() + ") de Gestión capturó " + e.getActionCommand());
        String sAccion = e.getActionCommand().toUpperCase();
        Dialogo vGestor;

        switch (sAccion) {
            case "ALTA":
                //Reservo la VISTA_GESTION
                this.setVistaAux((Dialogo) this.getVista());
                //Genero una VISTA_ALTA
                Dialogo vAlta = new DialogoGestionAlta(null, true);
                //Enlazo la vista con el controlador CONTROLADOR <--> VISTA_ALTA
                vAlta.setControlador(this);
                this.setVista(vAlta);
                //Preparo la VISTA_ALTA
                this.getVista().setTituloVentana("Alta de " + this.getNombre());
                //Como es un ALTA genero un nuevo objeto y lo inicializo
                this.creaNuevoObjeto();
                this.getObjetoGestionado().inicializar();
                this.getObjetoGestionado().prepararMetaDatos();
                this.getMetaDatosDeObjeto();
                //Inicializo la VISTA_ALTA
                this.inicializarVista();
                //Le envio los MetaDatos de acuerdo al modelo de Gestión
                this.setMetaDatosVista();
                //Construyo la VISTA_ALTA y la ,muestro
                this.getVista().ConstruirVista();
                this.getVista().mostrar();
                //Una vez que se termino el proceso de ALTA retorno la VISTA_GESTION al controlador
                this.setVista(this.vistaAux);
                break;
            case "GUARDAR":
                this.getVista().recuperarDatosDeModelo();
                this.getDatosDeVista();
                if (this.setDatosAObjeto()) {
                    this.getObjetoGestionado();
                    Core.mostrarMensaje("Guardar Objeto en la BD");
                    Core.mostrarMensaje(this.getObjetoGestionado());
                    this.getVista().cerrar();
                } else {
                    Core.mostrarMensaje("Error en los datos");
                }
                break;

            case "BAJA":
                //Reservo la VISTA_GESTION
                this.setVistaAux((Dialogo) this.getVista());
                //Genero una VISTA_ALTA
                Dialogo vBaja = new DialogoGestionBaja(null, true);
                //Enlazo la vista con el controlador CONTROLADOR <--> VISTA_ALTA
                vBaja.setControlador(this);
                this.setVista(vBaja);
                //Inicializo la VISTA_ALTA
                this.inicializarVista();
                //Preparo la VISTA_ALTA
                this.getVista().setTituloVentana("Baja de " + this.getNombre());
                //Como es un ALTA genero un nuevo objeto y lo inicializo
                this.creaNuevoObjeto();
                this.getObjetoGestionado().inicializar();
//##############################################################################
//##############################################################################
//##############################################################################
                //TOMAR DATOS DE LA GRILLA

                this.getVistaAux().recuperarDatosDeModelo();
//##############################################################################
                this.getVistaAux().setDatos(this.prepararCliente());
//##############################################################################
                this.getVistaAux().actualizarTablaDatos();

//                //TOMAR DATOS DE LA GRILLA
//                
                this.setDatos(this.getVistaAux().getDatos());
                //this.getDatosDeVista();
                this.setDatosAObjeto();

//##############################################################################
//##############################################################################
//##############################################################################
                this.getObjetoGestionado().prepararMetaDatos();
                this.getMetaDatosDeObjeto();
                //Le envio los MetaDatos de acuerdo al modelo de Gestión
                this.setMetaDatosVista();
                this.setDatosVista();
                //Construyo la VISTA_ALTA y la ,muestro
                this.getVista().ConstruirVista();
                this.getVista().mostrar();
                //Una vez que se termino el proceso de ALTA retorno la VISTA_GESTION al controlador
                this.setVista(this.vistaAux);
                break;
            case "BORRAR":
                this.getVista().recuperarDatosDeModelo();
                this.getDatosDeVista();
                if (Core.preguntar("Desea eliminar el registro")) {
                    Core.mostrarMensaje("SE ELIMINÓ\n" + this.getObjetoGestionado());
                    Core.mostrarMensaje("Objeto eliminado de la BD");
                }
                this.getVista().cerrar();
                break;

            case "AGREGAR CLIENTE":
                ((IVistaReflex) this.getVista()).getListaDeAtributos()[0].setEtiqueta("Cliente Agregado");
                break;

            case "VOLVER":
            case "CANCELAR":
                this.getVista().cerrar();
                break;
            default:
                Core.mostrarMensajeError("La función " + sAccion + " no está implementada");
                break;
        }

    }

    public HashMap prepararCliente() {
        HashMap hm = new HashMap();
        hm.put("ID", "009");
        hm.put("NOMBRE", "Ale");
        hm.put("APELLIDO", "Wolf");
        hm.put("DNI", "34132393");
        hm.put("DOMICILIO", "BS AS 671");
        hm.put("LOCALIDAD", "Tucuman");
        hm.put("CUIT", "20-03");
        hm.put("CATEGORIA", Core.Categoria.EXCENTO);
        return hm;
    }

    public HashMap prepararProductoParaVista() {
        HashMap hm = new HashMap();
        hm.put("ID", "12");
        hm.put("DESCRIPCION", "Desodorante");
        hm.put("PRECIO", 34.50);
        hm.put("TIPOIVA", Core.TipoIVA._21);
        hm.put("STOCK", 300);
        return hm;
    }
}
