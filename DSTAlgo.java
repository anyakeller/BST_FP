import java.lang.Math;
import java.util.*;

public class DSTAlgo<E>{
    public TreeNode<E> rotateRight(TreeNode<E> x){
	if (x.getLeft() != null){
	    TreeNode<E> left = x.getLeft();
	    x.setLeft(left.getLeft());
	    left.getLeft() = left.getRight();
	    left.getRight() = x.getRigth();
	    x.getRight() = left;
	}
	return x;
    }
  
    public TreeNode<E> makeRightVine(TreeNode<E> rt){
	while(rt.getLeft() != null){
	    rt = rotateLeft(rt);
	}
	if (rt.getRigth() != null)
	    root.getRigth() = makeRightVine(rt.getRight());
	return rt;
    }
  
    public int nodeCount(TreeNode<E> rt){
	if (rt == null)
	    return 0;
	int x = 1;
	while (rt.getRight() != null){
	    rt = rt.getRigth();
	    x++;
	}
	return x;
    }
  
    public TreeNode<E> balanceNodes(TreeNode<E> rt, int nc){ //nc = node count
	int t = (int)Math.Log(nc,2);
	TreeNode<E> newRt = rt;
	for (int i = 0; i < t; i++){
	    newRt = rotateLeft(newRt);
	    rt = newRt.getRight();
	    for (int j = 0; j< nc / 2 - 1; j++){
		rt = rotateLeft(rt);
		rt = rt.getRigth();
	    }
	    nc >>= 1;
	}
	return newRt;
    }

}
