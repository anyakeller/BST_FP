import java.lang.Math;
import java.util.*;

public class BalanceAlgo{

  
 /* private int[] toArray(BST<Integer> bst){
    int [] list = new int[bst.size()];
    return list;
  }*/
    
  

  public static ArrayList<Integer> inorderTraversalList(TreeNode<Integer> rt, ArrayList<Integer> list){
    if (rt == null) return list;
    inorderTraversalList(rt.getLeft(), list);
    list.add(rt.getValue());
 
    inorderTraversalList(rt.getRight(), list);
    return list;
  }
  
  public static BST<Integer> BalanceTree(int min, int max, 
                                         ArrayList<Integer> list, BST<Integer> bst)
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
  
  public static void BalanceTree(ArrayList<Integer> list, BST<Integer> bst){
    for (Integer i: list)
      bst.remove(i);
    
    BalanceTree(0,list.size()-1, list, bst);

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
    tree.postorder();
    tree.inorder();
    ArrayList<Integer> list = new ArrayList<Integer>();
    System.out.println(list);
    list = inorderTraversalList(tree.rt(), list);
    System.out.println(list);
   // tree = BalanceAlgo.BalanceTree(1,6,list,tree);
    BalanceTree(list,tree);
    tree.preorder();
    tree.postorder();
    tree.inorder();
    
    System.out.println("------------------------------------");
    
    BST<Integer> tree2 = new BST<Integer>();
    int N = 10; //Integer.parseInt(args[0]);
    for (int i = 0; i < N; i++){
      int r = (int) (Math.random() * 100);
      //   System.out.println("insert " + r);
      tree2.insert(r);
      //  System.out.println("tree: " + tree);
    }
    ArrayList<Integer> list2 = new ArrayList<Integer>();
    tree2.preorder();
    tree2.postorder();
    tree2.inorder();
    list2 = inorderTraversalList(tree2.rt(), list2);
    System.out.println(list2);
    BalanceTree(list2, tree2);
    tree2.preorder();
    tree2.postorder();
    tree2.inorder();
  }
}