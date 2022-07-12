import base.TreeNode;

public class EvaluateBooleanTree {
    public boolean evaluateTree(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val == 1;
        }
        boolean lr = evaluateTree(root.left);
        boolean rr = evaluateTree(root.right);
        if (root.val == 3) {
            return lr & rr;
        } else {
            return lr || rr;
        }
    }
}
