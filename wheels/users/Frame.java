package wheels.users;

import javax.swing.*;
import wheels.etc.*;


/**
 * Frame that has public methods to allow for behind-the-scenes
 * manipulation and GUI creation. Used to create graphical programming
 * assignments that hide actual graphics code from students. The JFrame
 * is actually contained by the class instead of subclassed so that the 
 * frame can be updated (packed) when components are added. Not beautiful, 
 * but, if Frame were a subclass of JFrame, components added after creation
 * through the static methods would not show up until the user resized the
 * window.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 *
 */

public class Frame {

    //The GUI components that can change are static so other classes can add
    //to the GUI without knowing about the specific instance of Frame (no
    //parameters)
    private static JFrame _frame;
    private static LayoutPanel _row, _col;

    public static DrawingPanel _dp; //DP is public because some graphics might
                                    //need to know about their DP in order
                                    //to call repaint(...)

    private LayoutPanel _contentPane, _topHalf;
    private QuitButton _qb;

    public Frame() {
	//make frame
	_frame = new JFrame();

	_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	//content pane is main panel
	_contentPane = new LayoutPanel(LayoutPanel.Y_AXIS);

	//upper portion containing DP and col for components
	_topHalf = new LayoutPanel(LayoutPanel.X_AXIS);

	_dp = new DrawingPanel(700, 500);
	_col = new LayoutPanel(LayoutPanel.Y_AXIS);

	_topHalf.add(_dp);
	_topHalf.add(_col);

	//row on bottom for components
	_row = new LayoutPanel(LayoutPanel.X_AXIS);

	_qb = new QuitButton("Quit");

	_contentPane.add(_topHalf);
	_contentPane.add(_row);
	_contentPane.add(_qb);

	_frame.setContentPane(_contentPane);
	_frame.pack();
	_frame.show();

    }

    /**
     * Adds a Component to the row at the bottom of the frame and 
     * repacks the frame so that everything shows up all pretty.
     */
    public static void addElementRow(java.awt.Component component) {
	_row.add(component);
	_frame.pack();
    }

    /**
     * Adds Component to column along right side and repacks.
     */
    public static void addElementColumn(java.awt.Component component) {
	_col.add(component);
	_frame.pack();
    }

}

	
	
    
