import base.TreeNode;

public class FindDistanceInBinaryTree {
    private int res = 0;

    public int findDistance(TreeNode root, int p, int q) {
        dfs(root, p, q, 0);
        return res;
    }

    private int dfs(TreeNode n, int p, int q, int d) {

        if (n == null) {
            return -1;
        }

        int left = dfs(n.left, p, q, d + 1);
        int right = dfs(n.right, p, q, d + 1);
        if (n.val == p || n.val == q) {
            if (left >= 0) {
                res = left - d;
            } else if (right >= 0) {
                res = right - d;
            }
            return d;
        }
        if (left >= 0 && right >= 0) {
            res = (left - d) + (right - d);
            return -1;
        } else if (left >= 0) {
            return left;
        } else if (right >= 0) {
            return right;
        } else {
            return -1;
        }

    }
}
