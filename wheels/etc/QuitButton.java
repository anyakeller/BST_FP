package wheels.etc;

import javax.swing.*;
import java.awt.event.*;

/**
 * A push button that will quit the program.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */

public class QuitButton extends JButton {

    /**
     * Constructs a QuitButton with the given name.
     */
    public QuitButton(String name) {
	super(name);
	this.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    System.exit(0);
		}
	    });
    }
}
