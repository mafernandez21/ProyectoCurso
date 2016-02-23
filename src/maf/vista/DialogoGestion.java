/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class DialogoGestion extends Dialogo {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    PanelContenedorTexto txtFiltro;
    PanelContenedorGrilla panelGrilla;
    PanelBotones panelBotonFiltro;
    JPanel panel;
    PanelBotones panelBotonesComandos;
    FormularioPrincipal ventanaPrincipal;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public DialogoGestion(JFrame ventanaPrincipal, boolean modal) {
        super(ventanaPrincipal, modal);
        this.txtFiltro = new PanelContenedorTexto();
        this.panelGrilla = new PanelContenedorGrilla();
        this.panelBotonFiltro = new PanelBotones();
        this.panel=new JPanel();
        this.panelBotonesComandos = new PanelBotones();
    }
    //</editor-fold>

    @Override
    public void inicializar() {
        super.inicializar();

        this.txtFiltro.inicializar();
        this.txtFiltro.setValor("");
        this.txtFiltro.setEtiqueta("Filtro");
                
        String sFiltro[]=new String[1];
        sFiltro[0] = "Filtrar";
        this.panelBotonFiltro.inicializar(this.getControlador(), sFiltro, true);

        this.txtFiltro.add(this.panelBotonFiltro);

        this.getPanelCentral().add(this.txtFiltro);
        
        this.panelGrilla.inicializar();
        
        this.panelGrilla.add(this.txtFiltro);
        this.getPanelCentral().add(this.panelGrilla);

        String sBotones[] = new String[5];
        sBotones[0] = "Alta";
        sBotones[1] = "Baja";
        sBotones[2] = "Modificar";
        sBotones[3] = "Ver";
        sBotones[4] = "Volver";
        this.panelBotonesComandos.inicializar(this.getControlador(), sBotones, false);

        this.getPanelDerecho().add(this.panelBotonesComandos);

        this.pack();
    }

    //<editor-fold defaultstate="collapsed" desc="Método Main">
    public static void main(String[] args) {
        DialogoGestion d = new DialogoGestion(null, true);

        PanelContenedorGrilla pcg = new PanelContenedorGrilla();
        pcg.inicializar();
       // pcg.getTxtFiltro().setText("");
        pcg.setEtiqueta("Filtro");
        d.getPanelCentral().add(pcg);

        PanelBotones pcb = new PanelBotones();
        String botones[] = new String[5];
        botones[0] = "Alta";
        botones[1] = "Baja";
        botones[2] = "Modificar";
        botones[3] = "Ver";
        botones[4] = "Volver";
        pcb.inicializar(null, botones, true);

        d.getPanelInferior().add(pcb);

        d.inicializar();
        d.centrar();
        d.mostrar();
        d.pack();
    }
    //</editor-fold>

    @Override
    public void recuperarDatosDeCampos() {
        throw new UnsupportedOperationException("Set Up Datos en Dialogo Gestion"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ConstruirVista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
