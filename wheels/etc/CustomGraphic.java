package wheels.etc;


/**
 * Interface for everything that is to be drawn on a DrawingPanel.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */

public interface CustomGraphic {

    /**
     * The real meat of the interface. The graphic must know how to draw
     * itself using the passed-in instance of <code>Graphics2D</code>. This
     * method will be called often (whenever the containing drawing panel is
     * repainted).
     *
     * @param g the <code>Graphics2D</code> you should use to paint your 
     * graphic
     */
    public void paint (java.awt.Graphics2D g);

    /**
     * If you are going to deal with mouse input, this function must return
     * true of the passed-in point is within the bounds of the graphic and 
     * false otherwise. If you are not using mouse interaction, you can
     * just return false.
     *
     * @param p the point 
     */
    public boolean contains (java.awt.Point p);

    /**
     * Called when a mouse button is pressed over the graphic.
     */
    public void mousePressed (java.awt.event.MouseEvent e);

    /**
     * Called when a mouse button is released over the graphic.
     */
    public void mouseReleased (java.awt.event.MouseEvent e);

    /**
     * Called when mouse is clicked over the graphic.
     */
    public void mouseClicked (java.awt.event.MouseEvent e);

    /**
     * Called when mouse button is pressed and dragged over graphic.
     */
    public void mouseDragged (java.awt.event.MouseEvent e);

}
