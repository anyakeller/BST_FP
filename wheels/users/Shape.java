package wheels.users;

/**
 * Generic superclass for all Wheels graphics. Subclasses must pass 
 * an instance of java.awt.Shape on super to define the Shape's
 * shape and define actualPaint to paint the shape.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */
public abstract class Shape extends wheels.etc.AbstractGraphic implements
    wheels.Rotatable, wheels.Colorable {

    /** The <code>wheels.etc.DrawingPanel</code> in which the shape will 
	be drawn. */
    protected wheels.etc.DrawingPanel _dp;

    /** The <code>java.awt.Shape</code> that defines the look of the shape */
    protected java.awt.Shape _shape;

    private java.awt.Color _c;
    private double _rot;

    public static final java.awt.Color DEFAULT_COLOR = java.awt.Color.red;

    /**
     * Constructs a shape in the wheels.users.Frame's DrawingPanel, with color
     * DEFAULT_COLOR.
     *
     * @param shape the shape that describes our Shape
     */
    public Shape (java.awt.Shape shape) {
	this (shape, wheels.users.Frame._dp);
	setColor(DEFAULT_COLOR);
    }

    /**
     * Constructs a Shape with the passed-in shape in the specified 
     * DrawingPanel. Shape defaults to rotation of 0 and the same color
     * as the DrawingPanel on which it is drawn.
     *
     * @param shape the shape that describes our Shape
     * @param dp the wheels.etc.DrawingPanel in which the shape will be 
     * drawn
     */
    public Shape (java.awt.Shape shape, wheels.etc.DrawingPanel dp) {

	super();
	_shape = shape;
	_dp = dp;
	_rot = 0;
	setColor(dp.getBackground());
    }

    /**
     * Hides the shape graphically. When this is done, the DrawingPanel no 
     * longer has a reference to the shape
     */
    public void hide () {
	_dp.removeGraphic(this);
	_dp.repaint(getBounds());
    }

    /**
     * Displays the shape graphically. DrawingPanel gets a reference to shape
     * when this is called. If the shape is already shown, the DrawingPanel 
     * will ignore the call to add it. (You don't have to worry about calling
     * show on a shown shape.
     */
    public void show () {
	_dp.addGraphic(this);
	_dp.repaint(getBounds());
    }

    /**
     * This is the methid the DrawingPanel will call when the shape needs to 
     * display itself. Subclasses should not override this method, as it
     * only sets the instance of <code>java.awt.Graphics2D</code> up to 
     * draw the shape at the proper rotation and calls 
     * <code>actualPaint(g)</code>.
     *
     * @param g the instance of <code>java.awt.Graphics2D</code> that should 
     * be used to paint the shape
     */

    public void paint (java.awt.Graphics2D g) {

	g.setPaint(_c);

	if (_rot != 0) {
	    java.awt.Rectangle rect = _shape.getBounds();
	    double centerX = rect.getCenterX();
	    double centerY = rect.getCenterY();
	    g.rotate(_rot, centerX, centerY);
	    this.actualPaint(g);
	    g.rotate(_rot * -1, centerX, centerY);
	}
	else
	    this.actualPaint(g);

    }

    /**
     * Subclasses must define this to do the actual painting of the shape. All
     * calls to <code>paint(java.awt.Graphics2D)</code> will forward to
     * this method.
     *
     * @param g the instance of <code>java.awt.Graphics2D</code> that should 
     * be used to paint the shape
     */
    public abstract void actualPaint (java.awt.Graphics2D g);

    /**
     * Sets the color of the shape. (go figure)
     */
    public void setColor (java.awt.Color c) {
	_c = c;
	_dp.repaint(getBounds());
    }

    /**
     * Returns the shape's color.
     */
    public java.awt.Color getColor () {
	return _c;
    }

    /**
     * Set the rotation of the shape.
     *
     * @param degrees the magnitude of the rotaiton in degrees
     */
    public void setRotation (int degrees) {
	java.awt.Rectangle oldBounds = getBounds();
	_rot = Math.toRadians(degrees);
	_dp.repaint(getBounds().union(oldBounds));
    }

    /**
     * Returns the Shape's current rotation in degrees.
     */
    public int getRotation () {
	return (int) Math.toDegrees(_rot);
    }

    /**
     * Normal users do not need to worry about this!
     *
     * Returns a <code>java.awt.Rectangle</code> that bounds the shape.
     */
    public java.awt.Rectangle getBounds () {
	java.awt.Rectangle r = _shape.getBounds();
	if (0 != _rot) {
	    double x = r.getCenterX();
	    double y = r.getCenterY();
	    java.awt.geom.AffineTransform trans =
		java.awt.geom.AffineTransform.getRotateInstance(_rot, x, y);
	    java.awt.Shape s = trans.createTransformedShape(_shape);
	    r = s.getBounds();
	}

	return r;
    }

    /**
     * Returns a boolean indicating whether or not the Shape contains the
     * passed-in point.
     */
    public boolean contains (java.awt.Point p) {
	if (0 != _rot) {
	    java.awt.Rectangle r = _shape.getBounds();
	    double x = r.getCenterX();
	    double y = r.getCenterY();
	    java.awt.geom.AffineTransform trans =
		java.awt.geom.AffineTransform.getRotateInstance(_rot, x, y);
	    java.awt.Shape s = trans.createTransformedShape(_shape);
	    return s.contains(p);
	}


	return _shape.contains(p);
    }

}

    
