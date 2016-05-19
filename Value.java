/*
Image for a value in the tree
*/
import wheels.users.*;

public class Value extends Rectangle{

    
    //constructor
    public Value(int x, int y){
        super(x,y);
        this.setSize(100,20);
        this.setColor(java.awt.Color.GRAY);
    }
    
	public Value(TreeNode<Integer> t){
		super (0,0);
		this.setSize(100,20);
		this.setColor(java.awt.Color.RED);
	}

    public void mousePressed(java.awt.event.MouseEvent e){
        this.setColor(java.awt.Color.LIGHT_GRAY);
    }
    
    public void mouseReleased(java.awt.event.MouseEvent e){
        this.setColor(java.awt.Color.GRAY);
    }
    
}
