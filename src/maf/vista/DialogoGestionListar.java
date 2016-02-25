/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import maf.modelo.ObjetoBase;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class DialogoGestionListar extends Dialogo {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    PanelContenedorTexto txtFiltro;
    PanelContenedorGrilla panelGrilla;
    PanelBotones panelBotonFiltro;
    JPanel panel;
    PanelBotones panelBotonesComandos;
    FormularioPrincipal ventanaPrincipal;
    //</editor-fold>

    public DialogoGestionListar(JFrame ventanaPrincipal, boolean modal) {
        super(ventanaPrincipal, modal);
        this.txtFiltro = new PanelContenedorTexto();
        this.panelGrilla = new PanelContenedorGrilla();
        this.panelBotonFiltro = new PanelBotones();
        this.panel = new JPanel();
        this.panelBotonesComandos = new PanelBotones();
    }

    @Override
    public void inicializar() {
        super.inicializar();
        this.txtFiltro.inicializar();
        this.txtFiltro.setValor("");
        this.txtFiltro.setEtiqueta("Filtro");

        String sFiltro[] = new String[1];
        sFiltro[0] = "Filtrar";
        this.panelBotonFiltro.inicializar(this.getControlador(), sFiltro, true);

        this.txtFiltro.add(this.panelBotonFiltro);

        this.panelGrilla.inicializar();

        this.panelGrilla.add(this.txtFiltro);
        this.getPanelCentral().add(this.panelGrilla);

        String sBotones[] = new String[2];
        sBotones[0] = "Listo";
        sBotones[1] = "Volver";
        this.panelBotonesComandos.inicializar(this.getControlador(), sBotones, true);

        if (this.panelGrilla.getTblGrilla().getSelectedRow() == -1) {
            this.panelBotonesComandos.bloquear(true);
            this.panelBotonesComandos.bloquearBoton(false, 1);
        }

        this.panelGrilla.getTblGrilla().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (panelGrilla.getTblGrilla().getSelectedRow() > -1) {
                    panelBotonesComandos.bloquear(false);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        
        this.panelGrilla.getPanelGrilla().addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (panelGrilla.getTblGrilla().getSelectedRow() == -1) {
                    panelBotonesComandos.bloquear(true);
                    panelBotonesComandos.bloquearBoton(false,1);
                }
            }
        });

        this.getPanelInferior().add(this.panelBotonesComandos);

        this.pack();
    }
    
    @Override
    public void recuperarDatosDeGUI() {
        if (this.panelGrilla.getTblGrilla().getSelectedRow() != -1) {
            //   this.panelGrilla.getValor();
            Object fila[] = (Object[]) this.panelGrilla.getValor();
            String sEtiqueta[] = String.valueOf(this.getMetaDatos().get("ATRIBUTOS")).split(",");

            for (int i = 0; i < sEtiqueta.length; i++) {
                this.getDatos().put(sEtiqueta[i], fila[i]);
            }
        }
    }

    @Override
    public void ConstruirVista() {
        this.prepararTblTablaDatos();
    }

    public void prepararTblTablaDatos() {
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
        }
    }

}
