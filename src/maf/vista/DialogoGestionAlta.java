/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.GridLayout;
import javax.swing.JFrame;
import maf.core.Core.Categoria;
import maf.core.Core.TipoFactura;
import maf.core.Core.TipoIVA;
import maf.modelo.interfaces.IVistaReflex;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class DialogoGestionAlta extends Dialogo implements IVistaReflex {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    PanelContenedor[] paneles;
    PanelBotones panelDeBotones;
    FormularioPrincipal ventanaPrincipal;
//    ActionListener controlador;
//    HashMap hmDatos;
//    HashMap hmMetaDatos;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public DialogoGestionAlta(JFrame ventanaPrincipal, boolean modal) {
        super(ventanaPrincipal, modal);
        this.panelDeBotones = new PanelBotones();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    public void inicializar() {
        super.inicializar();
        //this.ConstruirVista();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método Main">
    public static void main(String[] args) {
        //TODO-Aquí va la lógica para iniciar la clase
    }
    //</editor-fold>

    @Override
    public void ConstruirVista() {
        String sEtiqueta[] = String.valueOf(this.getMetaDatos().get("ATRIBUTOS")).split(",");
        String sTipoAtributo[] = String.valueOf(this.getMetaDatos().get("TIPOATRIBUTOS")).split(",");
        this.setListaDeAtributos(new PanelContenedor[sTipoAtributo.length]);

        for (int i = 0; i < sTipoAtributo.length; i++) {
            switch (sTipoAtributo[i]) {
                case "java.lang.String":
                case "int":
                case "double":
                    this.getListaDeAtributos()[i] = new PanelContenedorTexto();
                    this.getListaDeAtributos()[i].inicializar();
                    this.getListaDeAtributos()[i].setEtiqueta(sEtiqueta[i]);
                    String sValor = String.valueOf(this.getDatos().get(sEtiqueta[i].toUpperCase()));
                    if (sValor != null && !this.getDatos().isEmpty()) {
                        this.getListaDeAtributos()[i].setValor(sValor);
                    } else {
                        this.getListaDeAtributos()[i].setValor("");
                    }
                    break;
                case "maf.core.Core$Categoria":
                    this.getListaDeAtributos()[i] = new PanelContenedorCombo();
                    this.getListaDeAtributos()[i].inicializar();
                    this.getListaDeAtributos()[i].setEtiqueta(sEtiqueta[i].toUpperCase());
                    this.getListaDeAtributos()[i].setValor(Categoria.CONSUMIDOR_FINAL);
                    break;
                case "maf.core.Core$TipoIVA":
                    this.getListaDeAtributos()[i] = new PanelContenedorCombo();
                    this.getListaDeAtributos()[i].inicializar();
                    this.getListaDeAtributos()[i].setEtiqueta(sEtiqueta[i].toUpperCase());
                    this.getListaDeAtributos()[i].setValor(TipoIVA._21);
                    break;
                case "maf.core.Core$TipoFactura":
                    this.getListaDeAtributos()[i] = new PanelContenedorCombo();
                    this.getListaDeAtributos()[i].inicializar();
                    this.getListaDeAtributos()[i].setEtiqueta(sEtiqueta[i].toUpperCase());
                    this.getListaDeAtributos()[i].setValor(TipoFactura.B);
                    break;
            }
        }

        this.getPanelCentral().setLayout(new GridLayout(this.getListaDeAtributos().length, 1));

        for (PanelContenedor p : this.getListaDeAtributos()) {
            this.getPanelCentral().add(p);
        }

        String sBotones[] = new String[2];
        sBotones[0] = "Guardar";
        sBotones[1] = "Volver";

        this.panelDeBotones.inicializar(this.getControlador(), sBotones, true);

        this.getPanelInferior().add(this.panelDeBotones);

        this.pack();
    }

    @Override
    public void setListaDeAtributos(PanelContenedor[] paneles) {
        this.paneles = paneles;
    }

    @Override
    public PanelContenedor[] getListaDeAtributos() {
        return this.paneles;
    }

    @Override
    public void recuperarDatosDeModelo() {
        for (PanelContenedor p : this.getListaDeAtributos()) {
            String key = p.getEtiqueta().toUpperCase();
            Object o = p.getValor();
            if (!String.valueOf(o).equals("")) {
                this.getDatos().put(key, o);
            }
        }
    }
}
