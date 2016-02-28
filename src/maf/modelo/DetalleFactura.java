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
    private double precioUnitario;
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

    public double getPrecioUnitario() {
        return precioUnitario;
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
        this.importeiva = this.getProducto().getTipoIVA().getValor() * (this.getCantidad() * this.getPrecioUnitario());

    }

    public void setPrecioUnitario() {
        this.precioUnitario = this.producto.getPrecio();
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setTotallinea() {
        this.totallinea = this.getCantidad() * this.getPrecioUnitario();
    }

    @Override
    public String toString() {
         java.text.DecimalFormat formato=new java.text.DecimalFormat("##0.0##");
        return "(Cantidad/Producto/Precio/ImporteIVA/TotalLinea)\n"
                + cantidad + " \t\t\t\t "
                + producto.toString2() + " \t\t\t\t $"
                + formato.format(precioUnitario) + " \t\t\t\t $"
                + formato.format(importeiva) + " \t\t\t\t $"
                + formato.format(totallinea);
    }

    public String toString2() {
        java.text.DecimalFormat formato=new java.text.DecimalFormat("##0.0##");
        return  cantidad + " \t\t\t\t "
                + producto.toString2() + " \t\t\t\t $"
                + formato.format(precioUnitario) + " \t\t\t\t $"
                + formato.format(importeiva) + " \t\t\t\t $"
                + formato.format(totallinea);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    protected void setUpValoresDefault() {
        this.producto = new Producto();
        this.cantidad = 0;
        this.precioUnitario = 0.0;
        this.importeiva = 0.0;
        this.totallinea = 0.0;
    }

    @Override
    public boolean setDatos(HashMap hmDatos) {
        try {
            if (!hmDatos.containsValue(null)) {
                this.setProducto((Producto) hmDatos.get("PRODUCTO"));
                this.setCantidad(Integer.parseInt(String.valueOf(hmDatos.get("CANTIDAD"))));
                this.precioUnitario = Double.parseDouble(String.valueOf(hmDatos.get("PRECIOUNITARIO")));
                this.importeiva = Double.parseDouble(String.valueOf(hmDatos.get("IMPORTEIVA")));
                this.totallinea = Double.parseDouble(String.valueOf(hmDatos.get("TOTALLINEA")));
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
        hmSalida.put("PRODUCTO", this.getProducto());
        hmSalida.put("CANTIDAD", this.getCantidad());
        hmSalida.put("PRECIOUNITARIO", this.getPrecioUnitario());
        hmSalida.put("IMPORTEIVA", this.getImporteiva());
        hmSalida.put("TOTALLINEA", this.getTotallinea());
        return hmSalida;
    }
    //</editor-fold>

}
