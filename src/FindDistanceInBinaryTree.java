import base.TreeNode;

public class FindDistanceInBinaryTree {
    // find lc then get dist to both
    private int Max = (int) 1e9;

    public int findDistance(TreeNode root, int p, int q) {
        TreeNode lca = getLca(root, p, q);
        int d1 = dist(lca, p);
        int d2 = dist(lca, q);
        return d1 + d2;
    }

    private TreeNode getLca(TreeNode n, int p, int q) {
        if (n == null) {
            return null;
        }
        if (n.val == p || n.val == q) {
            return n;
        }
        TreeNode lr = getLca(n.left, p, q);
        TreeNode rr = getLca(n.right, p, q);
        if (lr != null && rr != null) {
            return n;
        } else {
            return lr != null ? lr : rr;
        }
    }

    private int dist(TreeNode n, int v) {
        if (n == null) {
            return Max;
        }
        if (n.val == v) {
            return 0;
        }
        return Math.min(dist(n.left, v), dist(n.right, v)) + 1;
    }
}
