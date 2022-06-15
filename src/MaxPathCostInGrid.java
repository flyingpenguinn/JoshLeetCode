public class MaxPathCostInGrid {

    private int Max = (int) 2e9;
    private Integer[][] dp;

    public int minPathCost(int[][] a, int[][] c) {
        int m = a.length;
        int n = a[0].length;
        dp = new Integer[m][n];
        int res = Max;
        for (int j = 0; j < n; ++j) {
            int cur = solve(0, j, a, c);
            res = Math.min(res, cur);
        }
        return res;
    }

    private int solve(int i, int j, int[][] a, int[][] c) {
        int m = a.length;
        int n = a[0].length;
        if (i == m - 1) {
            return a[i][j];
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int res = Max;
        for (int k = 0; k < n; ++k) {
            int cost = c[a[i][j]][k];
            int cur = a[i][j] + cost + solve(i + 1, k, a, c);
            res = Math.min(res, cur);
        }
        dp[i][j] = res;
        return res;
    }
}
