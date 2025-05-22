package customDSImplementations;

public class CustomBinaryTree {
    private int length;
    private Node root;
    public CustomBinaryTree() {
        this.root = null;
        this.length = 0;
    }
    
    public static class Node{
        private int value;
        private Node left;
        private Node right;
        public Node(int value){
            this.value = value;
            this.left = this.right =  null;
        }
    }
}
