/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class PanelBotones extends JPanel {

    JButton btnBotones[];
    ActionListener controlador;

    public PanelBotones() {
    }

    public JButton[] getListaBotones() {
        return btnBotones;
    }

    public void setBotones(String[] sNombreDeBotones) {
        if (sNombreDeBotones.length == 0) {
            sNombreDeBotones = new String[2];
            sNombreDeBotones[0] = "Aceptar";
            sNombreDeBotones[1] = "Cancelar";
        }
        btnBotones = new JButton[sNombreDeBotones.length];
        for (int i = 0; i < sNombreDeBotones.length; i++) {
            this.btnBotones[i] = new JButton();
            this.btnBotones[i].setText(sNombreDeBotones[i]);
            this.btnBotones[i].setActionCommand(sNombreDeBotones[i].toUpperCase());
        }
        this.setControlador(this.controlador);
    }

    public void setEtiquetaBoton(String sEtiqueta, int idx) {
        if (idx >= 0 && idx < this.btnBotones.length) {
            this.btnBotones[idx].setText(sEtiqueta);
        }
    }

    public void setComandoBoton(String sComando, int idx) {
        if (idx >= 0 && idx < this.btnBotones.length) {
            this.btnBotones[idx].setActionCommand(sComando.toUpperCase());
        }
    }

    public void setControlador(ActionListener controlador) {
        if (controlador != null) {
            this.controlador = controlador;
            for (JButton btn : this.btnBotones) {
                btn.addActionListener(this.controlador);
            }
        }
    }

    public final void inicializar(ActionListener controlador, String[] sNombreDeBotones, boolean horizontal) {
        this.controlador = controlador;
        this.setBotones(sNombreDeBotones);
        if (horizontal) {
            FlowLayout layout = new FlowLayout();
            layout.setAlignment(FlowLayout.CENTER);
            layout.setHgap(20);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);
            for (JButton btn : this.btnBotones) {
                this.add(btn);
            }
        } else {
            GridLayout layout = new GridLayout();
            layout.setColumns(1);
            layout.setRows(this.btnBotones.length);
            layout.setVgap(20);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);
            for (JButton btn : this.btnBotones) {
                this.add(btn);
            }
        }
    }

    public void bloquear(boolean sBloqueo) {
        for (JButton btn : this.btnBotones) {
            btn.setEnabled(!sBloqueo);
        }
    }
    
    public void bloquearBoton(boolean sBloqueo, int idx) {
        if (idx >= 0 && idx < this.btnBotones.length) {
            this.btnBotones[idx].setEnabled(!sBloqueo);
        }
    }

    public void reestructurarBotones(String[] sBotones){
        this.setBotones(sBotones);
    }
    
}
