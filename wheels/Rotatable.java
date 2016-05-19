package wheels;

/**
 * Interface for Object's that can have their rotation set and queried.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */

public interface Rotatable {

    /**
     * Sets the Object't rotation in degrees.
     */
    public void setRotation (int degrees);

    /**
     * Returns the Object's rotation in degrees.
     */
    public int getRotation ();

}
