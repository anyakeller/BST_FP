/*
Image for a value in the tree
*/
import wheels.users.*;

public class VisualNode<E> extends TextBox{

    
    //constructor
    public VisualNode(int x, int y){
        super(x,y,"");
        //this.setSize(,20);
        //this.setColor(java.awt.Color.GRAY);
    }
    
	public VisualNode(TreeNode<E> t){
		super (0,0,t.toString());
		//this.setSize(100,20);
		//this.setColor(java.awt.Color.RED);
   } 
}
