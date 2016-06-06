import java.lang.Math;
import java.util.*;

public class DSTAlgo<E>{
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
  
    public int nodeCount(TreeNode<E> rt){
	if (rt == null)
	    return 0;
	int x = 1;
	while (rt.getRight() != null){
	    rt = rt.getRight();
	    x++;
	}
	return x;
    }
  
    public TreeNode<E> balanceNodes(TreeNode<E> rt, int nc){ //nc = node count
	int t = (int)(Math.log((double)nc)/(Math.log((double)2)));
	TreeNode<E> newRt = rt;
	for (int i = 0; i < t; i++){
	    newRt = rotateLeft(newRt);
	    rt = newRt.getRight();
	    for (int j = 0; j< nc / 2 - 1; j++){
		rt = rotateRight(rt);
		rt = rt.getRight();
	    }
	    nc >>= 1;
	}
	return newRt;
    }

}
