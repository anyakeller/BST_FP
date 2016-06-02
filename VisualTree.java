import wheels.users.*;

public class VisualTree<E> extends Frame {

	public VisualTree(){
		super();
	}

	//pre: 
	//post: prints 
	public void tree(BST b){
		TextBox title = new TextBox(350,0,"Binary Search Tree");
		title.setColor(java.awt.Color.GREEN);		

		//VisualNode root = new VisualNode(350, 50, b.rt(),0);		
		preorderTraversal(b.rt());
		
		//TextBox box1 = new TextBox(50,200,"ABCDEFGHIJKLMOPQRSTUVWXYZ");		
		//TextBox box2 = new TextBox(200,500,"NOPQRSTUVWXYZ");			
		//TextBox box1 = new TextBox(50,200,"abcdefghijklmnopqrstuvwxyz");		
	}

	private void preorderTraversal(TreeNode<E> rt){
		if (rt == null) return;
		VisualNode n = new VisualNode(350,50, rt, 0);
		preorderTraversal(rt.getLeft(),175,100,1);  // numbers are calculated child  data
		preorderTraversal(rt.getRight(),525,100,1); // numbers are calculated child data
	}

	private void preorderTraversal(TreeNode<E> node, int x, int y, int gen){
		if (node == null) return;
		VisualNode child = new VisualNode(x,y,node, gen);
		System.out.println("gen " + gen + ":" + step(gen));

		preorderTraversal(node.getLeft(),x-step(gen+1),y+50,gen+1);  
		preorderTraversal(node.getRight(),x+step(gen+1),y+50,gen+1); 
	}


	public static int step(int gen){ //returns the horizontal distance for the line
		return (int)(350 * Math.pow(.5,gen));
	}

	public static void main(String[] args){	

		BST b = new BST();
		/*
		b.insert("quinn");
		b.insert("is");
		b.insert("a");
		b.insert("poop");
		*/
		b.insert(10);
		b.insert(5);
		b.insert(9);
		b.insert(4);
		b.insert(15);
		b.insert(12);
		b.insert(4);
		b.insert(3);
		b.insert(2);
		b.insert(1);
		b.balance();	
		b.preorder();
		VisualTree t = new VisualTree();
		t.tree(b);
	}

}
