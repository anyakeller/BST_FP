package wheels.etc;

/**
 * Abstract class implementing CustomGraphic interface. For convenience, 
 * defines all the mouse interraction methods to do nothing so they 
 * don't all have to be defined every time a class implementing CustomGraphic
 * is created.
 *
 * All subclasses must implement <code>paint(java.awt.Graphics2D)</code> and
 * <code>contains(java.awt.Point)</code>!
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 *
 */

abstract public class AbstractGraphic implements CustomGraphic {

    /**
     * Called when a mouse button is pressed over the graphic.
     */
    public void mousePressed (java.awt.event.MouseEvent e) {}

    /**
     * Called when a mouse button is released over the graphic.
     */
    public void mouseReleased (java.awt.event.MouseEvent e){}

    /**
     * Called when mouse is clicked over the graphic.
     */
    public void mouseClicked (java.awt.event.MouseEvent e){}

    /**
     * Called when mouse button is pressed and dragged over graphic.
     */
    public void mouseDragged (java.awt.event.MouseEvent e){}

}
