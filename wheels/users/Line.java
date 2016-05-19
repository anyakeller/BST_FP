package wheels.users;


/**
 * Class the models a 2D line. Has methods allowing you to adjust the line's
 * points, thickness, color, etc.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */

public class Line extends Shape {

    private java.awt.Point _p1, _p2;
    private int _thickness = 1;

    public static final int DEFAULT_LENGTH = 50;

    /**
     * Creates a Line with default endpoints in the wheels.users.Frame's
     * DrawingPanel.
     */
    public Line () {
	super(new java.awt.geom.Line2D.Double());

	// put the line in the center of the DP, of possible
	if (_dp.getSize().width > DEFAULT_LENGTH) {
	    int y = _dp.getSize().height / 2;
	    int x1 = (_dp.getSize().width / 2) - (DEFAULT_LENGTH / 2);
	    java.awt.Point p1 = new java.awt.Point(x1, y);
	    java.awt.Point p2 = new java.awt.Point(x1 + DEFAULT_LENGTH, y);
	    setPoints(p1, p2);
	}
	show();
    }

    /**
     * Creates a Line with the specified coordinates in the wheels.users.Frame's
     * DrawingPanel.
     *
     * @param x1 the x value of the first endpoint
     * @param y1 the y value of the first endpoint
     * @param x2 the x value of the second endpoint
     * @param y2 the y value of the second endpoint
     */
    public Line (int x1, int y1, int x2, int y2) {
	super(new java.awt.geom.Line2D.Double());
	setPoints(x1, y1, x2, y2);
	show();
    }

    /**
     * Creates a Line with the specified endpoints in the wheels.users.Frame's
     * DrawingPanel.
     *
     * @param p1 the first endpoint
     * @param p2 the second endpoint
     */ 
    public Line (java.awt.Point p1, java.awt.Point p2) {
	super(new java.awt.geom.Line2D.Double());
	setPoints(p1, p2);
	show();
    }

    /**
     * Creates a Line with endpoints (0, 0) and (0, 0) in the 
     * specified DrawingPanel.
     */
    public Line (wheels.etc.DrawingPanel dp) {
	super(new java.awt.geom.Line2D.Double(), dp);
	setPoints(0, 0, 0, 0);
	show();
    }

    /**
     * Creates a Line with the specified endpoint coordinates in the
     * specified DrawingPanel.
     *
     * @param dp the DrawingPanel in which the line will be drawn
     * @param x1 the x value of the first endpoint
     * @param y1 the y value of the first endpoint
     * @param x2 the x value of the second endpoint
     * @param y2 the y value of the second endpoint
     */
    public Line (wheels.etc.DrawingPanel dp, int x1, int y1, int x2, 
		 int y2) {
	super(new java.awt.geom.Line2D.Double(), dp);
	setPoints(x1, y1, x2, y2);
	show();
    }

    /**
     * Creates a Line with the specified endpoints in the
     * specified DrawingPanel.
     *
     * @param dp the DrawingPanel in which the line will be drawn
     * @param p1 the first endpoint
     * @param p2 the second endpoint
     */
    public Line (wheels.etc.DrawingPanel dp, java.awt.Point p1, 
		 java.awt.Point p2) {
	super(new java.awt.geom.Line2D.Double(), dp);
	setPoints(p1, p2);
	show();
    }

    /**
     * Sets the endpoints of the Line to the specified coordinates
     */
    public void setPoints (int x1, int y1, int x2, int y2) {
	setPoints(new java.awt.Point(x1, y1), new java.awt.Point(x2, y2));
    }

    /**
     * Sets the endpoints of the Line to the specified values.
     */
    public void setPoints (java.awt.Point p1, java.awt.Point p2) {
	java.awt.Rectangle oldBounds = getBounds();
	_p1 = p1;
	_p2 = p2;
	((java.awt.geom.Line2D.Double)_shape).setLine(_p1, _p2);
	_dp.repaint(oldBounds.union(getBounds()));
    }

    /**
     * Sets the coordinates of the first endpoint.
     */
    public void setP1 (int x, int y) {
	setP1(new java.awt.Point (x, y));
    }

    /**
     * Set the first endpoint.
     */
    public void setP1 (java.awt.Point p1) {
	setPoints(p1, getP2());
    }

    /**
     * Sets the coordinates of the second endpoint.
     */
    public void setP2 (int x, int y) {
	setP2(new java.awt.Point(x, y));
    }

    /**
     * Set the second endpoint.
     */
    public void setP2 (java.awt.Point p2) {
	setPoints(getP1(), p2);
    }

    /**
     * Returns the first endpoint.
     */
    public java.awt.Point getP1 () {
	return _p1;
    }

    /**
     * Return the x coordinate of the first endpoint.
     */
    public int getX1 () {
	return getP1().x;
    }

    /**
     * Returns the y coordinate of the first endpoint.
     */

    public int getY1 () {
	return getP1().y;
    }

    /**
     * Returns the second endpoint.
     */
    public java.awt.Point getP2 () {
	return _p2;
    }

    /**
     * Return the x coordinate of the second endpoint.
     */
    public int getX2 () {
	return getP2().x;
    }

    /**
     * Returns the y coordinate of the second endpoint.
     */

    public int getY2 () {
	return getP2().y;
    }

    /**
     * Sets the thickness of the stroke used to paint the line.
     */
    public void setThickness (int thickness) {
	java.awt.Rectangle oldBounds = getBounds();
	_thickness = thickness;
	_dp.repaint(oldBounds.union(getBounds()));
    }

    /**
     * Returns the thicknes of the stroke.
     */
    public int getThickness () {
	return _thickness;
    }

    /**
     * Returns the bounding rectangle of the line.
     */
    public java.awt.Rectangle getBounds () {
	java.awt.Rectangle b = super.getBounds();
	if (_thickness < 1)
	    b.grow(10, 10);
	else
	    b.grow(_thickness * 2, _thickness * 2);
	return b;
    }

    public void actualPaint (java.awt.Graphics2D g) {
	java.awt.Stroke oldStroke = g.getStroke();
	g.setStroke(new java.awt.BasicStroke(_thickness));
	g.draw(_shape);
	g.setStroke(oldStroke);
    }
	    
}
