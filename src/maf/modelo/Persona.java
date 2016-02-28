/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.modelo;

import java.util.HashMap;
import maf.core.Core;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class Persona extends ObjetoBase {

    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String domicilio;
    private String localidad;

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Persona() {
        super();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "id : " + id +
               "\nNombre : " + nombre + 
               "\nApellido : " + apellido +
               "\nDNI : " + dni +
               "\nDomicilio : " + domicilio + 
               "\nLocalidad : " + localidad;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    public void setUpValoresDefault() {
        this.setId(0);
        this.setNombre("Sin Nombre");
        this.setApellido("Sin Apellido");
        this.setDni("00000000");
        this.setDomicilio("Sin dirección");
        this.setLocalidad("Sin localidad");
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
        return hmSalida;
    }
    //</editor-fold>
}
