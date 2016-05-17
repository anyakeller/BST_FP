public class TreeApp{

  public static void main(String [] args){
  
  	BST<Integer> tree = new BST<Integer>();
  	BST<Integer> tree2 = tree;
  	BST<Integer> tree3 = tree;
    	
  	GenSort.split(tree);
  	System.out.println(tree);
  	GenSort.bogo(tree2);
  	System.out.println(tree2);
  	GenSort.swap(tree3);
  	System.out.println(tree3);
  	
  	
      }
  
  }
