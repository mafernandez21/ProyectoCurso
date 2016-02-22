/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maf.modelo;

import java.util.HashMap;
import maf.core.Core;

/**
 *
 * @author Paradigmas
 */
public class DetalleFactura extends ObjetoBase {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private Producto producto;
    private int cantidad;
    private double precio;
    private double importeiva;
    private double totallinea;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public DetalleFactura() {
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public int getCantidad() {
        return cantidad;
    }

    public double getImporteiva() {
        return importeiva;
    }

    public double getPrecio() {
        return precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public double getTotallinea() {
        return totallinea;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setImporteiva() {
        this.importeiva = this.getProducto().getTipoIVA().getValor() * (this.getCantidad() * this.getPrecio());

    }

    public void setPrecio() {
        this.precio = this.producto.getPrecio();
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setTotallinea() {
        this.totallinea = this.getCantidad() * this.getPrecio();
    }

    @Override
    public String toString() {
        return " DetalleFactura{" + " producto=" + producto + " cantidad="
                + cantidad + " precio=" + precio + " importeiva=" + importeiva
                + " totallinea=" + totallinea + '}';
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    protected void setUpValoresDefault() {
        this.producto = new Producto();
        this.cantidad = 0;
        this.precio = 0.0;
        this.importeiva = 0.0;
        this.totallinea = 0.0;
    }

    @Override
    public void setDatos(HashMap hmDatos) {
        try{
            this.setProducto((Producto) hmDatos.get("PRODUCTO"));
            this.setCantidad(Integer.parseInt(String.valueOf(hmDatos.get("CANTIDAD"))));
            this.precio = Double.parseDouble(String.valueOf(hmDatos.get("PRECIO")));
            this.importeiva = Double.parseDouble(String.valueOf(hmDatos.get("IMPORTEIVA")));
            this.totallinea = Double.parseDouble(String.valueOf(hmDatos.get("TOTALLINEA")));
        } catch(Exception ex) {
            Core.mostrarMensajeError("Estructura de Datos Malformada para " + this.getClass().getSimpleName());
        }
    }

    @Override
    public HashMap getDatos() {
        HashMap hmSalida = new HashMap();
        hmSalida.put("PRODUCTO", this.getProducto());
        hmSalida.put("CANTIDAD", this.getCantidad());
        hmSalida.put("PRECIO", this.getCantidad());
        hmSalida.put("IMPORTEIVA", this.getImporteiva());
        hmSalida.put("TOTALLINEA", this.getTotallinea());
        return hmSalida;
    }
    //</editor-fold>

}
