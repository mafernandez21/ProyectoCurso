/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
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
    PanelContenedorGrilla panelGrilla;
    PanelBotones panelBotonesComandos;
    VentanaPrincipal ventanaPrincipal;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Controlador">
    public DialogoGestionListar(JFrame ventanaPrincipal, boolean modal) {
        super(ventanaPrincipal, modal);
        this.panelGrilla = new PanelContenedorGrilla();
        this.panelBotonesComandos = new PanelBotones();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementaciones">
    @Override
    public void inicializar() {
        super.inicializar();
        this.panelGrilla.inicializar();

        this.getPanelCentral().add(this.panelGrilla);

        String sBotones[] = {"Seleccionar","Volver"};
        this.panelBotonesComandos.inicializar(this.getControlador(), sBotones, true);

        if (this.panelGrilla.getTblGrilla().getSelectedRow() < 0 ) {
            this.panelBotonesComandos.bloquear(true);
            this.panelBotonesComandos.bloquearBoton(false, 1);
        }
        
        this.panelGrilla.getTblGrilla().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (panelGrilla.getTblGrilla().getSelectedRow() != -1) {
                    panelBotonesComandos.bloquear(false);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        this.panelGrilla.getPanelGrilla().addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (panelGrilla.getTblGrilla().getSelectedRow() < 0) {
                    panelBotonesComandos.bloquear(true);
                    panelBotonesComandos.bloquearBoton(false, 1);
                }
            }
        });

        this.getPanelInferior().add(this.panelBotonesComandos);

        this.pack();
    }

    @Override
    public void recuperarDatosDeGUI() {
        if (this.panelGrilla.getTblGrilla().getSelectedRow() != -1) {
            Object fila[] = (Object[]) this.panelGrilla.getValor();
            String sEtiqueta[] = String.valueOf(this.getMetaDatos().get("ATRIBUTOS")).split(",");
            for (int i = 0; i < sEtiqueta.length; i++) {
                this.getDatos().put(sEtiqueta[i], fila[i]);
            }
        }
    }

    @Override
    public void construirVista() {
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

    @Override
    public void actualizarDatosDeVista() {}

    @Override
    public void setListaDeAtributos(PanelContenedor[] paneles) {}

    @Override
    public PanelContenedor[] getListaDeAtributos() {
        return null;
    }
    //</editor-fold>    
}
