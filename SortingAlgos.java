import java.lang.Math;
import java.util.*;

public class SortingAlgos{

  
 /* private int[] toArray(BST<Integer> bst){
    int [] list = new int[bst.size()];
    return list;
  }*/
    
  

  public static ArrayList<Integer> preorderTraversalList(TreeNode<Integer> rt, ArrayList<Integer> list){
    if (rt == null) return list;
    list.add(rt.getValue());
    preorderTraversalList(rt.getLeft(), list);
    preorderTraversalList(rt.getRight(), list);
    return list;
  }
  
  public static BST<Integer> BalanceTree(int min, int max, 
                                         ArrayList<Integer> list, BST<Integer> bst)
  {
    if (min <= max)
    {
      int middleNode = (int)Math.ceil(((double)min + max) / 2);
      
      bst.insert(list.get(middleNode-1));
      
      BalanceTree(min, middleNode - 1, list, bst);
      
      BalanceTree(middleNode + 1, max, list, bst);
    } 
    return bst;
  }
  
  
  
  public static void main(String [] args){
    BST<Integer> tree = new BST<Integer>();
    tree.insert(5);
    tree.insert(4);
    tree.insert(3);
    tree.insert(2);
    tree.insert(1);
    tree.insert(6);
    tree.preorder();
    ArrayList<Integer> list = new ArrayList<Integer>();
    list = SortingAlgos.preorderTraversalList(tree.rt(), list);
    tree = SortingAlgos.BalanceTree(1,6,list,tree);
    System.out.println(tree);
  }
}