package wheels;

/**
 * Interface for Objects that can have their location set and queried.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */

public interface Locatable {

    /**
     * Set Object's location to given coordinates.
     */
    public void setLocation (int x, int y);

    /**
     * Set Object's location to given point.
     */
    public void setLocation (java.awt.Point p);

    /**
     * Returns the Object's location.
     */
    public java.awt.Point getLocation ();

    /**
     * Returns the Object's x location.
     */
    public int getXLocation ();

    /**
     * Returns the Object's y location.
     */
    public int getYLocation ();

}
