package wheels.users;


/**
 * Subclass of RectangularShape that draws a rectangle.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */

public class Rectangle extends RectangularShape {

    /**
     * Creates a rectangle with dimensions DEFAULT_WIDTH x DEFAULT_HEIGHT
     * and color DEFAULT_COLOR, located in the center of the wheels.users.Frame's
     * DrawingPanel.
     */
    public Rectangle () {
	super(new java.awt.Rectangle());
    }

    /**
     * Creates a rectangle with default dimension and location in the
     * wheels.users.Frame's DrawingPanel, but with the specified color.
     */
    public Rectangle (java.awt.Color c) {
	super(new java.awt.Rectangle(), c);
    }

    /**
     * Creates a rectangle with default dimension and color in the 
     * wheels.users.Frame's DrawingPanel, but at location (x, y).
     */
    public Rectangle (int x, int y) {
	super(new java.awt.Rectangle(), x, y);
    }

    /**
     * Creates a rectangle with default location, dimension, and color in the
     * wheels.users.Frame's DrawingPanel, but at rotaiton <code>degrees</code>
     */
    public Rectangle (int degrees) {
	super(new java.awt.Rectangle(), degrees);
    }

    /**
     * Creates a rectangle in the passed-in drawing panel. In this constructor,
     * dimension is 0x0, location (0, 0), and the color is the same as the 
     * DrawingPanel's by default.
     */
    public Rectangle (wheels.etc.DrawingPanel dp) {
	super(new java.awt.Rectangle(), dp);
    }

}
