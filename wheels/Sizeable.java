package wheels;


/**
 * Interface for Object's that can have their size set and queried.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */

public interface Sizeable {

    /**
     * Sets the Object's size to the given dimensions.
     */
    public void setSize (int width, int height);

    /**
     * Sets the Object's size to the given Dimension.
     */
    public void setSize (java.awt.Dimension d);

    /**
     * Returns the Object's size as an AWT Dimension.
     */
    public java.awt.Dimension getSize ();

    /**
     * Returns the Object's width.
     */
    public int getWidth ();

    /**
     * Returns the Object's height.
     */
    public int getHeight ();

}
