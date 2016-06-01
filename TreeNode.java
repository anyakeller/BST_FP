public class TreeNode<E>{
    private E _value;
    private TreeNode<E> _left, _right;

    public TreeNode(E value){
	this(value,null,null);
    }

    public TreeNode(E value, TreeNode<E> left, TreeNode<E> right){
	_value = value;
	_left = left;
	_right = right;
    }

    public E getValue(){
	return _value;
    }

    public TreeNode<E> getLeft(){
	return _left;
    }
    
    public TreeNode<E> getRight(){
	return _right;
    }

    public E setValue(E newValue){
	E ans = getValue();
	_value = newValue;
	return ans;
    }

    public TreeNode<E> setLeft(TreeNode<E> newLeft){
	TreeNode<E> ans = getLeft();
	_left = newLeft;
	return ans;
    }


    public TreeNode<E> setRight(TreeNode<E> newRight){
	TreeNode<E> ans = getRight();
	_right = newRight;
	return ans;
    }

    public boolean isLeaf(){
	return getRight() == null && getLeft() == null;
    }

    public String toString(){
	return getValue() + "";
    }


}
