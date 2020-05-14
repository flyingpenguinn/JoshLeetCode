import base.TreeNode;

public class FlipEquivalentBinaryTrees {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return isEqual(root1, root2);
    }

    private boolean isEqual(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        boolean c1 = isEqual(t1.left, t2.left) && isEqual(t1.right, t2.right);
        if (c1) {
            return true;
        }
        boolean c2 = isEqual(t1.right, t2.left) && isEqual(t1.left, t2.right);
        return c2;
    }
}
