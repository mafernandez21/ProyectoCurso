/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maf.modelo;

import java.util.*;
import maf.core.Core.TipoFactura;

/**
 *
 * @author Paradigmas
 */
public class Factura extends ObjetoBase {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private int id;
    private Cliente cliente;
    private Date fecha;
    private int numero;
    private ArrayList<DetalleFactura> detalles;
    private TipoFactura tipo;
    private double totalneto;
    private double totaliva;
    private double total;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Factura() {
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<DetalleFactura> getDetalles() {
        return detalles;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getNumero() {
        return numero;
    }

    public double getTotal() {
        return total;
    }

    public double getTotaliva() {
        return totaliva;
    }

    public double getTotalneto() {
        return totalneto;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDetalles(ArrayList<DetalleFactura> detalles) {
        this.detalles = detalles;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTotaliva() {
        for (DetalleFactura d : detalles) {
            totaliva += 0.21 * d.getTotallinea();
        }
    }

    public void setTotalneto() {
        for (DetalleFactura d : detalles) {
            totalneto += d.getTotallinea();
        }
    }

    public void setTotal() {
        total = getTotaliva() + getTotalneto();
    }

    @Override
    public String toString() {
        return " Factura{" + " cliente=" + cliente + " fecha=" + fecha + " numero=" + numero + " detalles=" + detalles + " totalneto=" + totalneto + " totaliva=" + totaliva + " total=" + total + '}';
    }

    public TipoFactura getTipo() {
        return tipo;
    }

    public void setTipo(TipoFactura tipo) {
        this.tipo = tipo;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    protected void setUpValoresDefault() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDatos(HashMap hmDatos) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap getDatos() {
        return null;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>

}
