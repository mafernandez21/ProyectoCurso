/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.modelo;

import java.util.HashMap;
import maf.core.Core;
import maf.core.Core.Categoria;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class Cliente extends Persona {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private String cuit;
    private Categoria categoria;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Cliente() {
        super();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Categoria getCategoria() {
        return this.categoria;
    }

    public String getCuit() {
        return this.cuit;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCliente" + " \ncuit=" + cuit + " \ncategoria=" + categoria;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    public void setUpValoresDefault() {
        super.setUpValoresDefault();
        this.setCuit("Sin-CUIT");
        this.setCategoria(Categoria.CONSUMIDOR_FINAL);
    }

    @Override
    public boolean setDatos(HashMap hmDatos) {
        try {
            if (!hmDatos.containsValue(null)) {
                this.setId(Integer.parseInt(String.valueOf(hmDatos.get("ID"))));
                this.setNombre(String.valueOf(hmDatos.get("NOMBRE")));
                this.setApellido(String.valueOf(hmDatos.get("APELLIDO")));
                this.setDni(String.valueOf(hmDatos.get("DNI")));
                this.setDomicilio(String.valueOf(hmDatos.get("DOMICILIO")));
                this.setLocalidad(String.valueOf(hmDatos.get("LOCALIDAD")));
                this.setCuit(String.valueOf(hmDatos.get("CUIT")));
                this.setCategoria((Categoria) hmDatos.get("CATEGORIA"));
                return true;
            }
        } catch (Exception ex) {
            Core.mostrarMensajeError("Estructura de Datos Malformada para " + this.getClass().getSimpleName());
        }
        return false;
    }

    @Override
    public HashMap getDatos() {
        HashMap hmSalida = new HashMap();
        hmSalida.put("ID", this.getId());
        hmSalida.put("NOMBRE", this.getNombre());
        hmSalida.put("APELLIDO", this.getApellido());
        hmSalida.put("DNI", this.getDni());
        hmSalida.put("DOMICILIO", this.getDomicilio());
        hmSalida.put("LOCALIDAD", this.getLocalidad());
        hmSalida.put("CUIT", this.getCuit());
        hmSalida.put("CATEGORIA", this.getCategoria());
        return hmSalida;
    }
    //</editor-fold>
}
