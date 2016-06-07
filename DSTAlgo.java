import java.lang.Math;
import java.util.*;

public class DSTAlgo<E>{
	public TreeNode<E> rotateLeft(TreeNode<E> x){
		if (x.getRight() != null){
			TreeNode<E> right = x.getRight();
			x.setRight(right.getRight());
			right.setRight(right.getLeft());
			right.setLeft(x.getLeft());
			x.setLeft(right);
		}
		return x;
	}	


	public TreeNode<E> rotateRight(TreeNode<E> x){
		if (x.getLeft() != null){
			TreeNode<E> left = x.getLeft();
			x.setLeft(left.getLeft());
			left.setLeft(left.getRight());
			left.setRight(x.getRight());
			x.setRight(left);
		}
		return x;
	}

	public TreeNode<E> makeRightVine(TreeNode<E> rt){
		while(rt.getLeft() != null){
			rt = rotateRight(rt);
		}
		if (rt.getRight() != null)
			rt.setRight(makeRightVine(rt.getRight()));
		return rt;
	}

	public static int nodeCount(TreeNode<E> rt){
		if (rt == null)
			return 0;
		int x = 1;
		while (rt.getRight() != null){
			rt = rt.getRight();
			x++;
		}
		return x;
	}

	public static reeNode<E> balanceNodes(TreeNode<E> rt, int nc){ //nc = node count
		int t = (int)(Math.log((double)nc)/(Math.log((double)2)));
		TreeNode<E> newRt = rt;
		for (int i = 0; i < t; i++){
			newRt = rotateLeft(newRt);
			rt = newRt.getRight();
			for (int j = 0; j< nc / 2 - 1; j++){
				rt = rotateLeft(rt);
				rt = rt.getRight();
			}
			nc >>= 1;
		}
		return newRt;
	}

	public static void main(String [] args){
		BST tree = new BST();
		tree.insert(5);
		tree.insert(4);
		tree.insert(3);
		tree.insert(2);
		tree.insert(1);
		tree.insert(6);
		tree.preorder();
		System.out.println(tree);
		int nc = nodeCount(tree.rt());
		balanceNodes(tree.rt(), nc);
		System.out.println(tree);




	}

}
