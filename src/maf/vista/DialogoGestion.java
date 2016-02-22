/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class DialogoGestion extends Dialogo{

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    PanelContenedorGrilla panelGrilla;
    PanelContenedorBotones panelDeBotones;
    FormularioPrincipal ventanaPrincipal;
    ActionListener controlador;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public DialogoGestion(JFrame ventanaPrincipal, boolean modal, ActionListener controlador) {
        super(ventanaPrincipal, modal);
        this.controlador=controlador;
        this.panelGrilla=new PanelContenedorGrilla();
        this.panelDeBotones=new PanelContenedorBotones();
    }
    //</editor-fold>
    
    @Override
    public void inicializar(){
        super.inicializar();
        
        this.panelGrilla.inicializar();
        this.panelGrilla.getTxtFiltro().setText("");
        this.panelGrilla.setEtiqueta("Filtro");
        this.getPanelCentral().add(this.panelGrilla);
        
        String botones[]=new String[5];
        botones[0]="Alta";
        botones[1]="Baja";
        botones[2]="Modificar";
        botones[3]="Ver";
        botones[4]="Volver";
        this.panelDeBotones.inicializar(this.controlador,botones, false);
        
        this.getPanelDerecho().add(this.panelDeBotones);

        this.pack();
    }
    
    
    
    //<editor-fold defaultstate="collapsed" desc="Método Main">
    public static void main(String[] args) {
        DialogoGestion d=new DialogoGestion(null,true,null);
        
        
        PanelContenedorGrilla pcg=new PanelContenedorGrilla();
        pcg.inicializar();
        pcg.getTxtFiltro().setText("");
        pcg.setEtiqueta("Filtro");
        d.getPanelCentral().add(pcg);
        
        
        PanelContenedorBotones pcb=new PanelContenedorBotones();
        String botones[]=new String[5];
        botones[0]="Alta";
        botones[1]="Baja";
        botones[2]="Modificar";
        botones[3]="Ver";
        botones[4]="Volver";
        pcb.inicializar(null,botones, true);
        
        d.getPanelInferior().add(pcb);
        

        d.inicializar();
        d.centrar();
        d.mostrar();
        d.pack();
    }
    //</editor-fold>
    
    

}
