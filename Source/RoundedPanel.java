/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author Cesar
 */
public class RoundedPanel extends JPanel {
    private Color backgroundColor;
    private Color backgroundColor_2;
    private int cornerRadius = 15;
    private Image background;


    public RoundedPanel(LayoutManager layout, int radius) {
        super(layout);
        cornerRadius = radius;
    }

    public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    public RoundedPanel(int radius) {
        super();
        cornerRadius = radius;
    }

    public RoundedPanel(int radius, Color bgColor) {
        super();
        cornerRadius = radius;
        backgroundColor = bgColor;
    }
    
    /**
     * Constructs a RoundedButton with no label.
     */
    public RoundedPanel(int radius, Color bgColor, String imagePath) {
        // Construimos la imagen y se la asignamos al atributo background.
        this.setOpaque(false);
        this.cornerRadius = radius;
        this.backgroundColor = bgColor;
        this.background = new ImageIcon("C:/Users/Cesar/Documents/NetBeansProjects/TeatroHanabi/src/Images/"+imagePath).getImage();
        repaint();
    }

    public RoundedPanel(int radius, Color bgColor, Color bgColor_2) {
        super();
        cornerRadius = radius;
        backgroundColor = bgColor;
        backgroundColor_2 = bgColor_2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draws the rounded panel with borders.
        if (backgroundColor != null && backgroundColor_2 != null) {
            GradientPaint gp = new GradientPaint(0,0,backgroundColor_2,300,height,backgroundColor);
            graphics.setPaint(gp);
        }else if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
        } else {
            graphics.setColor(getBackground());
        }
        
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
        graphics.setColor(new Color(0.0f,0.0f,0.0f,0.0f)); // bordes transparentes
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
        
        if (this.background != null) {
            graphics.drawImage(this.background, 0, 0, width, height, null);
        }
    }
}
