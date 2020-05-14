import base.TreeNode;

public class UnivalueBinaryTree {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isUni(root, root.val);

    }

    boolean isUni(TreeNode root, int target) {
        if (root == null) {
            return true;
        }
        if (root.val != target) {
            return false;
        }
        // must be and
        return isUni(root.left, target) && isUni(root.right, target);
    }
}
