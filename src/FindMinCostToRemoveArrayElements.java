public class FindMinCostToRemoveArrayElements {
    private Integer[][] dp;

    public int minCost(int[] a) {
        int n = a.length;
        dp = new Integer[n][n + 1];
        return solve(a, 0, n);
    }

    private int solve(int[] a, int i, int pre) {
        int n = a.length;
        int prev = pre == n ? 0 : a[pre];
        if (i == n) {
            return prev;
        } else if (i == n - 1) {
            return Math.max(prev, a[i]);
        } else if (i == n - 2 && pre == n) {
            return Math.max(a[i], a[i + 1]);
        }
        if (dp[i][pre] != null) {
            return dp[i][pre];
        }
        int res = 0;
        if (pre == n) {
            int way1 = Math.max(a[i], a[i + 1]) + solve(a, i + 2, pre);
            int way2 = Math.max(a[i], a[i + 2]) + solve(a, i + 3, i + 1);
            int way3 = Math.max(a[i + 1], a[i + 2]) + solve(a, i + 3, i);
            res = Math.min(way1, Math.min(way2, way3));
        } else {
            int way1 = Math.max(a[i], a[i + 1]) + solve(a, i + 2, pre);
            int way2 = Math.max(a[i], prev) + solve(a, i + 2, i + 1);
            int way3 = Math.max(a[i + 1], prev) + solve(a, i + 2, i);
            res = Math.min(way1, Math.min(way2, way3));
        }

        dp[i][pre] = res;
        return res;
    }
}
