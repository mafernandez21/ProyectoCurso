/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.core;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu.Separator;
import maf.bdmem.BDEnMemoria;
import maf.controlador.ControladorGestion;
import maf.core.Core.Categoria;
import maf.core.Core.TipoFactura;
import maf.modelo.Cliente;
import maf.modelo.ObjetoBase;
import maf.modelo.interfaces.IControladorGestion;
import maf.vista.FormularioPrincipal;
import maf.vista.PanelBotones;
import maf.vista.PanelContenedorCombo;
import maf.vista.PanelContenedorGrilla;
import maf.vista.PanelContenedorTexto;

/**
 * Clase principal que inicia el Sistema de Factuaración
 *
 * @author Martín Alejandro Fernández
 * <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 * @version 1.0
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main.poblar();
        String sModulos[] = Main.getModulos();
        JFrame f = new FormularioPrincipal(Main.setUpControladores(sModulos));
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static String[] getModulos() {
        String archivoDeConfiguracion = "configuracionPorDefecto.properties";
        Cargador cargador = new Cargador();
        HashMap metaDatosModulos;

        cargador.cargarModulos(archivoDeConfiguracion);

        metaDatosModulos = (HashMap) cargador.getConfiguracion().get("MODULOS");

        String sModulos[] = String.valueOf(metaDatosModulos.get("MODULOS")).split(",");

        return sModulos;
    }

    public static ControladorGestion[] setUpControladores(String[] sModulos) {
        int numModulos = sModulos.length;

        ControladorGestion[] controladores = new ControladorGestion[numModulos];

        for (int i = 0; i < controladores.length; i++) {
            controladores[i] = new ControladorGestion();
            controladores[i].inicializar();
            controladores[i].setModelo(sModulos[i]);
            controladores[i].getMetaDatos();
            controladores[i].creaNuevoObjeto();
            controladores[i].inicializarObjeto();
        }
        return controladores;
    }

    public static void poblar() {

        for (int i = 0; i < 10; i++) {
            HashMap hmDatosCliente = new HashMap();
            hmDatosCliente.put("ID", i);
            hmDatosCliente.put("NOMBRE", "Nombre_" + i);
            hmDatosCliente.put("APELLIDO", "Apellido_" + i);
            hmDatosCliente.put("DNI", "1234567" + i);
            hmDatosCliente.put("DOMICILIO", "Domicilio_" + i);
            hmDatosCliente.put("LOCALIDAD", "Localidad_" + i);
            hmDatosCliente.put("CUIT", "20-1234567" + i + "-" + i);
            hmDatosCliente.put("CATEGORIA", Categoria.CONSUMIDOR_FINAL);
            Cliente c=new Cliente();
            c.inicializar();
            c.prepararMetaDatos();
            c.setDatos(hmDatosCliente);

            BDEnMemoria.conectar().insertar(c);
        }

    }

    public static void test3() {
        String archivoDeConfiguracion = "configuracionPorDefecto.properties";
        Cargador cargador = new Cargador();
        HashMap metaDatosModulos;

        cargador.cargarModulos(archivoDeConfiguracion);

        metaDatosModulos = (HashMap) cargador.getConfiguracion().get("MODULOS");

        String sModulos[] = String.valueOf(metaDatosModulos.get("MODULOS")).split(",");

        int numModulos = sModulos.length;

        IControladorGestion[] c = new ControladorGestion[numModulos];

        for (int i = 0; i < c.length; i++) {
            c[i] = new ControladorGestion();
            c[i].inicializar();
            c[i].setModelo(sModulos[i]);
            c[i].getMetaDatos();
            c[i].creaNuevoObjeto();
            c[i].inicializarObjeto();
        }

        HashMap hmDatosCliente = new HashMap();
        hmDatosCliente.put("ID", "009");
        hmDatosCliente.put("NOMBRE", "Ale");
        hmDatosCliente.put("APELLIDO", "Wolf");
        hmDatosCliente.put("DNI", "34132393");
        hmDatosCliente.put("DOMICILIO", "BS AS 671");
        hmDatosCliente.put("LOCALIDAD", "Tucuman");
        hmDatosCliente.put("CUIT", "20-03");
        hmDatosCliente.put("CATEGORIA", Categoria.CONSUMIDOR_FINAL);

        c[0].setDatos(hmDatosCliente);

        int numMenuItems = numModulos + 1;//1 boton por modulo + el cancelar

//
//        PanelContenedorCombo pac = new PanelContenedorCombo();
//        pac.inicializar();
//        pac.setValor(hmDatosCliente.get("CATEGORIA"));
//        vm.add(pac);
//
//        PanelContenedorCombo pac2 = new PanelContenedorCombo();
//        pac2.inicializar();
//        pac2.setValor(TipoFactura.A);
//        vm.add(pac2);
//
//        PanelContenedorTexto pat = new PanelContenedorTexto();
//        pat.inicializar();
//        pat.setEtiqueta("Etiqueta");
//        pat.setValor("Hola soy Texto");
//        vm.add(pat);
//
//        PanelContenedorGrilla pag = new PanelContenedorGrilla();
//        pag.inicializar();
//        pag.setEtiqueta("Etiqueta");
////        pag.setValor("Hola soy Texto");
//        vm.add(pag);
//        PanelBotones pcb = new PanelBotones();
//        
//        String s[]=new String[3];
//        s[0]="Aceptar";
//    s[1]="Cancelar";
//    s[2]="Volver";
//        pcb.setBotones(s);
//        pcb.inicializar();
//        vm.add(pcb);
        JMenuBar barraDeMenu = new JMenuBar();

        JMenu menuFacturacion = new JMenu();

        JMenuItem menuItems[] = new JMenuItem[numMenuItems];

        menuFacturacion.setText("Modulos de Gestión");

        for (int i = 0; i < numModulos; i++) {
            menuItems[i] = new JMenuItem(sModulos[i]);
            menuItems[i].setActionCommand("");
            menuItems[i].addActionListener((ActionListener) c[i]);
            menuFacturacion.add(menuItems[i]);
        }

        Separator sepVer = new Separator();
        JMenuItem itemModuloVer = new JMenuItem();
        itemModuloVer.setText("Ver");
        itemModuloVer.setActionCommand("menuVer");
        itemModuloVer.addActionListener(c[0]);
        menuFacturacion.add(sepVer);
        menuFacturacion.add(itemModuloVer);

        JMenuItem itemModuloSalir = new JMenuItem();
        itemModuloSalir.setText("Salir");
        itemModuloSalir.setActionCommand("menuSalir");
        itemModuloSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Cerrar
            }
        });
        Separator sepSalir = new Separator();
        menuFacturacion.add(sepSalir);
        menuFacturacion.add(itemModuloSalir);

        barraDeMenu.add(menuFacturacion);

        final JFrame vm = new JFrame();
        vm.setTitle(Core.NOMBRE_SISTEMA + " (ver." + Core.VERSION_SISTEMA + ")");
        vm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vm.setLayout(new FlowLayout());
        vm.setSize(300, 300);
        vm.setJMenuBar(barraDeMenu);

        vm.setSize(300, 300);
        vm.setLocationRelativeTo(null);
        vm.pack();
        vm.setVisible(true);
    }

    public static void test2() {
        String archivoDeConfiguracion = "configuracionPorDefecto.properties";
        Cargador cargador = new Cargador();
        HashMap metaDatos;

        cargador.cargarModulos(archivoDeConfiguracion);

        metaDatos = (HashMap) cargador.getConfiguracion().get("MODULOS");

        String sModulos[] = String.valueOf(metaDatos.get("MODULOS")).split(",");

        int numModulos = sModulos.length;

        IControladorGestion[] c = new ControladorGestion[numModulos];
        for (int i = 0; i < c.length; i++) {
            c[i] = new ControladorGestion();
            c[i].inicializar();
            c[i].setModelo(sModulos[i]);
            c[i].getMetaDatos();

            c[i].creaNuevoObjeto();
            c[i].inicializarObjeto();
        }

        HashMap hmDatos = new HashMap();
        hmDatos.put("ID", "009");
        hmDatos.put("NOMBRE", "Ale");
        hmDatos.put("APELLIDO", "Wolf");
        hmDatos.put("DNI", "34132393");
        hmDatos.put("DOMICILIO", "BS AS 671");
        hmDatos.put("LOCALIDAD", "Tucuman");
        hmDatos.put("CUIT", "20-03");
        hmDatos.put("CATEGORIA", Categoria.CONSUMIDOR_FINAL);

        c[0].setDatos(hmDatos);

        int numMenuItems = numModulos + 1;//1 boton por modulo + el cancelar

        final JFrame vm = new JFrame();
        vm.setTitle("Sistema de Facturación Reflex");
        vm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vm.setLayout(new FlowLayout());
        vm.setSize(300, 300);

        PanelContenedorCombo pac = new PanelContenedorCombo();
        pac.inicializar();
        pac.setValor(hmDatos.get("CATEGORIA"));
        vm.add(pac);

        PanelContenedorCombo pac2 = new PanelContenedorCombo();
        pac2.inicializar();
        pac2.setValor(TipoFactura.A);
        vm.add(pac2);

        PanelContenedorTexto pat = new PanelContenedorTexto();
        pat.inicializar();
        pat.setEtiqueta("Etiqueta");
        pat.setValor("Hola soy Texto");
        vm.add(pat);

        PanelContenedorGrilla pag = new PanelContenedorGrilla();
        pag.inicializar();
        pag.setEtiqueta("Etiqueta");
//        pag.setValor("Hola soy Texto");
        vm.add(pag);

        PanelBotones pcb = new PanelBotones();

        String s[] = new String[3];
        s[0] = "Aceptar";
        s[1] = "Cancelar";
        s[2] = "Volver";
        pcb.setBotones(s);
        pcb.inicializar(null, s, true);
        vm.add(pcb);

        JMenuBar barraDeMenu = new JMenuBar();

        JMenu menuFacturacion = new JMenu();

        JMenuItem menuItems[] = new JMenuItem[numMenuItems];

        menuFacturacion.setText("Facturacion");

        for (int i = 0; i < numModulos; i++) {
            menuItems[i] = new JMenuItem(sModulos[i]);
            menuItems[i].setActionCommand("");
            menuItems[i].addActionListener(null);
            menuFacturacion.add(menuItems[i]);
        }

        menuItems[menuItems.length - 1] = new JMenuItem("Salir");
        menuItems[menuItems.length - 1].setActionCommand("");
        menuItems[menuItems.length - 1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.dispose();
            }
        });

        menuFacturacion.add(menuItems[menuItems.length - 1]);

        barraDeMenu.add(menuFacturacion);

        vm.setJMenuBar(barraDeMenu);

        vm.setSize(300, 300);
        vm.setLocationRelativeTo(null);

//        Dialogo dg = new Dialogo(vm, true);
//        dg.inicializar();
//        dg.mostrar();
        vm.pack();
        vm.setVisible(true);

        //ControladorReflex cr = new ControladorReflex("modelo.clientes");
    }

    public static void test0() {
        ObjetoBase ob = new Cliente();
        ob.inicializar();
        ob.prepararMetaDatos();
        Core.mostrarMensajeConsola(ob.toString());
        HashMap hmDatos = new HashMap();
        hmDatos.put("ID", "009");
        hmDatos.put("NOMBRE", "Ale");
        hmDatos.put("APELLIDO", "Wolf");
        hmDatos.put("DNI", "34132393");
        hmDatos.put("DOMICILIO", "BS AS 671");
        hmDatos.put("LOCALIDAD", "Tucuman");
        hmDatos.put("CUIT", "20-03");
        hmDatos.put("CATEGORIA", Categoria.CONSUMIDOR_FINAL);

        ob.setDatos(hmDatos);
        Core.mostrarMensajeConsola(ob.toString());

        Core.mostrarMensajeConsola("Discrimina=" + (String.valueOf(((Cliente) ob).getCategoria().discrimina())));

    }

    public static void test1() {
        JFrame f = new JFrame(Core.NOMBRE_SISTEMA);

        JMenuBar BarraDeMenu = new JMenuBar();
        JMenu mnuModulos = new JMenu();

        mnuModulos.setText("Modulos");

        IControladorGestion[] c = new ControladorGestion[1];
        c[0] = new ControladorGestion();
        HashMap hmDatos = new HashMap();
        hmDatos.put("ID", "009");
        hmDatos.put("NOMBRE", "Ale");
        hmDatos.put("APELLIDO", "Wolf");
        hmDatos.put("DNI", "34132393");
        hmDatos.put("DOMICILIO", "BS AS 671");
        hmDatos.put("LOCALIDAD", "Tucuman");
        hmDatos.put("CUIT", "20-03");
        hmDatos.put("CATEGORIA", Categoria.CONSUMIDOR_FINAL);
        c[0].setModelo("maf.modelo.Cliente");
        c[0].creaNuevoObjeto();
        c[0].inicializarObjeto();

//        c[0].setDatosModelo();
        //c[1] = new ControladorReflex("modelo.Categoria");
        //for (ControladorReflex ctrl : c) {
        //ctrl.getMiObjetoReflex().inicializar();
//            ctrl.inicializarMetaDatos();
        //}
        JMenuItem[] itemModulo = new JMenuItem[c.length + 2];

        int i = 0;
        for (IControladorGestion ctrl : c) {
            itemModulo[i] = new JMenuItem();
            itemModulo[i].setText(ctrl.getNombre());
            itemModulo[i].setActionCommand("menu" + ctrl.getNombre());
            itemModulo[i].addActionListener(ctrl);
            i++;
        }
        Separator sep = new Separator();
        itemModulo[i] = new JMenuItem();
        itemModulo[i].setText("Ver");
        itemModulo[i].setActionCommand("menuVer");
        itemModulo[i].addActionListener(c[0]);

        Separator sep2 = new Separator();
        itemModulo[i + 1] = new JMenuItem();
        itemModulo[i + 1].setText("Salir");
        itemModulo[i + 1].setActionCommand("menuSalir");
        itemModulo[i + 1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        for (int j = 0; j < itemModulo.length; j++) {
            if (j == itemModulo.length - 2) {
                mnuModulos.add(sep);
                mnuModulos.add(itemModulo[j]);
                mnuModulos.add(sep2);
                mnuModulos.add(itemModulo[j + 1]);
            } else {
                mnuModulos.add(itemModulo[j]);
            }

        }
        BarraDeMenu.add(mnuModulos);

        f.setJMenuBar(BarraDeMenu);
        f.setLocationRelativeTo(null);
        f.setSize(300, 300);
        f.setVisible(true);
    }
}
