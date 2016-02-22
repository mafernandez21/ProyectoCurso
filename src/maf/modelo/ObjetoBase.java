/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.modelo;

import java.lang.reflect.Field;
import java.util.HashMap;
import maf.core.Core;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public abstract class ObjetoBase {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    HashMap hmDatos;
    HashMap hmMetaDatos;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public ObjetoBase() {
    }

    public ObjetoBase(HashMap hmDatos) {
        this.hmDatos.putAll(hmDatos);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Override">
    /**
     * Inicializa el objeto con sus valores por default
     */
    protected abstract void setUpValoresDefault();

    public abstract void setDatos(HashMap hmDatos);

    public abstract HashMap getDatos();
    //</editor-fold>

    public void inicializar() {
        this.hmDatos = new HashMap();
        this.hmMetaDatos = new HashMap();
        this.prepararMetaDatos();
        this.setUpValoresDefault();
    }

    public HashMap getMetaDatos() {
        //this.setUpMetaDatos();
        return this.hmMetaDatos;
    }

    public void prepararMetaDatos() {
        try {
            String sNombreSuperClase = String.valueOf(this.setUpMetaDatos(this.getClass()).get("SUPERCLASE"));
            String sNombreClase = String.valueOf(this.setUpMetaDatos(this.getClass()).get("CLASE"));
            String sNombreAtributos = String.valueOf(this.setUpMetaDatos(this.getClass()).get("ATRIBUTOS"));
            String sTipoAtributos = String.valueOf(this.setUpMetaDatos(this.getClass()).get("TIPOATRIBUTOS"));

            this.getMetaDatos().put("SUPERCLASE", sNombreSuperClase);
            this.getMetaDatos().put("CLASE", sNombreClase);
            this.getMetaDatos().put("ATRIBUTOS", sNombreAtributos);
            this.getMetaDatos().put("TIPOATRIBUTOS", sTipoAtributos);
        } catch (ClassNotFoundException ex) {
            Core.mostrarMensajeError("Generación de metadatos fallida, " + ex.getMessage());
        }

    }

    private HashMap setUpMetaDatos(Class clase) throws ClassNotFoundException {
        HashMap hmSalida = new HashMap();

        String sNombreSuperClase = clase.getSuperclass().getName();
        String sNombreClase = clase.getName();
        String sNombreAtributos = "";
        String sTipoAtributos = "";

        Field atributos[];

        //Extrae los atributos con sus tipos
        atributos = clase.getDeclaredFields();
        for (Field f : atributos) {
            f.setAccessible(true);
            try {
                sNombreAtributos += f.getName().trim().toUpperCase() + ",";
                sTipoAtributos += f.getGenericType().toString().trim() + ",";
            } catch (IllegalArgumentException ex) {
                Core.mostrarMensajeError(ex.getMessage());
            }
        }
        sNombreAtributos = sNombreAtributos.substring(0, sNombreAtributos.length() - 1);
        sTipoAtributos = sTipoAtributos.substring(0, sTipoAtributos.length() - 1);

        sTipoAtributos = sTipoAtributos.replaceAll("class ", "");

        if (!sNombreSuperClase.equals(Object.class.getName()) && !sNombreSuperClase.equals(ObjetoBase.class.getName())) {
            hmSalida.putAll(this.setUpMetaDatos(Class.forName(sNombreSuperClase)));
            hmSalida.put("ATRIBUTOS", String.valueOf(hmSalida.get("ATRIBUTOS")) + "," + sNombreAtributos);
            hmSalida.put("TIPOATRIBUTOS", String.valueOf(hmSalida.get("TIPOATRIBUTOS")) + "," + sTipoAtributos);
        } else {
            hmSalida.put("ATRIBUTOS", sNombreAtributos);
            hmSalida.put("TIPOATRIBUTOS", sTipoAtributos);
        }
        hmSalida.put("SUPERCLASE", sNombreSuperClase);
        hmSalida.put("CLASE", sNombreClase);
        return hmSalida;
    }

}
