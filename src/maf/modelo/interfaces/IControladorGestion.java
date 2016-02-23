/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.modelo.interfaces;

import java.awt.event.ActionListener;
import java.util.HashMap;
import maf.modelo.ObjetoBase;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public interface IControladorGestion extends ActionListener{

    //<editor-fold defaultstate="collapsed" desc="Controlador de Gestion">
    public String getNombre();
    
    public void inicializar();
    
    public void setDatos(HashMap hmDatos);
    
    public HashMap getDatos();
    
    public HashMap getMetaDatos();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Funciones de Gestión del Objeto">
    public boolean setModelo(String sModelo);

    public Class getModelo();

    public boolean creaNuevoObjeto();
    
    public void setObjeto(ObjetoBase objeto);

    public ObjetoBase getObjeto();

    /**
     * Inicializa el Objeto con sus valores por default
     */
    public void inicializarObjeto();

    /**
     * Toma los metadatos del modelo como ser el nombre de la clase, los
     * atributos, y si tiene una superclase tambien toma sus atributos.
     *
     * @return
     */
    public HashMap getMetaDatosDeObjeto();

    /**
     * Toma los datos ya cargados en el objeto
     *
     * @return
     */
    public HashMap getDatosDeObjeto();

    /**
     * Carga los datos en un objeto del modelo.
     */
    public boolean setDatosAObjeto();

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Funciones de Gestión de la Vista">
    public void inicializarVista();

    public void setMetaDatosVista();

    public void getDatosDeVista();

    public void setDatosVista();
    
    //</editor-fold>
}
