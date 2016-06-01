/*
Image for a value in the tree
*/
import wheels.users.*;

public class VisualNode<E> extends TextBox{
	
	private boolean hasLeft, hasRight;  // if this node has children
	

    //constructor
    public VisualNode(int x, int y){
        super(x,y,"");
        //this.setSize(,20);
        //this.setColor(java.awt.Color.GRAY);
    }
		
	public static int step(int gen){ //returns the horizontal distance for the line
		return (int)(350 * Math.pow(.5,gen));
	}

	public VisualNode(int x, int y, TreeNode<E> t, int gen){ // x is the center of the node, y is the y center, t is the node, and gen is the generation down 
		super (x,y,t.toString());
		if (t.children() == 1 || t.children() == 3){ // if has left or both, draw left child branch
			Line left = new Line(x,y+20,x-step(gen+1),y+50);
		}	
		if (t.children() == 2 || t.children() == 3){ // if has right  or both, draw right child branch
			Line right = new Line(x,y+20,x+step(gen+1),y+50);
		}


   } 
}
