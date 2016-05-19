package wheels.users;


/**
 * A class that can display an image on a DrawingPanel.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>),
 * based on NGP.Graphics.Image by Matt Chotin
 */

public class Image implements wheels.etc.CustomGraphic, wheels.Locatable,
    wheels.Sizeable 
{

    /** Our size */
    private java.awt.Dimension _dimension;
    /** Our location */
    private java.awt.Point _point;
    /** Our DrawingPanel */
    private wheels.etc.DrawingPanel _dpanel;
    /** The actual image */
    private java.awt.Image _awtImage;
    /** Our rotation */
    private double _rotationAngle;
    /** The bounding rectangle */
    private java.awt.Rectangle _bounds;


    /**
     * Create an image from a filename in the wheels.users.Frame's 
     * DrawingPanel. Note that
     * if you are running on Windows, the location of the image must be in your
     * classpath.
     *
     * @param file the filename to take this image form (gif or jpg). 
     */
    public Image(String file) {
	this(wheels.users.Frame._dp, file);
    }
    
    /**
     * Create an image from a URL in the wheels.users.Frame's DrawingPanel
     *
     * @param url the URL to take this image form (gif or jpg)
     */
    public Image(java.net.URL url) {
	this(wheels.users.Frame._dp, url);
    }
    
    
    /**
     * Create an image from a filename. Note that
     * if you are running on Windows, the location of the image must be in your
     * classpath.
     *
     * @param dp the DrawingPanel for this image
     * @param file the filename to take this image form (gif or jpg). 
     */
    public Image(wheels.etc.DrawingPanel dp, String file) {
	_awtImage = java.awt.Toolkit.getDefaultToolkit().getImage(file);
	this.setup(dp);
    }
    
    /**
     * Create an image from a URL.
     *
     * @param dp the DrawingPanel for this image
     * @param url the URL to take this image form (gif or jpg)
     */
    public Image(wheels.etc.DrawingPanel dp, java.net.URL url) {
	_awtImage = java.awt.Toolkit.getDefaultToolkit().getImage(url);
	this.setup(dp);
    }
    
    /**
     * Normal users need not use this!
     *<p>
     * Use the media tracker to load the image, then initialize everything 
     * else.
     */
    protected void setup(wheels.etc.DrawingPanel dp) {
	_dpanel = dp;
	java.awt.MediaTracker tracker = new java.awt.MediaTracker(_dpanel);
	tracker.addImage(_awtImage, 0);
	try {
	    tracker.waitForAll();
	}
	catch(InterruptedException e) { }
	if (tracker.isErrorAny()) {
	    System.out.println("Image failed to load.");
	    System.out.println("Execution will continue but this image won't work, and you can probably expect some other errors.");
	    return;
	}
	_point = new java.awt.Point(0, 0);
	_dimension = new java.awt.Dimension(_awtImage.getWidth(_dpanel),
					    _awtImage.getHeight(_dpanel));
	_rotationAngle = 0;
	_bounds = new java.awt.Rectangle(_point, _dimension);
	show();
    }
    
    /**
     * Set the dimensions of this Image
     *
     * @param d the new <code>Dimension</code> for this Image
     */
    public void setSize (java.awt.Dimension d) {
	java.awt.Rectangle oldBounds = this.getBounds();
	_dimension = d;
	_bounds.setSize(_dimension);
	_awtImage = _awtImage.getScaledInstance(_dimension.width,
						_dimension.height,
						_awtImage.SCALE_DEFAULT);
	_dpanel.repaint(this.getBounds().union(oldBounds));
    }

    /**
     * Set the dimensions of this Image
     *
     */
    public void setSize (int width, int height) {
	setSize(new java.awt.Dimension(width, height));
    }
    
    /**
     * Get the dimensions of this Image
     *
     * @return the <code>Dimension</code> for this Image
     */
    public java.awt.Dimension getSize () {
	if (null != _dimension)
	    return _dimension;
	else
	    return new java.awt.Dimension(0, 0);
    }

    /**
     * Returns the width of the Image.
     */
    public int getWidth() {
	return getSize().width;
    }

    /**
     * Returns the height of this Image.
     */
    public int getHeight () {
	return getSize().height;
    }
    
    /**
     * Set the location of this Image
     *
     * @param p the new <code>Point</code> for this Image
     */
    public void setLocation(java.awt.Point p) {
	_point = p;
	_bounds.setLocation(_point);
	_dpanel.repaint();
    }

    /**
     * Set the location of the Image.
     */
    public void setLocation (int x, int y) {
	setLocation(new java.awt.Point(x, y));
    }

    /**
     * Get the location of this Image
     *
     * @return the <code>Point</code> for this Image
     */
    public java.awt.Point getLocation() {
	if (null != _point)
	    return _point;
	else
	    return new java.awt.Point(0, 0);
    }
    
    /**
     * Get the x value of the Image's location.
     */
    public int getXLocation() {
	return getLocation().x;
    }

    /**
     * Get the y value of the Image's location.
     */
    public int getYLocation() {
	return getLocation().y;
    }

    /** Hide the Image so it won't paint */
    public void hide() {
	if (_dpanel != null) {
	    _dpanel.removeGraphic(this);
	    _dpanel.repaint(this.getBounds());
	}
    }
    
    /** Show the Image so it paints */
    public void show() {
	_dpanel.addGraphic(this);
	_dpanel.repaint(this.getBounds());
    }
    
    /**
     * Get the AWT Image that we are using, not necessary for most users.
     *
     * @return the real AWT image
     */
    public java.awt.Image getAWTImage() {
	return _awtImage;
    }
    
    /**
     * See if this Image contains the Point
     *
     * @param p the <code>Point</code> to test
     */
    public boolean contains(java.awt.Point p) {
	if (0 != _rotationAngle) {
	    double x = _bounds.getCenterX();
	    double y = _bounds.getCenterY();
	    java.awt.geom.AffineTransform trans =
		java.awt.geom.AffineTransform.getRotateInstance(_rotationAngle, x, y);
	    java.awt.Shape s = trans.createTransformedShape(_bounds);
	    return s.contains(p);
	}
	return _bounds.contains(p);
    }
    
    /**
     * Set the rotation for this Image
     *
     * @param degrees the degrees (clockwise) that this should rotate
     */
    public void setRotation(int degrees) {
	java.awt.Rectangle oldBounds = this.getBounds();
	_rotationAngle = Math.toRadians(degrees);
	_dpanel.repaint(getBounds().union(oldBounds));
    }
    
    /**
     * Get the rotation for this Image
     *
     * @return the degrees (clockwise) that this should rotate
     */
    public int getRotation() {
	return (int) Math.toDegrees(_rotationAngle);
    }
    
    /**
     * Normal users need not use this!
     *<p>
     * Rotate the Graphics Context if necessary, the paint, then reset the
     * rotation
     */
    public void paint(java.awt.Graphics2D g) {
	if (_rotationAngle != 0) {
	    double centerX = _bounds.getCenterX();
	    double centerY = _bounds.getCenterY();
	    g.rotate(_rotationAngle, centerX, centerY);
	    g.drawImage(_awtImage, _point.x, _point.y, _dpanel);
	    g.rotate(_rotationAngle*-1, centerX, centerY);
	}
	else g.drawImage(_awtImage, _point.x, _point.y, _dpanel);
    }
    
    /**
     * Get the rectangle that indicates the bounds of this <code>Image</code>
     *
     * @return the <code>java.awt.Rectangle</code> that indicates our bounds.
     */
    public java.awt.Rectangle getBounds() {
	java.awt.Rectangle r = _bounds;
	if (0 != _rotationAngle) {
	    double x = r.getCenterX();
	    double y = r.getCenterY();
	    java.awt.geom.AffineTransform trans =
		java.awt.geom.AffineTransform.getRotateInstance(_rotationAngle, x, y);
	    java.awt.Shape s = trans.createTransformedShape(_bounds);
	    r = s.getBounds();
	}
	return r;
    }
    
    /** Override to do something useful. */
    public void drag(java.awt.event.MouseEvent e) { }
    
    /**
     * Called when the Panel detects that the mouse was clicked.
     * If the click is within this Image. Override to do somthing useful.
     */
    public void mouseClicked(java.awt.event.MouseEvent e) {}
    
    /**
     * Called when the Panel detects that the mouse was dragged.
     * Override to do something useful.
     */
    public void mouseDragged(java.awt.event.MouseEvent e) {}
    
    /** Called when the Panel detects that the mouse was pressed.
     * Override to do something useful.
     */
    public void mousePressed(java.awt.event.MouseEvent e) {}
    
    /** Called when the Panel detects that the mouse was released.
     * Override to do something useful.
     */
    public void mouseReleased(java.awt.event.MouseEvent e) {}
    
}

