package wheels.users;


/**
 * Draws an elliptical shape.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */

public class Ellipse extends RectangularShape {

    /**
     * Creates a ellipse with dimensions DEFAULT_WIDTH x DEFAULT_HEIGHT
     * and color DEFAULT_COLOR, located in the center of the wheels.users.Frame's
     * DrawingPanel.
     */
    public Ellipse () {
	super(new java.awt.geom.Ellipse2D.Double());
    }

    /**
     * Creates a ellipse with default dimension and location in the
     * wheels.users.Frame's DrawingPanel, but with the specified color.
     */
    public Ellipse (java.awt.Color c) {
	super(new java.awt.geom.Ellipse2D.Double(), c);
    }

    /**
     * Creates a ellipse with default dimension and color in the 
     * wheels.users.Frame's DrawingPanel, but at location (x, y).
     */
    public Ellipse (int x, int y) {
	super(new java.awt.geom.Ellipse2D.Double(), x, y);
    }

    /**
     * Creates a ellipse with default location, dimension, and color in the
     * wheels.users.Frame's DrawingPanel, but at rotaiton <code>degrees</code>
     */
    public Ellipse (int degrees) {
	super(new java.awt.geom.Ellipse2D.Double(), degrees);
    }

    /**
     * Creates a ellipse in the passed-in drawing panel. In this constructor,
     * dimension is 0x0, location (0, 0), and the color is the same as the 
     * DrawingPanel's by default.
     */
    public Ellipse (wheels.etc.DrawingPanel dp) {
	super(new java.awt.geom.Ellipse2D.Double(), dp);
    }

}
