import java.util.Arrays;

public class NumberOfWaysToRecoverSameBst {
    // create the tree, then post order using compositions to calc the number c(n+k-1, k-1)
    private class TreeNode {
        private int val;
        private TreeNode left = null;
        private TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private TreeNode root = null;

    public int numOfWays(int[] a) {
        dp = new long[a.length + 2][a.length + 2];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        root = new TreeNode(a[0]);
        for (int i = 1; i < a.length; i++) {
            insertBst(root, a[i]);
        }
        return (int) doCount(root)[0] - 1;
    }

    private void insertBst(TreeNode root, int t) {
        TreeNode p = root;
        TreeNode pa = null;
        while (p != null) {
            if (p.val < t) {
                pa = p;
                p = p.right;
            } else {
                pa = p;
                p = p.left;
            }
        }
        if (pa.val > t) {
            pa.left = new TreeNode(t);
        } else {
            pa.right = new TreeNode(t);
        }
    }

    private long Mod = 1000000007;

    private long[] doCount(TreeNode n) {
        if (n == null) {
            return new long[]{0, 0};
        }
        if (n.left == null && n.right == null) {
            return new long[]{1, 1};
        } else if (n.left == null) {
            long[] rt = doCount(n.right);
            return new long[]{rt[0], rt[1] + 1};
        } else if (n.right == null) {
            long[] rt = doCount(n.left);
            return new long[]{rt[0], rt[1] + 1};
        } else {
            long[] left = doCount(n.left);
            long[] right = doCount(n.right);
            int boxes = (int) (left[1] + 1);
            int balls = (int) right[1];
            // each inner arrange combination yields a different composition
            long rt = left[0] * right[0];
            rt %= Mod;
            // compositions: n balls into k boxes, boxes can be empty. c(n+k-1, k-1) ways
            rt *= combi(balls + boxes - 1, boxes - 1);
            rt %= Mod;
            return new long[]{rt, left[1] + right[1] + 1};
        }
    }

    private long[][] dp;

    private long combi(int n, int k) {
        if (k == 0) {
            return 1;
        }
        if (n == k) {
            return 1;
        }
        if (dp[n][k] != -1) {
            return dp[n][k];
        }
        dp[n][k] = (combi(n - 1, k - 1) + combi(n - 1, k)) % Mod;
        return dp[n][k];
    }
}
