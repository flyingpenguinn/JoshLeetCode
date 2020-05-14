import base.TreeNode;

public class BinaryTreeColoring {
    // y will take the whole subtree as his/hers. so we calc left, right, rest(parent) of x and
    // see if we can get a bigger (part > n-part) there
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode xnode = findNode(root, x);
        int leftcount = getSubtree(xnode.left);
        int rightcount = getSubtree(xnode.right);
        int xnodes = leftcount + rightcount + 1;
        if (n - xnodes > xnodes) {
            // if we can find a bigger subtree by locking out x's parent...
            return true;
        } else {
            // find a bigger one in x's subtrees. note this includes the case where x is root
            if (leftcount > n - leftcount || rightcount > n - rightcount) {
                return true;
            }
        }
        return false;
    }

    private TreeNode findNode(TreeNode root, int x) {
        if (root == null) {
            return null;
        }
        if (root.val == x) {
            return root;
        }
        TreeNode lf = findNode(root.left, x);
        if (lf != null) {
            return lf;
        } else {
            return findNode(root.right, x);
        }
    }

    private int getSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getSubtree(root.left) + getSubtree(root.right) + 1;
    }
}
