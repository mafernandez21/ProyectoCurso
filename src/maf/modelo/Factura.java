/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maf.modelo;

import java.util.*;
import maf.core.Core;
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
        this.totaliva = 0.0;
        for (DetalleFactura d : detalles) {
            totaliva += d.getImporteiva();
        }
    }

    public void setTotalneto() {
        this.totalneto = 0.0;
        for (DetalleFactura d : detalles) {
            totalneto += d.getTotallinea();
        }
    }

    public void setTotal() {
        this.total = 0.0;
        total = getTotaliva() + getTotalneto();
    }

    @Override
    public String toString() {
        String sOut = "";

        sOut = "Factura\n"
                + "Fecha : " + this.fecha + "\n"
                + "Numero : " + this.numero + "\n"
                + "Tipo : " + this.tipo + "\n"
                + "************************************\n"
                + "Cliente\n" + cliente
                + "\n************************************\n"
                + "Detalles" + "\n";

        for (DetalleFactura df : this.getDetalles()) {
            sOut += df.toString() + "\n";
        }

        sOut += "************************************\n"
                + "Total Neto : $" + totalneto + "\n"
                + "Total IVA : $" + totaliva + "\n"
                + "Total : $" + total;
        return sOut;
    }

    public String toString2() {
        String sOut = "";

        sOut = "Factura\n"
                + "Fecha : " + this.fecha + "\n"
                + "Numero : " + this.numero + "\n"
                + "Tipo : " + this.tipo + "\n"
                + "************************************\n"
                + "Cliente\n" + cliente
                + "\n************************************\n"
                + "Detalles"
                + "\n(Cantidad/Producto/Precio/ImporteIVA/TotalLinea)\n";
        for (DetalleFactura df : this.getDetalles()) {
            sOut += df.toString2() + "\n";
        }

        sOut += "************************************\n"
                + "Total Neto : $" + totalneto + "\n"
                + "Total IVA : $" + totaliva + "\n"
                + "Total : $" + total;
        return sOut;
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
        this.id = 0;
        this.cliente = new Cliente();
        this.fecha = new Date();
        this.numero = 0;
        this.detalles = new ArrayList<DetalleFactura>();
        this.tipo = TipoFactura.C;
        this.totalneto = 0.0;
        this.totaliva = 0.0;
        this.total = 0.0;

    }

    @Override
    public boolean setDatos(HashMap hmDatos) {
        try {
            if (!hmDatos.containsValue(null)) {
                this.setId(Integer.parseInt(String.valueOf(hmDatos.get("ID"))));
                this.setCliente((Cliente) hmDatos.get("CLIENTE"));
                this.setFecha((Date) hmDatos.get("FECHA"));
                this.setNumero(Integer.parseInt(String.valueOf(hmDatos.get("NUMERO"))));
                this.setDetalles((ArrayList<DetalleFactura>) hmDatos.get("DETALLES"));
                this.setTipo((TipoFactura) hmDatos.get("TIPO"));
                this.setTotalneto();
                this.setTotaliva();
                this.setTotal();
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
        hmSalida.put("CLIENTE", this.getCliente());
        hmSalida.put("FECHA", this.getFecha());
        hmSalida.put("NUMERO", this.getNumero());
        hmSalida.put("DETALLES", this.getDetalles());
        hmSalida.put("TIPO", this.getTipo());
        hmSalida.put("TOTALNETO", this.getTotalneto());
        hmSalida.put("TOTALIVA", this.getTotaliva());
        hmSalida.put("TOTAL", this.getTotal());
        return hmSalida;
    }
    //</editor-fold>

}
