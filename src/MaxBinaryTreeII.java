import base.TreeNode;

public class MaxBinaryTreeII {
    // append, so always on the right...
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        } else if (root.val < val) {
            TreeNode nr = new TreeNode(val);
            nr.left = root;
            return nr;
        } else {
            TreeNode right = insertIntoMaxTree(root.right, val);
            root.right = right;
            return root;
        }
    }
}

