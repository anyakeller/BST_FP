import wheels.users.*;

public class Treeify extends Frame {
	
	public Treeify(){
		super();
	}

	//pre: 
	//post: prints 
	public static void tree(BST<Integer> b){
		String ans = "";
		ans += b.getRoot().getValue() + "";
		Value root = new Value(b._root);		
		
	}

	public static void main(String[] args){
		BST b = new BST();
		b.insert((Integer) 5);

		Treeify t = new Treeify();
		tree(b);
	}

}
