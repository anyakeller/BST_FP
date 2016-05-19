package wheels.etc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


/**
 * Modified JPanel for easy drawing of custom graphics. Keeps track of all
 * graphics that have been drawn on it and overrides paintComponent(...)
 * to properly redraw shapes. All shapes added to panel must implement
 * the wheels.etc.CustomGraphic interface so they can be properly redrawn.
 * Also forwards mouse imput to the appropriate graphic's mouse listening
 * methods.
 *
 * @autoor John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 *
 */


public class DrawingPanel extends JPanel {
    
    /** Storage for all the graphics drawn on the panel */
    protected Vector _graphics;

    /** keeps track of active graphic for mouse interaction */
    private CustomGraphic _activeGraphic;

    /**
     * Creates drawing panel with size 0x0.
     */
    public DrawingPanel () {
	this(new Dimension(0, 0));
    }

    /**
     * Creates drawing panel with the passed-in dimensions.
     *
     * @param width the width of Panel
     * @param hieght the height of the Panel
     */
    public DrawingPanel (int width, int height) {
	this(new Dimension(width, height));
    }

    /**
     * Creates drawing panel with the passed-in dimension.
     *
     * @param d the dimension of the Panel
     */
    public DrawingPanel (Dimension d) {

	//no layout (absolute positioning) and double-buffering to aid drawing
	super(null, true);

	//set desired dimensions
	this.setPreferredSize(d);
	this.setMaximumSize(d);
	this.setMinimumSize(d);

	//default BG white
	this.setBackground(Color.white);

	_graphics = new Vector();

	_activeGraphic = null;

	//add mouse listeners so we can detect mouse interaction
	this.addMouseListener(new MouseClick());
	this.addMouseMotionListener(new MouseMove());
    }

    /**
     * This should never be called directly!
     * <p>
     * Called by Swing to paint the panel. Overridden to paint the Panel
     * and all the graphics within it.
     *
     * @param g the <code>Graphics</code> instance we should use to draw
     */
    public void paintComponent (Graphics g) {
	super.paintComponent(g);

	paintGraphics(g);
    }

    /**
     * Normal user don't need to worry about this.
     * <p>
     * Loops through vector of graphics and tells each one to paint itself.
     *
     * @param g the <code>Graphics</code> instance we should use to draw
     */
    public void paintGraphics (Graphics g) {

	//cast to Graphics2D (in everything java 1.2 and later, always get G2D)
	Graphics2D g2 = (Graphics2D) g;

	int size = _graphics.size();
	for (int i = 0; i < size; ++i)
	    ((CustomGraphic) _graphics.elementAt(i)).paint(g2);

    }

    /**
     * Adds a graphic to the panel.
     * 
     * @param graphic the graphic to add
     */
    public void addGraphic (CustomGraphic graphic) {
	if (!_graphics.contains(graphic))
	    _graphics.add(graphic);;
    }

    /**
     * Removes the passed-in graphic from the panel.
     *
     * @param graphic the graphic to remove
     */
    public void removeGraphic (CustomGraphic graphic) {
	_graphics.remove(graphic);
    }

    /**
     * Finds the topmost graphic that contains the given point. Returns
     * null if the point is not contained by any of the shapes. Used
     * to route mouse input to the appropriate graphic.
     *
     * @param p the point
     */
    protected CustomGraphic findShape (Point p) {
	
	int size = _graphics.size();
	
	for (int i = size - 1; i >= 0; --i) {
	    if (((CustomGraphic) _graphics.elementAt(i)).contains(p)) {
		return (CustomGraphic) _graphics.elementAt(i);
	    }
	}
	
	return null;
    }

    /**
     * A mouse listener to detect mouse clicks. If the mouse click takes place
     * over a graphic in the panel, calls the appropriate method on the 
     * graphic.
     */
    class MouseClick extends MouseAdapter {

	/** handler for mouse clicks */
	public void mouseClicked (MouseEvent e) {

	    CustomGraphic g;

	    if ((g = findShape(e.getPoint())) != null) 
		g.mouseClicked(e);

	}

	/** handler for mouse presses */
	public void mousePressed (MouseEvent e) {

	    CustomGraphic g;

	    if ((g = findShape(e.getPoint())) != null) {
		_activeGraphic = g;
		g.mousePressed(e);
	    }

	}

	/** handler for mouse releases */
	public void mouseReleased (MouseEvent e) {
	    if (_activeGraphic != null) {
		_activeGraphic.mouseReleased(e);
		_activeGraphic = null;
	    }
	}
	    

    }

    /**
     * A listener for mouse motion. If mouse motion occurs over a graphic in
     * the panel, calls the appropriate mouse method on the graphic.
     */
    class MouseMove extends MouseMotionAdapter {

	/** handler for mouse drag */
	public void mouseDragged (MouseEvent e) {
	    if (_activeGraphic != null)
		_activeGraphic.mouseDragged(e);
		
	}
    } 
}
