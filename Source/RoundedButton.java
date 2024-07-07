/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;
import java.awt.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Cesar
 */
public class RoundedButton extends JButton {
    private Color shadowColor = getBackground().darker().darker();
    private Dimension arcs = new Dimension(20, 20);
    private int strokeSize = 1;
    private int shadowGap = 3;
    private int shadowOffset = 3;
    private int shadowAlpha = 150;
    private boolean pressed = false;
    private String label = "";
    private int width = 100;
    private int height = 100;
    private Image background;
    
    /**
     * Constructs a RoundedButton with no label.
     */
    public RoundedButton() {
        this("");
    }
    
    /**
     * Constructs a RoundedButton with no label.
     */
    public RoundedButton(String imagePath) {
        // Construimos la imagen y se la asignamos al atributo background.
        this.setOpaque(false);
        this.background = new ImageIcon("C:/Users/Cesar/Documents/NetBeansProjects/TeatroHanabi/src/Images/"+imagePath).getImage();
        repaint();
    }
    
    /**
     * Constructs a RoundedButton with no label.
     * @param width
     * @param height
     */
    public RoundedButton(int width,int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * gets the label
     *
     * @see setLabel
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * sets the label
     *
     * @see getLabel
     */
    @Override
    public void setLabel(String label) {
        this.label = label;
        invalidate();
        repaint();
    }

    /**
     * paints the RoundedButton
     */
    @Override
    public void paint(Graphics g) {
        // paint the interior of the button
        if (pressed) {
            g.setColor(new Color(187,187,187,200));
            g.translate(2, 2);
            this.width = getWidth() - 2;
            this.height = getHeight() - 2;
        } else {
            g.setColor(new Color(187,187,187,0));
            this.width = getWidth();
            this.height = getHeight();
        }
        
        g.fillRoundRect(
            shadowOffset,// X position
            shadowOffset,// Y position
            this.width - strokeSize - shadowOffset, // width
            this.height - strokeSize - shadowOffset, // height
            arcs.width, arcs.height);// arc Dimension
        
        //Draws the rounded opaque panel with borders.
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, this.width - shadowGap, this.height - shadowGap, arcs.width, arcs.height);
        g.setColor(new Color(187,187,187));
        g.drawRoundRect(0, 0, this.width - shadowGap, this.height - shadowGap, arcs.width, arcs.height);
        
        if (this.background != null) {
            g.drawImage(this.background, 0, 0, this.width, this.height, null);
        }
         
        // draw the label centered in the button
        Font f = getFont();
        if (f != null) {
            FontMetrics fm = getFontMetrics(getFont());
            g.setColor(getForeground());
            g.drawString(label, getWidth() / 2 - fm.stringWidth(label) / 2, getHeight() / 2 + fm.getMaxDescent());
        }
    }
    
    /**
     * The preferred size of the button.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }

    /**
     * The minimum size of the button.
     */
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(100, 100);
    }

    /**
     * Adds the specified action listener to receive action events from this
     * button.
     *
     * @param listener the action listener
     */
    @Override
    public void addActionListener(ActionListener listener) {
        actionListener = AWTEventMulticaster.add(actionListener, listener);
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }

    /**
     * Removes the specified action listener so it no longer receives action
     * events from this button.
     *
     * @param listener the action listener
     */
    @Override
    public void removeActionListener(ActionListener listener) {
        actionListener = AWTEventMulticaster.remove(actionListener, listener);
    }

    /**
     * Determine if click was inside round button.
     */
    @Override
    public boolean contains(int x, int y) {
        int mx = getSize().width / 2;
        int my = getSize().height / 2;
        return (((mx - x) * (mx - x) + (my - y) * (my - y)) <= mx * mx);
    }

    /**
     * Paints the button and distribute an action event to all listeners.
     */
    @Override
    public void processMouseEvent(MouseEvent e) {
        Graphics g;
        switch (e.getID()) {
            case MouseEvent.MOUSE_PRESSED:
                // render myself inverted....
                pressed = true;

                // Repaint might flicker a bit. To avoid this, you can use
                // double buffering (see the Gauge example).
                repaint();
                break;
            case MouseEvent.MOUSE_RELEASED:
                if (actionListener != null) {
                    actionListener.actionPerformed(new ActionEvent(
                            this, ActionEvent.ACTION_PERFORMED, label));
                }
                // render myself normal again
                if (pressed == true) {
                    pressed = false;

                    // Repaint might flicker a bit. To avoid this, you can use
                    // double buffering (see the Gauge example).
                    repaint();
                }
                break;
            case MouseEvent.MOUSE_ENTERED:

                break;
            case MouseEvent.MOUSE_EXITED:
                if (pressed == true) {
                    // Cancel! Don't send action event.
                    pressed = false;

                    // Repaint might flicker a bit. To avoid this, you can use
                    // double buffering (see the Gauge example).
                    repaint();

                    // Note: for a more complete button implementation,
                    // you wouldn't want to cancel at this point, but
                    // rather detect when the mouse re-entered, and
                    // re-highlight the button. There are a few state
                    // issues that that you need to handle, which we leave
                    // this an an excercise for the reader (I always
                    // wanted to say that!)
                }
                break;
        }
        super.processMouseEvent(e);
    }
    
    public Icon setIcon(String url, JButton boton){
        ImageIcon icon = new ImageIcon(getClass().getResource(url));
        int width = boton.getPreferredSize().width;
        int height = boton.getPreferredSize().height;
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        return icono;
    }
    
    public Icon setIconPress(String url, JButton boton){
        ImageIcon icon = new ImageIcon(getClass().getResource(url));
        int width = boton.getPreferredSize().width - 2;
        int height = boton.getPreferredSize().height - 2;
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        return icono;
    }
}
