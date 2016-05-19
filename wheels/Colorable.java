package wheels;

/**
 * Interface for Objects that can have their color changed and queried.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */

public interface Colorable {

    /**
     * Sets the Object's color.
     */
    public void setColor (java.awt.Color c);

    /**
     * Returns the Object's color.
     */
    public java.awt.Color getColor();

}
       
