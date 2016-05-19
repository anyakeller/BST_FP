import wheels.users.*;

public class Treeify extends Frame {
	
	public Treeify(){
		super();
	}

	//pre: 
	//post: prints 
	public static void tree(BST<Integer> b){
		Rectangle title = new Rectangle(200,0);
		title.setSize(300,20);
		title.setColor(java.awt.Color.BLACK);		

		String ans = "";
		ans += b.getRoot().getValue() + "";
		Value root = new Value(b.getRoot());		
		
	}

	public static void main(String[] args){
		BST b = new BST();
		b.insert((Integer) 5);

		Treeify t = new Treeify();
		tree(b);
	}

}
