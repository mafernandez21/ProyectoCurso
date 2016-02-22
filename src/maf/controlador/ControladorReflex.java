/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class ControladorReflex implements ActionListener {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    Class modeloReflex;
//    IObjeto objetoReflex;
//    IVistaReflex vistaReflex;
    HashMap hmDatos; //CLAVES:
    HashMap hmMetaDatos; //CLAVES:"SUPERCLASE","CLASE","ATRIBUTOS","TIPOATRIBUTOS","CONTROLADORAUX"
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public ControladorReflex(String sNombreModelo) {
//        try {
//            //Creamos una Clase de acuerdo con el modelo por ejemplo: Cliente
//            this.modeloReflex = Class.forName(sNombreModelo);
//
//            //Equivalente a Cliente miObjeto=new Cliente();
//            Object unObjetoNuevo = this.modeloReflex.newInstance();
//
//            //Tomo las funciones de la interface IObjeto de miObjeto.
//            this.objetoReflex = (IObjeto) unObjetoNuevo;
//
//            //Inicializo el objeto del modelo
//            this.objetoReflex.inicializar();
//
//            //Inicializo los HashMap para transferencia de datos
//            this.hmDatos = new HashMap();
//            this.hmDatos = this.objetoReflex.getDatos();
//            this.hmMetaDatos = new HashMap();
//
//            //Inicializo la vista
//            this.vistaReflex = new DialogoIO(null, true);
//            this.vistaReflex.inicializar(this);
//            this.vistaReflex.setTituloVentana(this.getNombre());
//
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
//            Mensaje.mostrarMensaje(ex.getMessage());
//        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters, Setters and Others Basic">
    public void setModeloReflex(Class modeloReflex) {
        this.modeloReflex = modeloReflex;
    }

    public Class getModeloReflex() {
        return modeloReflex;
    }

//    public void setObjetoReflex(IObjeto objetoReflex) {
//        this.objetoReflex = objetoReflex;
//    }
//
//    public IObjeto getObjetoReflex() {
//        return objetoReflex;
//    }
//
//    public void setVistaReflex(IVistaReflex vistaReflex) {
//        this.vistaReflex = vistaReflex;
//    }
//
//    public IVistaReflex getVistaReflex() {
//        return vistaReflex;
//    }

    public void setMetaDatos(HashMap hmMetaDatos) {
        this.hmMetaDatos.putAll(hmMetaDatos);
    }

    public HashMap getMetaDatos() {
        return this.hmMetaDatos;
    }

    public void setDatos(HashMap hmMetaDatos) {
        this.hmDatos.putAll(hmMetaDatos);
    }

    public HashMap getDatos() {
        return this.hmDatos;
    }

    public void showMetaDatos() {
        System.out.println(this.hmMetaDatos);
    }

    public void showDatos() {
        System.out.println(this.hmDatos);
    }

    public String quitarComaFinal(String sCadena) {
        return sCadena.substring(0, sCadena.length() - 1);
    }

    public String formatoPaqueteClase(String sCadena) {
        String sFullName = sCadena.toLowerCase();
        String sPaquete = sFullName.substring(0, 7); //el paquete es "modelo."
        String sClaseModelo = sFullName.substring(7, 8).toUpperCase() + sFullName.substring(8, sFullName.length());
        return sPaquete + sClaseModelo;
    }

    //</editor-fold>
    
//    public void inicializarMetaDatos() {
//        boolean bTieneSuperClase = false;
//        String sNombreSuperClase = this.getModeloReflex().getSuperclass().getSimpleName();
//        String sNombreClase = this.getModeloReflex().getSimpleName();
//        String sNombreAtributos = "";
//        String sTipoAtributos = "";
//        Field atributos[];
//
//        //Si la clase en cuestion tiene una superclase (o extiende a otra clase)
//        //Extrae los atributos (con sus valores), de la superclase
//        if (this.getModeloReflex().getSuperclass() != Object.class) {
//            bTieneSuperClase = true;
//            atributos = this.getObjetoReflex().getClass().getSuperclass().getDeclaredFields();
//            for (int i = 0; i < atributos.length; i++) {
//                atributos[i].setAccessible(true);
//                try {
//                    sNombreAtributos += atributos[i].getName().toUpperCase() + ",";
//                    sTipoAtributos += atributos[i].getGenericType().toString().toUpperCase() + ",";
//                } catch (IllegalArgumentException ex) {
//                    Mensaje.mostrarMensaje(ex.getMessage());
//                }
//            }
//            sNombreAtributos = this.quitarComaFinal(sNombreAtributos);
//            sTipoAtributos = this.quitarComaFinal(sTipoAtributos);
//        }
//
//        //Extrae los atributos (con sus valores), de la clase en cuestión
//        atributos = this.getModeloReflex().getDeclaredFields();
//        for (int i = 0; i < atributos.length; i++) {
//            atributos[i].setAccessible(true);
//            try {
//                if (!bTieneSuperClase) {
//                    sNombreAtributos += atributos[i].getName().toUpperCase() + ",";
//                    sTipoAtributos += atributos[i].getGenericType().toString().toUpperCase() + ",";
//                } else {
//                    sNombreAtributos += "," + atributos[i].getName().toUpperCase();
//                    sTipoAtributos += "," + atributos[i].getGenericType().toString().toUpperCase();
//                }
//            } catch (IllegalArgumentException ex) {
//                Mensaje.mostrarMensaje(ex.getMessage());
//            }
//        }
//
//        if (!bTieneSuperClase) {
//            sNombreAtributos = this.quitarComaFinal(sNombreAtributos);
//            sTipoAtributos = this.quitarComaFinal(sTipoAtributos);
//        }
//
//        sTipoAtributos = sTipoAtributos.replaceAll("CLASS ", "");
//
//        String vectorTipoAtributos[] = sTipoAtributos.split(",");
//
//        for (int i = 0; i < vectorTipoAtributos.length; i++) {
//            switch (vectorTipoAtributos[i].toUpperCase()) {
//                case "INT":
//                case "FLOAT":
//                case "DOUBLE":
//                case "JAVA.LANG.STRING":
//                    break;
//                default:
//                    ControladorReflex nuevoControlador = new ControladorReflex(this.formatoPaqueteClase(vectorTipoAtributos[i]));
//                    nuevoControlador.inicializarMetaDatos();
//                    this.getMetaDatos().put("CONTROLADOR", nuevoControlador);
//                    break;
//            }
//        }
//        this.getMetaDatos().put("SUPERCLASE", sNombreSuperClase);
//        this.getMetaDatos().put("CLASE", sNombreClase);
//        this.getMetaDatos().put("ATRIBUTOS", sNombreAtributos);
//        this.getMetaDatos().put("TIPOATRIBUTOS", sTipoAtributos);
//        this.getMetaDatos().put("BOTONES", "Aceptar,Cancelar");
//    }
//
//    public void inicializarVista() {
//        //Genero los campos para los atributos
//        String sEtiqueta[] = String.valueOf(this.getMetaDatos().get("ATRIBUTOS")).split(",");
//        String sTipo[] = String.valueOf(this.getMetaDatos().get("TIPOATRIBUTOS")).split(",");
//
//        if (sEtiqueta != null && sTipo != null) {
//            //Inicializo el panel de los atributos
//            this.getVistaReflex().setListaDeAtributos(new IAtributo[sEtiqueta.length]);
//
//            for (int i = 0; i < sEtiqueta.length; i++) {
//                switch (sTipo[i]) {
//                    case "INT":
//                    case "FLOAT":
//                    case "DOUBLE":
//                    case "JAVA.LANG.STRING":
//                        this.getVistaReflex().getListaDeAtributos()[i] = new PanelAtributoEtiquetaTexto();
//                        ((PanelAtributoEtiquetaTexto) this.getVistaReflex().getListaDeAtributos()[i]).inicializar();
//                        ((PanelAtributoEtiquetaTexto) this.getVistaReflex().getListaDeAtributos()[i]).setEtiqueta(sEtiqueta[i]);
//                        ((PanelAtributoEtiquetaTexto) this.getVistaReflex().getListaDeAtributos()[i]).setTexto("");
//                        break;
//                    default:
//                        this.getVistaReflex().getListaDeAtributos()[i] = new PanelAtributoBoton();
//                        ((PanelAtributoBoton) this.getVistaReflex().getListaDeAtributos()[i]).inicializar();
//
//                        IControladorReflex c = ((IControladorReflex) this.getMetaDatos().get("CONTROLADOR"));
//                        c.inicializarVista();
//                        ((PanelAtributoBoton) this.getVistaReflex().getListaDeAtributos()[i]).setControlador(c);
//                        ((PanelAtributoBoton) this.getVistaReflex().getListaDeAtributos()[i]).setEtiqueta(sEtiqueta[i]);
//                        break;
//                }
//            }
//
//            this.getVistaReflex().getContenedorDeAtributos().setLayout(new GridLayout(sEtiqueta.length, 1));
//
//            this.getVistaReflex().getContenedorDeAtributos().removeAll();
//
//            for (IAtributo p : this.getVistaReflex().getListaDeAtributos()) {
//                this.getVistaReflex().getContenedorDeAtributos().add((Component) p);
//            }
//
//            //Los PanelBotones
//            sEtiqueta = String.valueOf(this.getMetaDatos().get("BOTONES")).split(",");
//            this.getVistaReflex().setPanelBotones(new PanelBotones());
//
//            this.getVistaReflex().getPanelBotones().setBotones(sEtiqueta);
//
//            this.getVistaReflex().getPanelBotones().setControlador(this);
//
//            this.getVistaReflex().getPanelBotones().inicializar();
//
//            this.getVistaReflex().getContenedorDeBotones().setLayout(new GridLayout(1, 1));
//
//            this.getVistaReflex().getContenedorDeBotones().removeAll();
//
//            this.getVistaReflex().getContenedorDeBotones().add(this.getVistaReflex().getPanelBotones());
//        } else {
//            Mensaje.mostrarMensaje("Estructura de Datos mal formada");
//        }
//        this.getVistaReflex().inicializar(this);
//    }
//
//    /**
//     * falta implementar la clave BOTONES del hashmap MetaDatos, que se envia a
//     * la vista, dependiendo de los botones requeridos por la ventana
//     */
//    @Override
//    public void setDatosVista() {
//        HashMap hmDatosDelModelo = this.objetoReflex.getDatos();
//        String sEtiqueta[] = String.valueOf(this.getMetaDatos().get("ATRIBUTOS")).split(",");
//        String sTipo[] = String.valueOf(this.getMetaDatos().get("TIPOATRIBUTOS")).split(",");
//
//        if (sEtiqueta != null && sTipo != null && hmDatosDelModelo != null) {
//            //Inicializo el panel de los atributos
//            this.getVistaReflex().setListaDeAtributos(new IAtributo[sEtiqueta.length]);
//
//            for (int i = 0; i < sEtiqueta.length; i++) {
//                switch (sTipo[i]) {
//                    case "INT":
//                    case "FLOAT":
//                    case "DOUBLE":
//                    case "JAVA.LANG.STRING":
//                        this.getVistaReflex().getListaDeAtributos()[i] = new PanelAtributoEtiquetaTexto();
//                        ((PanelAtributoEtiquetaTexto) this.getVistaReflex().getListaDeAtributos()[i]).inicializar();
//                        ((PanelAtributoEtiquetaTexto) this.getVistaReflex().getListaDeAtributos()[i]).setEtiqueta(sEtiqueta[i]);
//                        ((PanelAtributoEtiquetaTexto) this.getVistaReflex().getListaDeAtributos()[i]).setTexto(String.valueOf(hmDatosDelModelo.get(sEtiqueta[i])));
//                        break;
//                    default:
//                        this.getVistaReflex().getListaDeAtributos()[i] = new PanelAtributoBoton();
//                        ((PanelAtributoBoton) this.getVistaReflex().getListaDeAtributos()[i]).inicializar();
//
//                        ControladorReflex nuevoControlador = ((ControladorReflex) this.getMetaDatos().get("CONTROLADOR"));
//
//                        String claveHashMap = nuevoControlador.getObjetoReflex().getClass().getSimpleName().toUpperCase();
//                        IObjeto modeloTemporal = ((IObjeto) this.getDatos().get(claveHashMap));
//                        nuevoControlador.setDatos(modeloTemporal.getDatos());
//                        nuevoControlador.setDatosModelo();
//
//                        nuevoControlador.setDatosVista();
//                        ((PanelAtributoBoton) this.getVistaReflex().getListaDeAtributos()[i]).setControlador(nuevoControlador);
//                        ((PanelAtributoBoton) this.getVistaReflex().getListaDeAtributos()[i]).setEtiqueta(sEtiqueta[i]);
//                        break;
//                }
//            }
//
//            this.getVistaReflex().getContenedorDeAtributos().setLayout(new GridLayout(sEtiqueta.length, 1));
//
//            this.getVistaReflex().getContenedorDeAtributos().removeAll();
//
//            for (IAtributo p : this.getVistaReflex().getListaDeAtributos()) {
//                this.getVistaReflex().getContenedorDeAtributos().add((Component) p);
//            }
//
//            //Los PanelBotones
//            sEtiqueta = String.valueOf(this.getMetaDatos().get("BOTONES")).split(",");
//            this.getVistaReflex().setPanelBotones(new PanelBotones());
//
//            this.getVistaReflex().getPanelBotones().setBotones(sEtiqueta);
//
//            this.getVistaReflex().getPanelBotones().setControlador(this);
//
//            this.getVistaReflex().getPanelBotones().inicializar();
//
//            this.getVistaReflex().getContenedorDeBotones().setLayout(new GridLayout(1, 1));
//
//            this.getVistaReflex().getContenedorDeBotones().removeAll();
//
//            this.getVistaReflex().getContenedorDeBotones().add(this.getVistaReflex().getPanelBotones());
//        } else {
//            Mensaje.mostrarMensaje("Estructura de Datos mal formada");
//        }
//    }
//
//    @Override
//    public void getDatosVista() {
//        HashMap hmDatosDelModelo = new HashMap();//this.objetoReflex.getDatos();
//        String sEtiqueta[] = String.valueOf(this.getMetaDatos().get("ATRIBUTOS")).split(",");
//        String sTipo[] = String.valueOf(this.getMetaDatos().get("TIPOATRIBUTOS")).split(",");
//
//        if (sEtiqueta != null && sTipo != null) {
//
//            for (int i = 0; i < sEtiqueta.length; i++) {
//                switch (sTipo[i]) {
//                    case "INT":
//                    case "FLOAT":
//                    case "DOUBLE":
//                    case "JAVA.LANG.STRING":
//                        hmDatosDelModelo.put(
//                                ((PanelAtributoEtiquetaTexto) this.getVistaReflex().getListaDeAtributos()[i]).getEtiqueta().toUpperCase(),
//                                ((PanelAtributoEtiquetaTexto) this.getVistaReflex().getListaDeAtributos()[i]).getTexto());
//                        break;
//                    default:
//
//                        ControladorReflex controladorActual = ((ControladorReflex) this.getMetaDatos().get("CONTROLADOR"));
//
//                        String claveHashMap = controladorActual.getObjetoReflex().getClass().getSimpleName().toUpperCase();
//
//                        IObjeto modeloTemporal = controladorActual.getObjetoReflex();
//
//                        controladorActual.setDatos(modeloTemporal.getDatos());
//
//                        controladorActual.setDatosModelo();
//
//                        controladorActual.setDatosVista();
//
//                        hmDatosDelModelo.put(claveHashMap, modeloTemporal);
//
//                        break;
//                }
//            }
//        } else {
//            Mensaje.mostrarMensaje("Estructura de Datos mal formada");
//        }
//        this.getObjetoReflex().setDatos(hmDatosDelModelo);
//        this.setDatos(hmDatosDelModelo);
//    }
//
    @Override
    public void actionPerformed(ActionEvent e) {
//        String comando = e.getActionCommand().toUpperCase();
//
//        switch (comando) {
//            case "ALTA":
//                JOptionPane.showMessageDialog(null, "El controlador (" + this.getNombre().toUpperCase() + ") capturó el comando => " + e.getActionCommand());
////ALTA
//                this.inicializarMetaDatos();
//                this.inicializarVista();
//                this.setDatosModelo();
//                this.setDatosVista();
//                this.getVistaReflex().mostrar();
//                break;
//            case "BAJA":
//            case "MODIFICAR":
//            case "BUSCAR":
//            case "MOSTRAR":
//            default:
//                Mensaje.mostrarMensaje("Función (" + comando + ") no Implementada");
//                break;
//        }
//
//
//        if (e.getActionCommand().contains("menuSalir") || e.getActionCommand().contains("SALIR")) {
//            System.exit(0);
//        }
//
//        if (e.getActionCommand().contains("Cliente") || e.getActionCommand().contains("sub")) {
//            JOptionPane.showMessageDialog(null, "El controlador (" + this.getNombre().toUpperCase() + ") capturó el comando => " + e.getActionCommand());
////ALTA
//            this.inicializarMetaDatos();
//            this.inicializarVista();
//            this.setDatosModelo();
//            this.setDatosVista();
//            this.getVistaReflex().mostrar();
//
//        }
//        if (e.getActionCommand().contains("ACEPTAR") || e.getActionCommand().contains("GUARDAR")) {
//            this.getVistaReflex().ocultar();
//            this.getDatosVista();
//            this.getVistaReflex().cerrar();
//
//            BDEnMemoria.conectar().insertar(this.getObjetoReflex());
//
//            Mensaje.mostrarMensaje(this.getObjetoReflex().toString());
//            Mensaje.mostrarMensaje("Los datos fueron guardados con exito");
//        }
//
//        if (e.getActionCommand().contains("VER") || e.getActionCommand().contains("Ver")) {
//            Mensaje.mostrarMensaje("Numero de Registros " + BDEnMemoria.conectar().getNumeroDeRegistros());
//            for (Registro r : BDEnMemoria.conectar().seleccionarTodos("CLiente")) {
//                Mensaje.mostrarMensaje(r.getId() + r.getSNombreTabla() + r.getODatos().toString());
//            }
//        }
//
//        if (e.getActionCommand().contains("CANCELAR")) {
//            this.getVistaReflex().cerrar();
//        }
    }

    //<editor-fold defaultstate="collapsed" desc="Método Main">
    public static void main(String[] args) {

        ControladorReflex miControlador = new ControladorReflex("modelo.Cliente");

        //SIMULO que tomo los datos de la vista Y LOS INSERTO EN EL HASHMAP
        //(como EJEMPLO!!!!!!!!!, LA VISTA NO ESTA LISTA TODAVIA QLIAO)
        HashMap hmDatos = new HashMap();
        hmDatos.put("ID", "009");
        hmDatos.put("NOMBRE", "Ale");
        hmDatos.put("APELLIDO", "Wolf");
        hmDatos.put("DNI", "34132393");
        hmDatos.put("DOMICILIO", "BS AS 671");
        hmDatos.put("LOCALIDAD", "Tucuman");
        hmDatos.put("CUIT", "20-03");
//        hmDatos.put("CATEGORIA", new Categoria(0, "Irresponsable"));
//        miControlador.setDatos(hmDatos);
//        miControlador.setDatosModelo();
//        miControlador.inicializarMetaDatos();
//        miControlador.inicializarVista();
//        miControlador.setDatosVista();
//        miControlador.getVistaReflex().mostrar();

//        BDEnMemoria.conectar().insertar(new Cliente());
    }
    //</editor-fold>
}