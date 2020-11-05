import base.TreeNode;

public class LowestCommonAncestorOfBinaryTreeII {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lca(root, p, q)[0];
    }

    // 0:lca, 1: either p or q if lca == null. otherwise null
    private TreeNode[] lca(TreeNode n, TreeNode p, TreeNode q) {
        if (n == null) {
            return new TreeNode[]{null, null};
        }
        TreeNode[] left = lca(n.left, p, q);
        TreeNode[] right = lca(n.right, p, q);
        if (left[0] != null || right[0] != null) {
            return left[0] == null ? right : left;
        }
        if (n == p || n == q) {
            if (left[1] != null || right[1] != null) {
                return new TreeNode[]{n, null};
            } else {
                return new TreeNode[]{null, n};
            }
        } else {
            if (left[1] != null && right[1] != null) {
                return new TreeNode[]{n, null};
            } else {
                return new TreeNode[]{null, left[1] == null ? right[1] : left[1]};
            }
        }
    }
}
