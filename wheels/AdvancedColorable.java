package wheels;

/**
 * Interface for Objects that can have a frame and fill color set and 
 * queried, as well as overall color.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */

public interface AdvancedColorable extends Colorable {

    /**
     * Sets the frame color.
     */
    public void setFrameColor (java.awt.Color c);

    /**
     * Returns the frame color.
     */
    public java.awt.Color getFrameColor ();

    /**
     * Sets the fill color.
     */
    public void setFillColor (java.awt.Color c);

    /**
     * Returns the fill color.
     */
    public java.awt.Color getFillColor ();

}
