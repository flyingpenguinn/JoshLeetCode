import base.TreeNode;

public class BinaryTreePruning {
    public TreeNode pruneTree(TreeNode root) {
        boolean h1 = dop(root);
        if (h1) {
            return root;
        }
        return null;
    }

    boolean dop(TreeNode t) {
        if (t == null) {
            return false;
        }
        boolean lh1 = dop(t.left);
        if (!lh1) {
            t.left = null;
        }
        boolean rh1 = dop(t.right);
        if (!rh1) {
            t.right = null;
        }
        boolean rt = t.val == 1;
        return rt || lh1 || rh1;
    }
}
