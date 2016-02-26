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
public class DialogoGestion extends Dialogo {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    PanelContenedorGrilla panelGrilla;
    PanelBotones panelBotonesComandos;
    VentanaPrincipal ventanaPrincipal;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public DialogoGestion(JFrame ventanaPrincipal, boolean modal) {
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

        String sBotones[] = new String[5];
        sBotones[0] = "Alta";
        sBotones[1] = "Baja";
        sBotones[2] = "Modificar";
        sBotones[3] = "Ver";
        sBotones[4] = "Volver";
        this.panelBotonesComandos.inicializar(this.getControlador(), sBotones, false);

        if (this.panelGrilla.getTblGrilla().getSelectedRow() == -1) {
            this.panelBotonesComandos.bloquear(true);
            this.panelBotonesComandos.bloquearBoton(false, 0);
            this.panelBotonesComandos.bloquearBoton(false, 4);
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
                if (panelGrilla.getTblGrilla().getSelectedRow() == -1) {
                    panelBotonesComandos.bloquear(true);
                    panelBotonesComandos.bloquearBoton(false, 0);
                    panelBotonesComandos.bloquearBoton(false, 4);
                }
            }
        });

        this.getPanelDerecho().add(this.panelBotonesComandos);

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
    //</editor-fold>

}
