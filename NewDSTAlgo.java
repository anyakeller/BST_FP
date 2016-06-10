public class NewDSTAlgo{
  //from: https://github.com/DChaushev/Advanced-Data-Structures/blob/master/Day-Stout-Warren/src/BinarySearchTree.java
  public void makeComplete() {
    dsw();
  }
  
  private void dsw() {
    TreeNode newRoot = new TreeNode(null, null);
    newRoot.setRight(_root);
    treeToVine(newRoot);
    vineToTree(newRoot, numberOfElements);
    _root = newRoot.getRight();
  }
  
  private void treeToVine(TreeNode root) {
    TreeNode tail = root;
    TreeNode rest = tail.getRight();
    
    while (rest != null) {
      if (rest.getLeft()== null) {
        tail = rest;
        rest = rest.getRigth();
      } else {
        TreeNode temp = rest.getLeft();
        rest.setLeft(temp.getRight());
        temp.setRight(rest);
        rest = temp;
        tail.setRight(temp);
      }
    }
  }
  
  private void compress(TreeNode root, int count) {
    TreeNode scanner = root;
    for (int i = 0; i < count; i++) {
      TreeNode child = scanner.getRight();
      scanner.setRight(child.getRight());
      scanner = scanner.getRight();
      child.setRight(scanner.getLeft());
      scanner.setLeft(child);
    }
  }
  
  private void vineToTree(TreeNode root, int size) {
    int numberOfLeaves = (int) (size + 1 - Math.pow(2, Math.floor(Math.log(size + 1) / Math.log(2))));
    compress(root, numberOfLeaves);
    size = size - numberOfLeaves;
    while (size > 1) {
      compress(root, (int) Math.floor(size / 2));
      size = (int) Math.floor(size / 2);
    }
  }
}