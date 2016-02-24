/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maf.modelo;

import java.util.HashMap;
import maf.core.Core;
import maf.core.Core.TipoIVA;

/**
 *
 * @author Paradigmas
 */
public class Producto extends ObjetoBase {

    private int id;
    private String descripcion;
    private double precio;
    private TipoIVA tipoiva;
    private int stock;

    public Producto() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public TipoIVA getTipoIVA() {
        return tipoiva;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setTipoIVA(TipoIVA tipoiva) {
        this.tipoiva = tipoiva;
    }

    @Override
    public String toString() {
        return " Producto{" + " id=" + id + " descripcion=" + descripcion + " precio=" + precio + " tipoiva=" + tipoiva + " stock=" + stock + '}';
    }

    @Override
    protected void setUpValoresDefault() {
        this.setId(0);
        this.setDescripcion("Sin-Descripcion");
        this.setPrecio(0.0);
        this.setTipoIVA(TipoIVA._21);
        this.setStock(0);
    }

    @Override
    public boolean setDatos(HashMap hmDatos) {
        try {
            if (!hmDatos.containsValue(null)) {
                this.setId(Integer.parseInt(String.valueOf(hmDatos.get("ID"))));
                this.setDescripcion(String.valueOf(hmDatos.get("DESCRIPCION")));
                this.setPrecio(Double.parseDouble(String.valueOf(hmDatos.get("PRECIO"))));
                this.setTipoIVA((TipoIVA) (hmDatos.get("TIPOIVA")));
                this.setStock(Integer.parseInt(String.valueOf(hmDatos.get("STOCK"))));
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
        hmSalida.put("DESCRIPCION", this.getDescripcion());
        hmSalida.put("PRECIO", this.getPrecio());
        hmSalida.put("TIPOIVA", this.getTipoIVA());
        hmSalida.put("STOCK", this.getStock());
        return hmSalida;
    }

}
