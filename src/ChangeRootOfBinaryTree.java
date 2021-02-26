public class ChangeRootOfBinaryTree {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    // similar to reversing linked list!
    public Node flipBinaryTree(Node root, Node leaf) {
        Node cur = leaf;
        Node pre = null;
        while(cur != root){
            Node p = cur.parent;
            cur.parent = pre;
            clearParent(p, cur);
            if(cur.left != null){
                cur.right = cur.left;
            }
            cur.left = p;
            pre = cur;
            cur = p;
        }
        cur.parent  = pre;
        return leaf;
    }

    private void clearParent(Node p, Node cur){
        if(p.left == cur){
            p.left = null;
        }else{
            p.right = null;
        }
    }
}
