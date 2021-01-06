public class ChangeRootOfBinaryTree {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node flipBinaryTree(Node root, Node leaf) {
        // changing pp while moving p. typical linked list stuff...
        Node cur = leaf;
        Node p = cur.parent;
        while (p != null) {
            Node pp = p.parent;
            if (cur.left != null) {
                cur.right = cur.left;
            }
            if (cur.parent == p) {
                cur.parent = null;
            }
            cur.left = p;
            p.parent = cur;
            if (cur == p.left) {
                p.left = null;
            } else {
                p.right = null;
            }

            cur = p;
            p = pp;
            pp = pp == null ? null : pp.parent;
        }
        return leaf;
    }
}
