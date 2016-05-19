package wheels.etc;

import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * Panel that allows for the horizontal or vertical arrangement of components.
 * Hides some of the idiosyncracies of swing layouts. Essentially a BoxLayout
 * that places all the Components in contains in indiviual panels within
 * the LayoutPanel so that sizing is handled properly.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>
 */

public class LayoutPanel extends JPanel {

    public static final int X_AXIS = 0;
    public static final int Y_AXIS = 1;

    private Vector _panels;
    private FlowLayout _panelLayout;

    /**
     * Constructs an empty layout panel with horizontal orientation.
     * Default alignment within the panel is centered. Use
     * {@link #alignLeft() alignLeft}, {@link #alignRight() alignRight},
     * or {@link #alignCenter() alignCenter} to change the alignment.
     *
     * @throws java.lang.IllegalArgumentException if <code>orientation</code>
     * does not equal <code>X_AXIS</code> or <code>Y_AXIS</code>
     */
    public LayoutPanel () {
	this(X_AXIS);
    }

    /**
     * Constructs an empty layout panel with the specified orientation.
     * Default alignment within the panel is centered. Use
     * {@link #alignLeft() alignLeft}, {@link #alignRight() alignRight},
     * or {@link #alignCenter() alignCenter} to change the alignment.
     *
     * @param orientation <code>X_AXIS</code> to align elements in a row,
     * <br> <code>Y_AXIS</code> to align them in a column
     *
     * @throws java.lang.IllegalArgumentException if <code>orientation</code>
     * does not equal <code>X_AXIS</code> or <code>Y_AXIS</code>
     */
    public LayoutPanel (int orientation) {
	super();
	
	//translate the passed-in orientation to a BoxLayout orientation
	int bOrient;
	if (Y_AXIS == orientation)
	    bOrient = BoxLayout.Y_AXIS;
	else if (X_AXIS == orientation)
	    bOrient = BoxLayout.X_AXIS;
	else
	    throw new IllegalArgumentException("Orientation other than LayoutPanel.X_AXIS or LayoutPanel.Y_AXIS passed to LayoutPanel constructor");

	this.setLayout(new BoxLayout(this, bOrient));

	//layout to be used for the panels
	_panelLayout = new FlowLayout(FlowLayout.CENTER);

	//initialize vector to store panels
	_panels = new Vector();
    }

    /**
     * @noshow
     *
     * Normal users don't need to worry about this!
     *
     * Overrides the java.awt.Component addImpl(...) to make a new panel
     * for every component that is added. Ensures proper sizing and even
     * spacing.
     * 
     * @param comp the Component to be added
     * @param constraints the constraints to be placed on the addition
     * @param index the index at which the component should be added
     */
    protected void addImpl (Component comp, Object constraints, int index) {
	
	JPanel panel = new JPanel();
	panel.setLayout(_panelLayout);
	panel.setBackground(this.getBackground());
	panel.add(comp, 0);
	_panels.add(panel);
	super.addImpl(panel, constraints, index);

    }
	
    /**
     * Aligns all subsequent components added to the Panel to the left. 
     * Doesn't affect components that have already been added.
     */
    public void alignLeft() {
	_panelLayout = new FlowLayout(FlowLayout.LEFT);
    }
	
    /**
     * Aligns all subsequent components added to the Panel to the center. 
     * Doesn't affect components that have already been added.
     */
    public void alignCenter() {
	_panelLayout = new FlowLayout(FlowLayout.CENTER);
    }
	
    /**
     * Aligns all subsequent components added to the Panel to the right. 
     * Doesn't affect components that have already been added.
     */
    public void alignRight() {
	_panelLayout = new FlowLayout(FlowLayout.RIGHT);
    }

}
