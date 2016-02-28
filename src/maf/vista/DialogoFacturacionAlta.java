/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import maf.controlador.ControladorGestion;
import maf.core.Core.TipoFactura;
import maf.modelo.Cliente;
import maf.modelo.Factura;
import maf.modelo.ObjetoBase;
import maf.modelo.interfaces.IVista;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class DialogoFacturacionAlta extends Dialogo implements IVista {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private JPanel panelDatosCliente;
    private PanelContenedorEtiqueta lblFecha;
    private PanelContenedorEtiqueta lblNombreApellidoCliente;
    private PanelContenedorEtiqueta lblCategoriaCliente;
    private PanelBotones btnAgregarCliente;

    private JPanel panelDatosDetalles;
    private PanelBotones agregarDetalle;
    private PanelContenedorGrilla panelGrilla;

    private JPanel panelDatosFactura;
    private PanelContenedorEtiqueta lblSubTotal;
    private PanelContenedorEtiqueta lblTotal;

    private PanelContenedorCombo cboTipoDeFactura;

    private PanelBotones botonesDeVentana;
    private ActionListener controladorListadoClientes;
    private ActionListener controladorDetalles;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public DialogoFacturacionAlta(JFrame ventanaPrincipal, boolean modal, ActionListener controladorListadoClientes, ActionListener controladorDetalles) {
        super(ventanaPrincipal, modal);
        this.lblFecha = new PanelContenedorEtiqueta();
        this.lblNombreApellidoCliente = new PanelContenedorEtiqueta();
        this.lblCategoriaCliente = new PanelContenedorEtiqueta();
        this.btnAgregarCliente = new PanelBotones();
        this.agregarDetalle = new PanelBotones();
        this.panelGrilla = new PanelContenedorGrilla();
        this.lblSubTotal = new PanelContenedorEtiqueta();
        this.lblTotal = new PanelContenedorEtiqueta();
        this.botonesDeVentana = new PanelBotones();
        this.controladorListadoClientes = controladorListadoClientes;
        this.controladorDetalles = controladorDetalles;
        this.panelDatosCliente = new JPanel();
        this.panelDatosDetalles = new JPanel();
        this.panelDatosFactura = new JPanel();
        this.cboTipoDeFactura = new PanelContenedorCombo();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public PanelContenedorEtiqueta getLblNombreApellidoCliente() {
        return lblNombreApellidoCliente;
    }

    public void setLblNombreApellidoCliente(PanelContenedorEtiqueta lblNombreApellidoCliente) {
        this.lblNombreApellidoCliente = lblNombreApellidoCliente;
    }

    public PanelBotones getBtnAgregarCliente() {
        return btnAgregarCliente;
    }

    public void setBtnAgregarCliente(PanelBotones btnAgregarCliente) {
        this.btnAgregarCliente = btnAgregarCliente;
    }

    public PanelBotones getAgregarDetalle() {
        return agregarDetalle;
    }

    public void setAgregarDetalle(PanelBotones agregarDetalle) {
        this.agregarDetalle = agregarDetalle;
    }

    public PanelContenedorGrilla getPanelGrilla() {
        return panelGrilla;
    }

    public void setPanelGrilla(PanelContenedorGrilla panelGrilla) {
        this.panelGrilla = panelGrilla;
    }

    public PanelContenedorEtiqueta getLblSubTotal() {
        return lblSubTotal;
    }

    public void setLblSubTotal(PanelContenedorEtiqueta lblSubTotal) {
        this.lblSubTotal = lblSubTotal;
    }

    public PanelContenedorEtiqueta getLblTotal() {
        return lblTotal;
    }

    public void setLblTotal(PanelContenedorEtiqueta lblTotal) {
        this.lblTotal = lblTotal;
    }

    public PanelBotones getBotonesDeVentana() {
        return botonesDeVentana;
    }

    public void setBotonesDeVentana(PanelBotones botonesDeVentana) {
        this.botonesDeVentana = botonesDeVentana;
    }

    public ActionListener getControladorListadoClientes() {
        return this.controladorListadoClientes;
    }

    public void setControladorListadoClientes(ActionListener controladorListadoClientes) {
        this.controladorListadoClientes = controladorListadoClientes;
    }

    public ActionListener getControladorDetalles() {
        return controladorDetalles;
    }

    public void setControladorDetalles(ActionListener controladorDetalles) {
        this.controladorDetalles = controladorDetalles;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Implementaciones">
    @Override
    public void inicializar() {
        super.inicializar();
        this.construirVista();

        this.panelDatosCliente.setLayout(new GridLayout(4, 1));

        //this.panelDatosDetalles.setLayout(new GridLayout(1, 1));
        this.lblFecha.inicializar();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        this.lblFecha.setEtiqueta("Fecha:" + formato.format(new Date()));
        this.panelDatosCliente.add(this.lblFecha);

        this.lblNombreApellidoCliente.inicializar();
        this.lblNombreApellidoCliente.setEtiqueta("Nombre del Cliente");
        this.panelDatosCliente.add(this.lblNombreApellidoCliente);

        this.lblCategoriaCliente.inicializar();
        this.lblCategoriaCliente.setEtiqueta("Categoria del Cliente");
        this.panelDatosCliente.add(this.lblCategoriaCliente);

        this.cboTipoDeFactura.inicializar();
        this.cboTipoDeFactura.setEtiqueta("Tipo de Factura");
        this.cboTipoDeFactura.setValor(TipoFactura.A);

        this.panelDatosCliente.add(cboTipoDeFactura);

        this.getPanelSuperior().add(this.panelDatosCliente);

        String sBotones1[] = {"Seleccionar un Cliente"};
        this.btnAgregarCliente.inicializar(this.controladorListadoClientes, sBotones1, true);
        this.btnAgregarCliente.setComandoBoton("setCliente", 0);
        this.getPanelSuperior().add(this.btnAgregarCliente);

        //this.getPanelCentral().setLayout(new GridLayout(3, 1));
        this.panelGrilla.inicializar();
        this.panelDatosDetalles.add(this.panelGrilla);

        this.prepararTblTablaDatos();

        String sBotones2[] = {"Agregar Detalle"};
        this.agregarDetalle.inicializar(this.controladorDetalles, sBotones2, true);
        this.agregarDetalle.setComandoBoton("nuevoDetalle", 0);
        this.panelDatosDetalles.add(this.agregarDetalle);

        this.lblSubTotal.inicializar();
        this.lblSubTotal.setEtiqueta("Sub Total : ");
        this.lblTotal.inicializar();
        this.lblTotal.setEtiqueta("Total : ");

        this.getPanelCentral().add(this.panelDatosDetalles);

        this.panelDatosFactura.add(this.lblSubTotal);
        this.panelDatosFactura.add(this.lblTotal);

        this.getPanelInferior().add(this.panelDatosFactura);

        String sBotones3[] = {"Guardar", "Cancelar"};
        this.botonesDeVentana.inicializar(this.getControlador(), sBotones3, true);
        this.getPanelInferior().add(this.botonesDeVentana);

        //    this.setSize(500, 400);
        this.pack();
    }

    @Override
    public void construirVista() {

    }

    @Override
    public void recuperarDatosDeGUI() {
        ControladorGestion c=(ControladorGestion)this.getControlador();
        Factura f=(Factura)c.getObjeto();
        
        f.setTotalneto();
        f.setTotaliva();
        f.setTotal();
        
        c.getDatosDeObjeto();
        
        this.getDatos().putAll(((ControladorGestion)this.getControlador()).getDatos());
        
        this.getDatos().put("TIPO", this.cboTipoDeFactura.getValor());
        this.getDatos().put("TOTALNETO", f.getTotalneto());
        this.getDatos().put("TOTALIVA", f.getTotaliva());
        this.getDatos().put("TOTAL", f.getTotal());
    }

    @Override
    public void actualizarDatosDeVista() {
        ControladorGestion c=(ControladorGestion)this.getControlador();
        Factura f=(Factura)c.getObjeto();
        Cliente cliente=f.getCliente();
        
        
        f.setTotalneto();
        f.setTotaliva();
        f.setTotal();
        
        this.lblNombreApellidoCliente.setEtiqueta("Nombre, Apellido: " + cliente.getNombre() + ", " + cliente.getApellido());
        this.lblCategoriaCliente.setEtiqueta("Categoria: " + cliente.getCategoria());
        
        java.text.DecimalFormat formato=new java.text.DecimalFormat("##0.0##");
        
        
        this.lblSubTotal.setEtiqueta("Sub Total (ImporteIVA): " + formato.format(f.getTotalneto())+"("+formato.format(f.getTotaliva())+")");
        this.lblTotal.setEtiqueta("Total : " + formato.format(f.getTotal()));
        
    }

    @Override
    public void setListaDeAtributos(PanelContenedor[] paneles) {
    }

    @Override
    public PanelContenedor[] getListaDeAtributos() {
        return null;
    }

    public void prepararTblTablaDatos() {

        ControladorGestion cg = new ControladorGestion();
        cg.setModelo("maf.modelo.DetalleFactura");
        cg.inicializar();
        cg.creaNuevoObjeto();
        cg.inicializarObjeto();
        cg.getMetaDatosDeObjeto();
        cg.setVista(this);
        cg.setMetaDatosVista();

        String sEtiqueta[] = String.valueOf(this.getMetaDatos().get("ATRIBUTOS")).split(",");

        DefaultTableModel modelo = new DefaultTableModel();

        this.panelGrilla.getTblGrilla().setModel(modelo);

        for (String s : sEtiqueta) {
            modelo.addColumn(s);
        }
    }

    public void actualizarTablaDatos(ArrayList<ObjetoBase> listaDeObjetos) {
        if (listaDeObjetos != null) {
            this.prepararTblTablaDatos();
            DefaultTableModel modelo = (DefaultTableModel) this.panelGrilla.getTblGrilla().getModel();
            for (ObjetoBase ob : listaDeObjetos) {
                String sEtiqueta[] = String.valueOf(ob.getMetaDatos().get("ATRIBUTOS")).split(",");
                Object[] fila = new Object[sEtiqueta.length];
                for (int i = 0; i < sEtiqueta.length; i++) {
                    fila[i] = ob.getDatos().get(sEtiqueta[i]);
                }
                modelo.addRow(fila);
            }
            this.panelGrilla.getTblGrilla().setModel(modelo);
            this.actualizarDatosDeVista();
        }
    }

    //</editor-fold>
}
