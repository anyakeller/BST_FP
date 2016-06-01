import wheels.users.*;

public class VisualTree<E> extends Frame {
	
	public VisualTree(){
		super();
	}

	//pre: 
	//post: prints 
	public void tree(BST b){
		TextBox title = new TextBox(200,0,"Binary Search Tree");
		title.setSize(300,40);
		title.setColor(java.awt.Color.GREEN);		
		//title.setText("herp derp i'm a tree"); 

		String ans = "";
		ans += b.getRoot().getValue() + "";
		VisualNode root = new VisualNode(b.getRoot());		
		TextBox box1 = new TextBox(50,200,"ABCDEFGHIJKLMOPQRSTUVWXYZ");		
		//TextBox box2 = new TextBox(200,500,"NOPQRSTUVWXYZ");		
	}

	public static void main(String[] args){	

		BST b = new BST();
		b.insert(5);

		VisualTree t = new VisualTree();
		t.tree(b);
	}

}
