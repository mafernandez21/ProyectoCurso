/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import maf.core.Core;
import maf.modelo.interfaces.IVista;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public abstract class Dialogo extends JDialog implements IVista {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private ActionListener controlador;
    private HashMap hmDatos;
    private HashMap hmMetaDatos;
    private JFrame ventanaPrincipal;

    private JPanel panelPrincipal;
    private JPanel panelSuperior;
    private JPanel panelCentral;
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private JPanel panelInferior;
    private PanelContenedor Atributos[];
    private PanelBotones Botones;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Dialogo(JFrame ventanaPrincipal, boolean modal) {
        super(ventanaPrincipal, modal);
        this.ventanaPrincipal = ventanaPrincipal;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JPanel getPanelSuperior() {
        return panelSuperior;
    }

    public void setPanelSuperior(JPanel panelSuperior) {
        this.panelSuperior = panelSuperior;
    }

    public JPanel getPanelCentral() {
        return panelCentral;
    }

    public void setPanelCentral(JPanel panelCentral) {
        this.panelCentral = panelCentral;
    }

    public JPanel getPanelIzquierdo() {
        return panelIzquierdo;
    }

    public void setPanelIzquierdo(JPanel panelIzquierdo) {
        this.panelIzquierdo = panelIzquierdo;
    }

    public JPanel getPanelDerecho() {
        return panelDerecho;
    }

    public void setPanelDerecho(JPanel panelDerecho) {
        this.panelDerecho = panelDerecho;
    }

    public JPanel getPanelInferior() {
        return panelInferior;
    }

    public void setPanelInferior(JPanel panelInferior) {
        this.panelInferior = panelInferior;
    }

    public PanelContenedor[] getAtributos() {
        return Atributos;
    }

    public void setAtributos(PanelContenedor[] Atributos) {
        this.Atributos = Atributos;
    }

    public PanelBotones getBotones() {
        return Botones;
    }

    public void setBotones(PanelBotones Botones) {
        this.Botones = Botones;
    }

    //</editor-fold>
    
    @Override
    public void inicializar() {

        this.panelPrincipal = new JPanel();
        this.panelSuperior = new JPanel();
        this.panelIzquierdo = new JPanel();
        this.panelDerecho = new JPanel();
        this.panelCentral = new JPanel();
        this.panelInferior = new JPanel();
        this.hmDatos = new HashMap();
        this.hmMetaDatos = new HashMap();

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                cerrar();
            }
        });

        BorderLayout layout = new BorderLayout();
        this.panelPrincipal.setLayout(layout);
        this.panelPrincipal.setBackground(new java.awt.Color(102, 153, 0));

        this.panelSuperior.setBackground(Color.YELLOW);
        this.panelIzquierdo.setBackground(Color.BLACK);
        this.panelDerecho.setBackground(Color.BLACK);
        this.panelInferior.setBackground(Color.YELLOW);
        this.panelCentral.setBackground(Color.RED);

        this.panelPrincipal.add(this.panelSuperior, BorderLayout.NORTH);
        this.panelPrincipal.add(this.panelIzquierdo, BorderLayout.WEST);
        this.panelPrincipal.add(this.panelDerecho, BorderLayout.EAST);
        this.panelPrincipal.add(this.panelCentral, BorderLayout.CENTER);
        this.panelPrincipal.add(this.panelInferior, BorderLayout.SOUTH);

        this.add(this.panelPrincipal);
        this.pack();
    }

    @Override
    public void setControlador(ActionListener controlador) {
        this.controlador = controlador;
    }

    @Override
    public ActionListener getControlador() {
        return this.controlador;
    }

    @Override
    public void setMetaDatos(HashMap hmMetaDatos) {
        if (hmMetaDatos != null) {
            this.hmMetaDatos.putAll(hmMetaDatos);
        } else {
            Core.mostrarMensajeError("Estructura de Datos Malformada para " + this.getClass().getSimpleName());
        }
    }

    @Override
    public HashMap getMetaDatos() {
        return this.hmMetaDatos;
    }

    @Override
    public abstract void recuperarDatosDeGUI();
    
    @Override
    public void setDatos(HashMap hmDatos) {
        if (hmDatos != null) {
            this.hmDatos.putAll(hmDatos);
        } else {
            Core.mostrarMensajeError("Estructura de Datos Malformada para " + this.getClass().getSimpleName());
        }
    }

    @Override
    public HashMap getDatos() {
        return this.hmDatos;
    }

    @Override
    public void setTituloVentana(String sTitulo) {
        this.setTitle(sTitulo);
    }

    @Override
    public void centrar() {
        this.setLocationRelativeTo(this.ventanaPrincipal);
    }

    @Override
    public void mostrar() {
        this.setVisible(true);
    }

    @Override
    public void ocultar() {
        this.setVisible(false);
    }

    @Override
    public void cerrar() {
        this.ocultar();
        this.dispose();
    }

}
