import java.lang.Math;
import java.util.*;

public class BST<E extends Comparable>{

	private TreeNode<E> _root;
	private int _size;

	public BST(){
		_root = null;
		_size = 0;
	}

	public boolean isEmpty(){
		return _size == 0;
	}

	public int size() {
		return _size;
	}

	public TreeNode<E> rt(){
		return _root;
	}

	// Iterative version
	// returns null if val not found
	public TreeNode<E> find(E val){
		TreeNode<E> curr = _root;
		while ( curr != null && !val.equals(curr.getValue())){
			if (val.compareTo(curr.getValue()) < 0) curr = curr.getLeft();
			else curr = curr.getRight();
		}
		return curr;
	}
	//********************************************************
	// Recursive version
	// post: return null if x not found
	public TreeNode<E> findR(E x){
		return findR(_root, x);
	}

	private TreeNode<E> findR(TreeNode<E> rt, E x){
		if (rt == null || x.equals(rt.getValue())) return rt;
		if (x.compareTo(rt.getValue()) < 0) 
			return findR(rt.getLeft(),x);
		return findR(rt.getRight(), x);
	}
	//********************************************************

	public boolean isFound(E x){
		return find(x) != null;
	}

	//********************************************************
	// pre: !isEmpty()
	public E maxValue() throws IllegalStateException{
		return maxValue(_root);
	}

	private E maxValue(TreeNode<E> rt) throws IllegalStateException{
		if (rt == null)  throw new IllegalStateException("empty");
		return maxNode(rt).getValue();
	}
	//********************************************************
	private TreeNode<E> maxNode(TreeNode<E> rt){
		if (rt != null) 
			while (rt.getRight() != null)
				rt = rt.getRight();
		return rt;
	}
	//********************************************************
	// pre: !isEmpty()
	public E minValue() throws IllegalStateException{
		return minValue(_root);
	}

	private E minValue(TreeNode<E> rt) throws IllegalStateException{
		if (rt == null) throw new IllegalStateException("empty"); 
		return minNode(rt).getValue();
	}
	//********************************************************
	private TreeNode<E> minNode(TreeNode<E> rt){
		if (rt != null) 
			while (rt.getLeft() != null) 
				rt = rt.getLeft();
		return rt;
	}
	//********************************************************
	public boolean remove(E val){
		if (isFound(val)){
			_root = remove(_root, val);
			_size--;
			return true;
		}
		return false;
	}
	// val is in the tree
	private TreeNode<E> remove(TreeNode<E> rt, E val){
		if (rt.isLeaf()) return null;
		if (val.equals(rt.getValue())){
			// no right substree
			if (rt.getRight() == null){
				E max = maxValue(rt.getLeft());
				rt.setValue(max);
				rt.setLeft(remove(rt.getLeft(), max));
			}
			else {
				E min = minValue(rt.getRight());
				rt.setValue(min);
				rt.setRight(remove(rt.getRight(), min));
			}
		}
		else if (val.compareTo(rt.getValue()) < 0)
			rt.setLeft(remove(rt.getLeft(), val));
		else rt.setRight(remove(rt.getRight(), val));    
		return rt;
	}
	//********************************************************
	// Recursive version
	// inserts x as a  leaf
	public void insert(E x){
		_root = insertNode(_root,new TreeNode<E>(x));
		_size++;
	}

	public void balance(){
		ArrayList<E> list = new ArrayList<E>();
		list = inorderTraversalList(rt(), list);
		BalanceTree(list, this);
	}
	
	public ArrayList<E> inorderTraversalList(TreeNode<E> rt, ArrayList<E> list){
		if (rt == null) return list;
		inorderTraversalList(rt.getLeft(), list);
		list.add(rt.getValue());

		inorderTraversalList(rt.getRight(), list);
		return list;
	}

