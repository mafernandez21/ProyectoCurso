/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.controlador;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import maf.bdmem.BDEnMemoria;
import maf.core.Core;
import maf.modelo.ObjetoBase;
import maf.modelo.interfaces.IControladorGestion;
import maf.modelo.interfaces.IVista;
import maf.vista.Dialogo;
import maf.vista.DialogoGestion;
import maf.vista.DialogoGestionAlta;
import maf.vista.DialogoGestionBaja;
import maf.vista.DialogoGestionModificar;
import maf.vista.DialogoGestionVer;

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
    private ArrayList<ObjetoBase> grupoDeObjetos;
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
        this.grupoDeObjetos = new ArrayList<ObjetoBase>();
        for(Object o: BDEnMemoria.conectar().seleccionar(this.getNombre())){
            this.grupoDeObjetos.add((ObjetoBase)o);
        }
        this.hmMetaDatos = new HashMap();
        this.hmDatos = new HashMap();
    }

    public Class getcModelo() {
        return this.cModelo;
    }

    public void setcModelo(Class cModelo) {
        this.cModelo = cModelo;
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

    public ArrayList<ObjetoBase> getGrupoDeDatos() {
        return grupoDeObjetos;
    }

    public void setGrupoDeDatos(ArrayList<ObjetoBase> grupoDeDatos) {
        this.grupoDeObjetos = grupoDeDatos;
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
    public boolean agregarObjeto() {

        if (this.getGrupoDeDatos() == null) {
            this.setGrupoDeDatos(new ArrayList<ObjetoBase>());
        }

        for (ObjetoBase ob : this.getGrupoDeDatos()) {
            if (this.getObjeto().equals(ob)) {
                Core.mostrarMensajeError("ERROR interno - El objeto ya existe y no se agregó");
                return false;
            }
        }
        this.getGrupoDeDatos().add(this.getObjeto());
        return true;
    }

    @Override
    public boolean removerObjeto(ObjetoBase obj) {
        if (this.getGrupoDeDatos() != null && !this.getGrupoDeDatos().isEmpty()) {
            for (ObjetoBase ob : this.getGrupoDeDatos()) {
                if (ob.equals(obj)) {
                    this.getGrupoDeDatos().remove(ob);
                    return true;
                }
            }
        }
        Core.mostrarMensajeError("ERROR interno - El objeto no se eliminó");
        return false;
    }

    @Override
    public int getIndiceDeObjeto(ObjetoBase obj) {
        if (this.getGrupoDeDatos() != null) {
            for (int i = 0; i < this.getGrupoDeDatos().size(); i++) {
                if (obj.equals(this.getGrupoDeDatos().get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void setObjeto(ObjetoBase objeto
    ) {
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
        this.getMetaDatos().putAll(this.getObjeto().getMetaDatos());
        return this.getObjeto().getMetaDatos();
    }

    @Override
    public HashMap getDatosDeObjeto() {
        this.getDatos().putAll(this.getObjeto().getDatos());
        return this.getObjeto().getDatos();
    }

    @Override
    public boolean setDatosAObjeto() {
        return this.getObjeto().setDatos(this.getDatos());
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
        //Core.mostrarMensaje("Controlador (" + this.getNombre() + ") de Gestión capturó " + e.getActionCommand());
        String sAccion = e.getActionCommand().toUpperCase();
        switch (sAccion) {
            case "ALTA":
            case "BAJA":
            case "MODIFICAR":
            case "VER":
                this.seleccionarAlgoritmo(sAccion);
                break;
            case "GUARDAR":
                this.guardarObjeto();
                break;
            case "BORRAR":
                this.eliminarObjeto(true);
                this.getVista().cerrar();
                break;
            case "ACTUALIZAR":
                this.eliminarObjeto(false);
                this.guardarObjeto();
                break;
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

    private void seleccionarAlgoritmo(String sAccion) {
        //Reservo la VISTA_GESTION
        this.setVistaAux((Dialogo) this.getVista());
        //Genero una VISTA_ALTA
        Dialogo vistaInput;

        switch (sAccion) {
            case "ALTA":
                vistaInput = new DialogoGestionAlta(null, true);
                vistaInput.setTituloVentana("Alta de " + this.getNombre());
                //Enlazo la vista con el controlador
                vistaInput.setControlador(this);
                this.setVista(vistaInput);
                //Preparo la vista
                this.getVista().inicializar();
                this.creaNuevoObjeto();
                this.getObjeto().inicializar();
                this.getObjeto().prepararMetaDatos();
                break;
            case "BAJA":
                vistaInput = new DialogoGestionBaja(null, true);
                this.getVistaAux().recuperarDatosDeGUI();
                vistaInput.setTituloVentana("Baja " + this.getNombre());
                //Enlazo la vista con el controlador
                vistaInput.setControlador(this);
                this.setVista(vistaInput);
                //Preparo la vista
                this.getVista().inicializar();
                this.creaNuevoObjeto();
                this.getObjeto().inicializar();
                this.getObjeto().prepararMetaDatos();
                vistaInput.setDatos(this.getVistaAux().getDatos());
                this.getObjeto().setDatos(this.getVistaAux().getDatos());
                this.setObjeto(this.getGrupoDeDatos().get(this.getIndiceDeObjeto(this.getObjeto())));
                break;
            case "MODIFICAR":
                vistaInput = new DialogoGestionModificar(null, true);
                this.getVistaAux().recuperarDatosDeGUI();
                vistaInput.setTituloVentana("Modificar " + this.getNombre());
                //Enlazo la vista con el controlador
                vistaInput.setControlador(this);
                this.setVista(vistaInput);
                //Preparo la vista
                this.getVista().inicializar();
                this.creaNuevoObjeto();
                this.getObjeto().inicializar();
                this.getObjeto().prepararMetaDatos();
                vistaInput.setDatos(this.getVistaAux().getDatos());
                this.getObjeto().setDatos(this.getVistaAux().getDatos());
                this.setObjeto(this.getGrupoDeDatos().get(this.getIndiceDeObjeto(this.getObjeto())));

                break;
            case "VER":
                vistaInput = new DialogoGestionVer(null, true);
                this.getVistaAux().recuperarDatosDeGUI();
                vistaInput.setTituloVentana("Ver " + this.getNombre());
                //Enlazo la vista con el controlador
                vistaInput.setControlador(this);
                this.setVista(vistaInput);
                //Preparo la vista
                this.getVista().inicializar();
                this.creaNuevoObjeto();
                this.getObjeto().inicializar();
                this.getObjeto().prepararMetaDatos();
                vistaInput.setDatos(this.getVistaAux().getDatos());
                this.getObjeto().setDatos(this.getVistaAux().getDatos());
                this.setObjeto(this.getGrupoDeDatos().get(this.getIndiceDeObjeto(this.getObjeto())));
                break;

            default:
                break;
        }
        this.getMetaDatosDeObjeto();
        //Le envio los MetaDatos de acuerdo al modelo de Gestión
        this.getDatosDeObjeto();
        if (!sAccion.equals("ALTA")) {
            this.setDatosVista();
        }
        this.setMetaDatosVista();
        //Construyo la VISTA_ALTA y la ,muestro
        this.getVista().ConstruirVista();
        this.getVista().centrar();
        this.getVista().mostrar();
        //Una vez que se termino el proceso de ALTA retorno la VISTA_GESTION al controlador
        //Creo un nuevo objeto y lo inicializo
        this.creaNuevoObjeto();
        this.getObjeto().inicializar();
        this.setVista(this.getVistaAux());
    }
    
    private void guardarObjeto() {
        this.getVista().recuperarDatosDeGUI();
//Recupera los datos que están en la vista
        this.getDatosDeVista();
//Si los datos pueden ser almacenados en el objeto los almacena y luego envia el objeto a la BD
        if (this.setDatosAObjeto() && this.agregarObjeto()) {
            //Core.mostrarMensaje("Se esta por Guardar el Objeto en la BD\n" + this.getObjeto());
            this.getVistaAux().actualizarTablaDatos(this.getGrupoDeDatos());
            BDEnMemoria.conectar().insertar(this.getObjeto());
            this.getVista().cerrar();
        } else {
            Core.mostrarMensaje("Los datos son erroeneos o están incompletos, por favor revise y reintente");
        }
    }

    private void eliminarObjeto(boolean advertencia) {
        if (advertencia) {
            if (Core.preguntar("Desea eliminar el registro")) {
                this.removerObjeto(this.getObjeto());
                this.getVistaAux().actualizarTablaDatos(this.getGrupoDeDatos());
                BDEnMemoria.conectar().borrar(this.getObjeto());
                Core.mostrarMensaje("Objeto eliminado de la BD");
            }
        } else {
            this.removerObjeto(this.getObjeto());
            this.getVistaAux().actualizarTablaDatos(this.getGrupoDeDatos());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Codigo para descarte">
//    private void darDeAlta(boolean bModifica) {
//        //Reservo la VISTA_GESTION
//        this.setVistaAux((Dialogo) this.getVista());
//        //Genero una VISTA_ALTA
//        Dialogo vistaInput;
//
//
//        if (!bModifica) {
//            vistaInput = new DialogoGestionAlta(null, true);
//        } else {
//            vistaInput = new DialogoGestionModificar(null, true);
//        }
//        
//
////Enlazo la vista con el controlador
//        vistaInput.setControlador(this);
//        this.setVista(vistaInput);
//        //Preparo la vista
//        this.getVista().inicializar();
//        
//        
//        if (!bModifica) {
//            this.getVista().setTituloVentana("Alta de " + this.getNombre());
//            this.creaNuevoObjeto();
//            this.getObjeto().inicializar();
//
//        } else {
//
//            this.getVistaAux().recuperarDatosDeGUI();
//
//            this.getVista().setTituloVentana("Modificar " + this.getNombre());
//            this.getVista().setDatos(this.getVistaAux().getDatos());
//            this.getObjeto().setDatos(this.getVistaAux().getDatos());
//
//            this.setObjeto(this.getGrupoDeDatos().get(this.getIndiceDeObjeto(this.getObjeto())));
//
//        }
//
//        
//        
//        this.getObjeto().prepararMetaDatos();
//        this.getMetaDatosDeObjeto();
//        //Le envio los MetaDatos de acuerdo al modelo de Gestión
//        this.getDatosDeObjeto();
//
//        
//        
//        
//        if (bModifica) {
//            this.setDatosVista();
//        }
//
//        
//        
//        
//        this.setMetaDatosVista();
//        //Construyo la VISTA_ALTA y la ,muestro
//        this.getVista().ConstruirVista();
//        this.getVista().mostrar();
//        //Una vez que se termino el proceso de ALTA retorno la VISTA_GESTION al controlador
//        //Creo un nuevo objeto y lo inicializo
//        this.creaNuevoObjeto();
//        this.getObjeto().inicializar();
//        this.setVista(this.getVistaAux());
//    }
//    

    
//    private void darDeBaja(boolean bModifica) {
//        //Reservo la VISTA_GESTION
//        this.setVistaAux((Dialogo) this.getVista());
////Genero una VISTA_BAJA
//        Dialogo vBaja = new DialogoGestionBaja(null, true);
////Enlazo la vista con el controlador
//        vBaja.setControlador(this);
//        this.setVista(vBaja);
////Inicializo la vista
//        this.inicializarVista();
//        this.getVista().setTituloVentana("Baja de " + this.getNombre());
////Creo un nuevo objeto y lo inicializo
//        this.creaNuevoObjeto();
//        this.getObjeto().inicializar();
////##############################################################################
////##############################################################################
////##############################################################################
//        if (bModifica) {
//
////TOMAR DATOS DE LA GRILLA
//            this.getVistaAux().recuperarDatosDeGUI();
//            this.getVista().setDatos(this.getVistaAux().getDatos());
//            this.getDatosDeVista();
//            this.setDatosAObjeto();
//            this.setObjeto(this.getGrupoDeDatos().get(this.getIndiceDeObjeto(this.getObjeto())));
//
//            this.getObjeto().prepararMetaDatos();
//            this.getMetaDatosDeObjeto();
//            //Le envio los MetaDatos de acuerdo al modelo de Gestión
//            this.setDatosVista();
//            this.setMetaDatosVista();
//
//            //Construyo la vista y la muestro
//            this.getVista().ConstruirVista();
//            this.getVista().mostrar();
//        } else {
//            this.setObjeto(this.getGrupoDeDatos().get(this.getIndiceDeObjeto(this.getObjeto())));
//            this.eliminarObjeto(false);
//        }
//        //Una vez que se termino el proceso de ALTA retorno la VISTA_GESTION al controlador
//        //Creo un nuevo objeto y lo inicializo
//        this.creaNuevoObjeto();
//        this.getObjeto().inicializar();
//        this.setVista(this.getVistaAux());
//    }


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
//</editor-fold>
    
}
