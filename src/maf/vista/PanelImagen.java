/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.vista;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Descripcion ...
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class PanelImagen extends JPanel {

    private String sImagen;

    public PanelImagen() {
        this.sImagen = "/maf/img/max.jpg";
    }

    public PanelImagen(String sImagen) {
        this.sImagen = sImagen;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension tam = this.getSize();
        ImageIcon imgFondo = new ImageIcon(this.getClass().getResource(this.sImagen));
        g.drawImage(imgFondo.getImage(), 0, 0, tam.width, tam.height, null);
        this.setOpaque(false);
        super.paintComponent(g);
    }

}