	public BST<E> BalanceTree(int min, int max,
			ArrayList<E> list, BST<E> bst)
	{
		if (min <= max)
		{
			int middleNode = (int)Math.ceil(((double)min + max) / 2);

			bst.insert(list.get(middleNode));

			BalanceTree(min, middleNode - 1, list, bst);

			BalanceTree(middleNode + 1, max, list, bst);
		}
		return bst;
	}

	public void BalanceTree(ArrayList<E> list, BST<E> bst){
		for (E i: list)
			bst.remove(i);

		BalanceTree(0,list.size()-1, list, bst);

	}



	private TreeNode<E> insertNode(TreeNode<E> rt, TreeNode<E> leaf){
		if (rt == null) return leaf;
		E val = leaf.getValue();
		if (val.compareTo(rt.getValue()) < 0)
			rt.setLeft(insertNode(rt.getLeft(),leaf));
		else 
			rt.setRight(insertNode(rt.getRight(),leaf));
		return rt;
	}

	//********************************************************
	// Iterative version
	// inserts a leaf
	private void insertNode(TreeNode<E> leaf){
		E val = leaf.getValue();
		if (_root == null)
			_root = leaf;
		else{
			TreeNode<E> curr = _root;
			TreeNode<E> prev = curr;
			while (curr != null){
				prev = curr;
				if (val.compareTo(curr.getValue()) >= 0)
					curr = curr.getRight();
				else
					curr = curr.getLeft();
			}
			if (val.compareTo(prev.getValue()) >= 0)
				prev.setRight(leaf);
			else
				prev.setLeft(leaf);

		}
		_size++;
	}
	//********************************************************
	public String toString(){
		String ans = toString(_root);
		if (ans.length() > 1) {
			int pos = ans.lastIndexOf(",");
			ans = ans.substring(0,pos);
		}
		return ans;
	}
	// Uses an inorder traversal.
	private String toString(TreeNode<E> rt){
		String ans = "";
		if (rt != null){
			ans = toString(rt.getLeft()) + rt.getValue() + ", " + 
				toString(rt.getRight());
		}
		return ans;
	}
	//********************************************************


	//********************************************************
	public void inorder(){
		System.out.print("inorder: " );
		inorderTraversal(_root);
		System.out.println();
	}

	private void inorderTraversal(TreeNode<E> rt){
		if (rt == null) return;
		inorderTraversal(rt.getLeft());
		System.out.print(rt.getValue() + " " );
		inorderTraversal(rt.getRight());
	}
	//********************************************************
	public void preorder(){
		System.out.print("preorder: " );
		preorderTraversal(_root);
		System.out.println();
	}

	private void preorderTraversal(TreeNode<E> rt){
		if (rt == null) return;
		System.out.print(rt.getValue() + " " ); 
		preorderTraversal(rt.getLeft());
		preorderTraversal(rt.getRight());
	}
	//********************************************************
	public void postorder(){
		System.out.print("postorder: " );
		postorderTraversal(_root);
		System.out.println();
	}

	private void postorderTraversal(TreeNode<E> rt){
		if (rt == null) return;
		postorderTraversal(rt.getLeft());
		postorderTraversal(rt.getRight());
		System.out.print(rt.getValue() + " " );     
	}
	//********************************************************
	public static void main(String [] args){
		BST<Integer> tree = new BST<Integer>();
		int N = 10; //Integer.parseInt(args[0]);
		for (int i = 0; i < N; i++){
			int r = (int) (Math.random() * 100);
			//   System.out.println("insert " + r);
			tree.insert(r);
			//  System.out.println("tree: " + tree);
		}



		tree.inorder();
		//   System.out.println(tree);
		tree.preorder();
		//  System.out.println(tree);
		tree.postorder();
		System.out.println(tree);


		/*
		//System.out.println("Max value: " + tree.maxValue());
		while (!tree.isEmpty()){
		Integer max = tree.maxValue();
		System.out.println("max : " + max);
		tree.remove(max);
		System.out.println("remove max: " + tree);
		}*/

	}

}
