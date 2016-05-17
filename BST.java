public class BST<E extends Comparable>{

    private TreeNode<E> _root;
    private int _size;

    public BST(){
    	_root = null;
    	_size = 0;
    }

    // returns null if val not found
    public TreeNode<E> find(E val){
    	TreeNode<E> curr = _root;
    	while ( curr != null && !val.equals(curr.getValue())){
    	    if (val.compareTo(curr.getValue()) < 0) curr = curr.getLeft();
    	    else curr = curr.getRight();
	}
	return curr;
    }

    public boolean isEmpty(){
    	return _size == 0;
    }

    public int size() {
    	return _size;
    }
    

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


    public boolean isFound(E x){
    	return find(x) != null;
    }
    // pre: !isEmpty()
    public E maxValue() throws IllegalStateException{
    	return maxValue(_root);
    }

    private E maxValue(TreeNode<E> rt) throws IllegalStateException{
    	if (rt == null)  throw new IllegalStateException("empty");
    	return maxNode(rt).getValue();
    }

    private TreeNode<E> maxNode(TreeNode<E> rt){
    	if (rt != null) 
    	    while (rt.getRight() != null)
    		rt = rt.getRight();
    	return rt;
    }

    // pre: !isEmpty()
    public E minValue() throws IllegalStateException{
    	return minValue(_root);
    }

    private E minValue(TreeNode<E> rt) throws IllegalStateException{
    	if (rt == null) throw new IllegalStateException("empty");	
    	return minNode(rt).getValue();
    }

    private TreeNode<E> minNode(TreeNode<E> rt){
    	if (rt != null) 
    	    while (rt.getLeft() != null) 
    		rt = rt.getLeft();
    	return rt;
    }
    /*    
	  public boolean isLeaf(TreeNode<E> rt){
    	 if (rt != null && rt.getLeft() == null && rt.getRight() == null)
    	 return true;
    	 return false;
	  }
    */
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


    public void insert(E x){
	//insertNode(new TreeNode<E>(x));
	_root = insertNode(_root,new TreeNode<E>(x));
	_size++;
    }

    private TreeNode<E> insertNode(TreeNode<E> rt, TreeNode<E> n){
    	if (rt == null) return n;
    	E val = n.getValue();
    	if (val.compareTo(rt.getValue()) < 0)
    	    rt.setLeft(insertNode(rt.getLeft(),n));
    	else 
    	    rt.setRight(insertNode(rt.getRight(),n));
    	return rt;
    }



    private void insertNode(TreeNode<E> n){
    	E val = n.getValue();
    	if (_root == null)
    	    _root = n;
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
    		prev.setRight(n);
    	    else
    		prev.setLeft(n);
    
    	}
    	_size++;
        }
        public String toString(){
    	String ans = "";
    	return ans;
    }
   
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

    public static void main(String [] args){
    	
    }

}
