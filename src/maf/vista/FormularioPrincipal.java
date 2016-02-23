/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu.Separator;
import maf.controlador.ControladorGestion;
import maf.controlador.ControladorMenu;
import maf.core.Core;
import maf.modelo.interfaces.IControladorGestion;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class FormularioPrincipal extends JFrame {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private JMenuBar barraDeMenu;
    private ActionListener controladorMenu;
    private IControladorGestion gestores[];
    private final PanelImagen imagenFondo;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public FormularioPrincipal(ControladorGestion gestores[]) {
        this.gestores = gestores;
        this.imagenFondo = new PanelImagen();
        this.controladorMenu = new ControladorMenu(this,gestores);
        this.inicializar();
    }

    private void inicializar() {
        
        this.barraDeMenu = new JMenuBar();

        this.setTitle(Core.NOMBRE_SISTEMA + " (ver." + Core.VERSION_SISTEMA + ")");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setJMenuBar(this.inicializarMenuModulos(this.gestores));

        //Inicializa el JFrame Maximizado y con una imagen de fondo
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());
        this.add(this.imagenFondo, BorderLayout.CENTER);

        this.pack();
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    //</editor-fold>
    
    JMenuBar inicializarMenuModulos(IControladorGestion gestores[]) {
        JMenu mnuFacturacion = new JMenu();
        mnuFacturacion.setText("Facturación");

        JMenuItem mnuFacturacionNueva = new JMenuItem();
        mnuFacturacionNueva.setText("Nueva Factura");
        mnuFacturacionNueva.setActionCommand("Nueva_Factura");
        mnuFacturacionNueva.addActionListener(this.controladorMenu);

        JMenuItem mnuFacturacionListar = new JMenuItem();
        mnuFacturacionListar.setText("Listar Facturas");
        mnuFacturacionListar.setActionCommand("Listar_Facturas");
        mnuFacturacionListar.addActionListener(this.controladorMenu);

        JMenuItem mnuFacturacionImprimir = new JMenuItem();
        mnuFacturacionImprimir.setText("Imprimir");
        mnuFacturacionImprimir.setActionCommand("Imprimir");
        mnuFacturacionImprimir.addActionListener(this.controladorMenu);

        JMenuItem mnuFacturacionSalir = new JMenuItem();
        mnuFacturacionSalir.setText("Salir");
        mnuFacturacionSalir.setActionCommand("salir");
        mnuFacturacionSalir.addActionListener(this.controladorMenu);

        Separator sepImpresion = new Separator();
        Separator sepSalir = new Separator();

        mnuFacturacion.add(mnuFacturacionNueva);
        mnuFacturacion.add(mnuFacturacionListar);
        mnuFacturacion.add(sepImpresion);
        mnuFacturacion.add(mnuFacturacionImprimir);
        mnuFacturacion.add(sepSalir);
        mnuFacturacion.add(mnuFacturacionSalir);

        barraDeMenu.add(mnuFacturacion);

        JMenu mnuGestion = new JMenu();
        mnuGestion.setText("Gestión");

        JMenuItem mnuItemGestion[] = new JMenuItem[gestores.length];

        for (int i = 0; i < gestores.length; i++) {
            mnuItemGestion[i] = new JMenuItem();
            mnuItemGestion[i].setText(gestores[i].getNombre());
            mnuItemGestion[i].setActionCommand(gestores[i].getNombre());
            mnuItemGestion[i].addActionListener(this.controladorMenu);
            //mnuItemGestion[i].addActionListener((ActionListener) gestores[i]);
            mnuGestion.add(mnuItemGestion[i]);
        }

        barraDeMenu.add(mnuGestion);
        JMenuItem mnuAyuda = new JMenu();
        mnuAyuda.setText("Ayuda");

        JMenuItem mnuAyudaContenido = new JMenuItem();
        mnuAyudaContenido.setText("Contenido");
        mnuAyudaContenido.setActionCommand("Contenido");
        mnuAyudaContenido.addActionListener(this.controladorMenu);

        JMenuItem mnuAyudaAcerca = new JMenuItem();
        mnuAyudaAcerca.setText("Acerca de...");
        mnuAyudaAcerca.setActionCommand("Acerca de...");
        mnuAyudaAcerca.addActionListener(this.controladorMenu);

        Separator sepAyuda = new Separator();
        
        mnuAyuda.add(mnuAyudaContenido);
        mnuAyuda.add(sepAyuda);
        mnuAyuda.add(mnuAyudaAcerca);

        barraDeMenu.add(mnuAyuda);
        return barraDeMenu;
    }
}
