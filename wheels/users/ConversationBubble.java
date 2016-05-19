package wheels.users;

import javax.swing.*;
import java.awt.event.*;


/**
 * Subclass of RoundedRectangle that will display a string, like conversation
 * bubbles in cartoon.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */

public class ConversationBubble extends RoundedRectangle {

    private JTextArea _text;
    private int _borderWidth, _vGap, _tailX, _tailY, _tailDir;
    private boolean _defaultTail = true;
    private java.awt.geom.Line2D _tail;

    public static final int DEFAULT_WIDTH = 200;
    public static final int DEFAULT_BORDER_WIDTH = 30;
    public static final int TAIL_DIR_LEFT = 0;
    public static final int TAIL_DIR_RIGHT = 1;

    private static final int TAIL_WIDTH_DIVISOR = 6;
    private static final int TAIL_HEIGHT_DIVISOR = 3;

    /**
     * Constructs a ConversationBubble in the wheels.users.Frame's DrawingPanel.
     */
    public ConversationBubble (String text) {
	this (text, TAIL_DIR_RIGHT);
    }
    /**
     * Constructs a ConversationBubble displaying the specified String in 
     * the wheels.users.Frame's DrawingPanel. The conversation bubble's "tail"--the
     * line that indicates the speaker creating the conversation bubble--
     * will be pointing in the direction specified by <code>tailDir</code>.
     *
     * @param text the String to display
     * @param tailDir the direction of the bubble's "tail." MUST be
     * either TAIL_DIR_LEFT or TAIL_DIR_RIGHT!
     *
     * @throws java.lang.IllegalArgumentException if <code>tailDir</code>
     * is not TAIL_DIR_LEFT or TAIL_DIR_RIGHT
     */
    public ConversationBubble (String text, int tailDir) {
	this (text, wheels.users.Frame._dp, tailDir);
    }

    /**
     * Constructs a ConversationBubble displaying the specified String in 
     * the passed-in DrawingPanel. The bubble's "tail"--the 
     * line that indicates the speaker creating the conversation bubble--
     * will be pointing to the right.
     */
    public ConversationBubble (String text, wheels.etc.DrawingPanel dp) {
	this (text, dp, TAIL_DIR_RIGHT);
    }

    /**
     * Constructs a ConversationBubble displaying the specified String in 
     * the passed-in DrawingPanel. The conversation bubble's "tail"--the
     * line that indicates the speaker creating the conversation bubble--
     * will be pointing in the direction specified by <code>tailDir</code>.
     *
     * @param text the String to display
     * @param dp the DrawingPanel in which the bubble will be drawn
     * @param tailDir the direction of the bubble's "tail." MUST be
     * either TAIL_DIR_LEFT or TAIL_DIR_RIGHT!
     *
     * @throws java.lang.IllegalArgumentException if <code>tailDir</code>
     * is not TAIL_DIR_LEFT or TAIL_DIR_RIGHT
     */
    public ConversationBubble (String text, wheels.etc.DrawingPanel dp,
			       int tailDir) {

	super(dp);

	_borderWidth = DEFAULT_BORDER_WIDTH;
	_vGap = _borderWidth;
	
	_tailDir = tailDir;
	if (tailDir != TAIL_DIR_RIGHT && tailDir != TAIL_DIR_LEFT)
	    throw new IllegalArgumentException("Invalid value for 'tailDir' passed to ConversationBubble constructor.");

	// creates the text display
	_text = new JTextArea(text);
	_text.setEditable(false);
	// defaults to same color as background, so appears transparent
	_text.setBackground(_dp.getBackground());
	// wrap lines
	_text.setLineWrap(true);
	// break lines on whitespace
	_text.setWrapStyleWord(true);

	this.setFrameColor(java.awt.Color.black);
	this.setFillColor(null);
	this.setLocation(new java.awt.Point(0, 0));
	this.setWidth(DEFAULT_WIDTH);
	updateTail();

	this.show();

    }

    /**
     * Sets the location of the ConversationBubble. Overridden to position the
     * text box within the frame. (Calls to <code>setLocation(int, int)</code>
     * forward to <code>setLocation(java.awt.Point)</code>, so either one 
     * will work correctly.)
     */
    public void setLocation (java.awt.Point p) {
	if (null != _text)
	    _text.setLocation(new java.awt.Point(p.x + _borderWidth,
						 p.y + _vGap));
	super.setLocation (p);
	updateTail();
    }

    /**
     * Sets the size of the ConversationBubble. NOTE: setting an explicit
     * size for the bubble may cut off some of the text. Use
     * <code>setWidth(int)</code> to specify a width for the bubble while 
     * maintaining view of all the text.
     */
    public void setSize (java.awt.Dimension d) {
	
	if (null != _text) {
	    // center the text vertically if there's room
	    int newTWidth = d.width - 2 * _borderWidth;

	    // first set the width with any height so we can find
	    // out the optimal height for the given width
	    _text.setSize(new java.awt.Dimension(newTWidth, 1));

	    // find out the optimal dimension
	    java.awt.Dimension prefD = 
		_text.getPreferredScrollableViewportSize();

	    // if the passed-in height is greater than the optimal height
	    // for the text box...
	    int newTHeight = d.height - 2 * _borderWidth;
	    if (newTHeight > prefD.height) {
		// set the height to the optimal height (visually the same, but
		// prevents the empty part of the text box from overlapping
		// the frame)
		newTHeight = prefD.height;
		// set the vertical borders so the text is centered vertically
		_vGap = (d.height - prefD.height) / 2;
	    }
	    else {
		// otherwise, vert gap is the same as horizontal gap
		_vGap = _borderWidth;
	    }

	    // resize the text box
	    _text.setSize(new java.awt.Dimension(newTWidth, newTHeight));

	    // update location to account for possible changes in vertical
	    // spacing
	    setLocation(getLocation());

	    //System.out.println("set text dim to "+newTWidth+"x" + newTHeight);
	}

	// actually resize frame and repaint
	super.setSize(d);

	// update the tail
	updateTail();
	
    }

