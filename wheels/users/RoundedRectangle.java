package wheels.users;


/**
 * Subclass of RectangularShape that  draws a rectangle with rounded corners.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@.cs.brown.edu">jgoodwin</a>)
 */

public class RoundedRectangle extends RectangularShape {

    /**
     * Creates a shape with dimensions DEFAULT_WIDTH x DEFAULT_HEIGHT
     * and color DEFAULT_COLOR, located in the center of the wheels.users.Frame's
     * DrawingPanel.
     */
    public RoundedRectangle () {
	super(new java.awt.geom.RoundRectangle2D.Double());
    }

    /**
     * Creates a shape with default dimension and location in the
     * wheels.users.Frame's DrawingPanel, but with the specified color.
     */
    public RoundedRectangle (java.awt.Color c) {
	super(new java.awt.geom.RoundRectangle2D.Double(), c);
    }

    /**
     * Creates a shape with default dimension and color in the 
     * wheels.users.Frame's DrawingPanel, but at location (x, y).
     */
    public RoundedRectangle (int x, int y) {
	super(new java.awt.geom.RoundRectangle2D.Double(), x, y);
    }

    /**
     * Creates a shape with default location, dimension, and color in the
     * wheels.users.Frame's DrawingPanel, but at rotaiton <code>degrees</code>
     */
    public RoundedRectangle (int degrees) {
	super(new java.awt.geom.RoundRectangle2D.Double(), degrees);
    }

    /**
     * Creates a shape in the passed-in drawing panel. In this constructor,
     * dimension is 0x0, location (0, 0), and the color is the same as the 
     * DrawingPanel's by default.
     */
    public RoundedRectangle (wheels.etc.DrawingPanel dp) {
	super(new java.awt.geom.RoundRectangle2D.Double(), dp);
    }

    /**
     * Sets the shape's size. Overridden to take the RoundedRectangle's
     * round corners. Since <code>setSize(int, int)</code> forwards to
     * <code>setSize(java.awt.Dimension)</code>, both methods will work 
     * properly.
     */
    public void setSize (java.awt.Dimension d) {
	java.awt.Rectangle oldBounds = getBounds();

	super.setSize(d);

	int arcw = d.width / 2;
	int arch = d.height / 2;

	java.awt.Point p = getLocation();
	
	((java.awt.geom.RoundRectangle2D.Double)_shape).setRoundRect(p.x, p.y,
								     d.width, 
								     d.height,
								     arcw, 
								     arch);

	_dp.repaint(oldBounds.union(getBounds()));

    }

}
