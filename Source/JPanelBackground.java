/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class JPanelBackground extends JPanel {
    // Atributo que guardara la imagen de Background que le pasemos.
    private Image background;

    public JPanelBackground(String imagePath) {
            // Construimos la imagen y se la asignamos al atributo background.
            this.setOpaque(false);
            this.background = new ImageIcon("C:/Users/Cesar/Documents/NetBeansProjects/TeatroHanabi/src/Images/"+imagePath).getImage();
            repaint();
    }

    // Metodo que es llamado automaticamente por la maquina virtual Java cada vez que repinta
    public void paintComponent(Graphics g) {

            /* Obtenemos el tamaño del panel para hacer que se ajuste a este
            cada vez que redimensionemos la ventana y se lo pasamos al drawImage */
            int width = this.getPreferredSize().width;
            int height = this.getPreferredSize().height;

            // Mandamos que pinte la imagen en el panel
            if (this.background != null) {
                    g.drawImage(this.background, 0, 0, width, height, null);
            }

            super.paintComponent(g);
    }
}