    /**
     * Sizes the ConversationBubble to the given width, but maintains full view
     * of the contents by adjusting the height if necessary.
     */
    public void setWidth (int width) {
	// set to any height so we can find out the optimal height for
	// the given width
	_text.setSize(new java.awt.Dimension(width - 2 * _borderWidth,
					     1));
	//set size to the optimal dimension
	java.awt.Dimension d = _text.getPreferredScrollableViewportSize();
	setSize(new java.awt.Dimension(d.width + 2 * _borderWidth,
				       d.height + 2 * _borderWidth));
    }

    /**
     * Graphically shows the conversation bubble.
     */
    public void show () {
	if (null != _text)
	    _dp.add(_text);
	super.show();
    }

    /**
     * Graphically hides the conversation bubble.
     */
    public void hide () {
	if (null != _text)
	    _dp.remove(_text);
	super.hide();
    }

    /**
     * Changes the size of the border of whitespace between the text area
     * and its frame.
     */
    public void setBorderWidth (int width) {
	//System.out.println("setBorderWidth " + width);
	if (width >= 0) {
	    _borderWidth = width;
	    setSize(getSize());
	    setLocation(getLocation());
	}
    }

    /**
     * Set the background and frame color of the ConversationBubble.
     */
    public void setColor (java.awt.Color c) {
	if (null != _text)
	    _text.setBackground(c);
	super.setColor(c);
    }

    /**
     * Set the background color of the ConversationBubble.
     */
    public void setFillColor (java.awt.Color c) {
	if (null != _text)
	    _text.setBackground(c);
	super.setFillColor(c);
    }

    /**
     * Makes the bubble display the passed-in string. Adjusts height so
     * the all of new string is properly displayed.
     */
    public void setText (String text) {
	_text.setText(text);
	setWidth(getWidth());
    }

    /**
     * Normal do not need to worry about this!
     *
     * Does the actual work to paint the conversation bubble.
     */
    public void actualPaint (java.awt.Graphics2D g) {
	super.actualPaint(g);
	java.awt.Stroke oldS = g.getStroke();
	g.setStroke(new java.awt.BasicStroke(getFrameThickness()));
	int x1, y1, x2, y2;
	x1 = getXLocation() + (getWidth() / 2);
	y1 = getYLocation() + getHeight();
	g.setColor(getFrameColor());
	g.draw(_tail);
	g.setStroke(oldS);	   
    }

    /**
     * Returns the bounds of the conversation bubble.
     */
    public java.awt.Rectangle getBounds () {
	java.awt.Rectangle b = _dp.getBounds();
	b.grow(6, 6);

	return b;
    }

    /**
     * Overridden to do nothing.
     */
    public void setRotation (int degrees) {}
    

    /**
     * Allows you to explicitly specify the point that the conversation
     * bubble's "tail" extends from. The tail is the line that attaches
     * the bubble to the speaker's mouth.
     */
    public void setTail (java.awt.Point p) {
	_defaultTail = false;
	_tailX = p.x;
	_tailY = p.y;
	updateTail();
	_dp.repaint(getBounds());
    }

    /**
     * Allows you to explicitly specify the point that the conversation
     * bubble's "tail" extends from. The tail is the line that attaches
     * the bubble to the speaker's mouth.
     */
    public void setTail (int x, int y) {
	setTail(new java.awt.Point(x, y));
    }

    /**
     * Allows you to specify the direction in which the ConversationBubble's 
     * "tail"--the line that indicates the source of the conversation
     * bubble. If you had previously specified an endpoint for the tail
     * using <code>setTail</code>, the tail will now revert to its default
     * sizing at the specified direction.
     *
     * @param dir the direction (must be TAIL_DIR_LEFT or TAIL_DIR_RIGHT)
     *
     * @throws java.lang.IllegalArgumentException if <code>dir</code> is not
     * TAIL_DIR_LEFT or TAIL_DIR_RIGHT.
     */
    public void setTailDirection (int dir) {
	if (dir != TAIL_DIR_RIGHT && dir != TAIL_DIR_LEFT)
	    throw new IllegalArgumentException("Invalid value passed to ConverationBubble.setTailDirection(int).");

	_tailDir = dir;
	_defaultTail = true;
	updateTail();
	_dp.repaint(getBounds());
    }

    /**
     * Updates the tail
     */
    private void updateTail () {
	int x1, y1;
	x1 = getXLocation() + (getWidth() / 2);
	y1 = getYLocation() + getHeight();
	if (_defaultTail) {
	    int multiplier = 1;
	    if (_tailDir == TAIL_DIR_LEFT)
		multiplier = -1;
	    _tailX = x1 + ((getWidth() / 6) * multiplier);
	    _tailY = y1 + (getHeight() / 3);
	}
	_tail = new java.awt.geom.Line2D.Double(x1, y1, _tailX, _tailY);
	_dp.repaint(this.getBounds());
    }
	

}
