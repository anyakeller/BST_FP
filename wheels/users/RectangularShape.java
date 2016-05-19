package wheels.users;


/**
 * Superclass for all Shapes that can be described in terms of rectangular
 * dimensions (ellipse, rectangle, etc.).
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */

public abstract class RectangularShape extends Shape implements wheels.Locatable,
    wheels.Sizeable, wheels.AdvancedColorable {

    private java.awt.Dimension _dim;
    private java.awt.Point _loc;
    private java.awt.Color _fillColor, _frameColor;
    private int _stroke = 1;

    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;

    /**
     * The default constructor will draw the shape in the center of the
     * wheels.users.Frame's DrawingPanel, with dimension DEFAULT_WIDTH x 
     * DEFAULT_HEIGHT and color DEFAULT_COLOR.
     */
    public RectangularShape (java.awt.geom.RectangularShape s) {
	super(s);
	_dim = new java.awt.Dimension();
	_loc = new java.awt.Point();
	setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

	//if the shape is not bigger than its drawing panel, put it
	//in the middle of the panel
	java.awt.Dimension dpDim = _dp.getPreferredSize();
	if (dpDim.width > DEFAULT_WIDTH && dpDim.height > DEFAULT_HEIGHT) {
	    int x, y;

	    x = (dpDim.width / 2) - (DEFAULT_WIDTH / 2);
	    y = (dpDim.height / 2) - (DEFAULT_HEIGHT / 2);

	    setLocation(x, y);

	}
	//otherwise, put it at (0, 0)
	else
	    setLocation(0, 0);

	show();
    }

    /**
     * Draws new shape in the wheels.users.Frame's DrawingPanel with default
     * dimensions and location, and color set to the passed-in color.
     */
    public RectangularShape (java.awt.geom.RectangularShape s, 
			     java.awt.Color c) {
	this(s);
	setColor(c);
    }

    /**
     * Draws new shape in the wheels.users.Frame's DrawingPanel with default
     * color and location, and location set to the passed-in values.
     */
    public RectangularShape (java.awt.geom.RectangularShape s, int x, int y) {
	this(s);
	setLocation(x, y);
    }

    /**
     * Draws new shape with default location, color, and dimension in the 
     * wheels.users.DrawingPanel, at a rotation of degrees.
     */
    public RectangularShape (java.awt.geom.RectangularShape s, int degrees) {
	this(s);
	setRotation(degrees);
    }

    /**
     * Constructs a rectagular shape with the passed-in properties.
     * In this constructor, size is set to 0x0, location to (0, 0), and
     * color to the same color as the DrawingPanel.
     */
    public RectangularShape (java.awt.geom.RectangularShape s, 
			     wheels.etc.DrawingPanel dp) {
	super (s, dp);

	_dim = new java.awt.Dimension();
	_loc = new java.awt.Point();

	setSize(0, 0);
	setLocation(0, 0);

	//visible by default
	show();
    }

    /**
     * Overrides actualPaint to paint a frame and fill with the specified
     * colors. If either color ahs been set to null, that part is not 
     * drawn.
     */
    public void actualPaint (java.awt.Graphics2D g) {
	if (null != _fillColor) {
	    g.setPaint(_fillColor);
	    g.fill(_shape);
	}	

	if (null != _frameColor) {
	    java.awt.Stroke oldStroke = g.getStroke();
	    g.setStroke(new java.awt.BasicStroke(_stroke));
	    g.setPaint(_frameColor);
	    g.draw(_shape);
	    g.setStroke(oldStroke);
	}
    }

    /**
     * Overridden to set the color of the shape's frame and fill.
     */
    public void setColor (java.awt.Color c) {
	_frameColor = c;
	_fillColor = c;
	super.setColor(c);
    }

    /**
     * Sets the color of the shape's frame. If c is null, won't draw the frame.
     */
    public void setFrameColor (java.awt.Color c) {
	_frameColor = c;
	_dp.repaint(getBounds());
    }

    /**
     * Returns the color of the shape's frame.
     */
    public java.awt.Color getFrameColor () {
	return _frameColor;
    }

    /**
     * Sets the color of the shape's fill. If c is null, fill is not drawn
     * (transparent).
     */
    public void setFillColor (java.awt.Color c) {
	_fillColor = c;
	_dp.repaint(getBounds());
    }

    /**
     * Returns the shape's fill color.
     */
    public java.awt.Color getFillColor () {
	return _fillColor;
    }
    
    /**
     * Sets the thickness of the frame's stroke. Must be positive. Ignores
     * calls with negative arguments.
     */
    public void setFrameThickness (int thickness) {
	if (thickness >= 0) {
	    java.awt.Rectangle oldBounds = getBounds();
	    _stroke = thickness;
	    _dp.repaint(oldBounds.union(getBounds()));
	}
    }

    /**
     * Returns the thicknes of the frame's stroke.
     */
    public int getFrameThickness () {
	return _stroke;
    }
    
    /**
     * Set the location of the upper-left corner of the shape's bounding box.
     */
    public void setLocation (int x, int y) {
	setLocation(new java.awt.Point(x, y));
    }
    
    /**
     * Set the location of the upper-left corner of the shape's bounding box.
     */
    public void setLocation (java.awt.Point p) {
	java.awt.Rectangle oldBounds = getBounds();
	_loc = p;
	((java.awt.geom.RectangularShape)_shape).setFrame(_loc, _dim);
	_dp.repaint(oldBounds.union(getBounds()));
    }

    /**
     * Returns the shape's location.
     */
    public java.awt.Point getLocation () {
	if (null != _loc) 
	    return _loc;
	else
	    return new java.awt.Point(0, 0);
    }

    /**
     * Returns the x value of the shape's location.
     */
    public int getXLocation () {
	return getLocation().x;
    }

    /**
     * Returns the y value of the shape's location.
     */
    public int getYLocation () {
	return getLocation().y;
    }

    /**
     * Set the dimension of the shape.
     */
    public void setSize (int width, int height) {
	setSize(new java.awt.Dimension(width, height));
    }

    /**
     * Set the dimension of the shape.
     */
    public void setSize (java.awt.Dimension d) {
	java.awt.Rectangle oldBounds = getBounds();
	_dim = d;
	((java.awt.geom.RectangularShape)_shape).setFrame(_loc, _dim);
	_dp.repaint(oldBounds.union(getBounds()));

    }

    /**
     * Returns the shape's dimensions.
     */
    public java.awt.Dimension getSize () {
	if (null != _dim)
	    return _dim;
	else
	    return new java.awt.Dimension(0, 0);
    }

    /**
     * Returns the shape's width.
     */
    public int getWidth () {
	return getSize().width;
    }

    /**
     * Returns the shape's height.
     */
    public int getHeight () {
	return getSize().height;
    }

    /**
     * Overrides the getBounds method of Shape to account for the shape's 
     * frame.
     */
    public java.awt.Rectangle getBounds () {
	java.awt.Rectangle b = super.getBounds();
	if (_stroke > 0)
	    b.grow(2*_stroke, 2*_stroke);
	else
	    b.grow(2, 2);
	return b;
    }

}
