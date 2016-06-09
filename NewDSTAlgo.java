public class NewDSTAlgo{
  //from: https://github.com/DChaushev/Advanced-Data-Structures/blob/master/Day-Stout-Warren/src/BinarySearchTree.java
  public void makeComplete() {
    dsw();
  }
  
  private void dsw() {
    TreeNode newRoot = new TreeNode(null, null);
    newRoot.right = root;
    treeToVine(newRoot);
    vineToTree(newRoot, numberOfElements);
    this.root = newRoot.right;
  }
  
  private void treeToVine(TreeNode root) {
    TreeNode tail = root;
    TreeNode rest = tail.right;
    
    while (rest != null) {
      if (rest.left == null) {
        tail = rest;
        rest = rest.right;
      } else {
        TreeNode temp = rest.left;
        rest.left = temp.right;
        temp.right = rest;
        rest = temp;
        tail.right = temp;
      }
    }
  }
  
  private void compress(TreeNode root, int count) {
    TreeNode scanner = root;
    for (int i = 0; i < count; i++) {
      TreeNode child = scanner.right;
      scanner.right = child.right;
      scanner = scanner.right;
      child.right = scanner.left;
      scanner.left = child;
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