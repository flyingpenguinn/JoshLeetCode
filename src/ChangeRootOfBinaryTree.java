public class ChangeRootOfBinaryTree {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    ;

    // we should climb by the parent link, not dfs
    // note the .parent can be changed, so we keep ppp to move together with p and pp
    public Node flipBinaryTree(Node root, Node leaf) {
        Node p = leaf;
        Node pp = p.parent;
        // need to move these 2 together because .parent is changed. so need ppp to keep pp's old parent...
        p.parent = null;
        while (p != root) {
            //  System.out.println(p.val+" "+pp.val);
            Node ppp = pp.parent;
            if (p == pp.left) {
                pp.left = null;
            } else {
                pp.right = null;
            }
            pp.parent = p;
            if (p.left != null) {
                p.right = p.left;
            }
            p.left = pp;
            p = pp;
            pp = ppp;
        }
        return leaf;
    }

    // solution 2 dfs. note we maintain parent pointer of child in n, but set right/left tree on n's level in place
    public Node flipBinaryTreeDfs(Node root, Node leaf) {
        dfs(root, root, leaf);
        return leaf;
    }

    private boolean dfs(Node n, Node root, Node leaf) {
        if (n == null) {
            return false;
        }
        if (n == leaf) {
            n.parent = null;
            return true;
        }
        boolean lr = dfs(n.left, root, leaf);
        boolean rr = dfs(n.right, root, leaf);
        if (lr || rr) {
            if (rr) {
                n.right.left = n;
                n.parent = n.right;
                if (n != root) {
                    n.right = n.left;
                } else {
                    n.right = null;
                }
            } else {
                n.left.left = n;
                n.parent = n.left;
                n.left = null;
            }
            return true;
        }
        return false;
    }
}
