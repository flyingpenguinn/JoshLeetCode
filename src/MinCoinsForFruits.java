public class MinCoinsForFruits {
    private int Max = (int) (1e9);
    private Integer[][] dp;

    public int minimumCoins(int[] a) {
        int n = a.length;
        dp = new Integer[n][n + 1];
        return solve(a, 0, 0);
    }

    private int solve(int[] a, int i, int j) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int way1 = a[i] + solve(a, i + 1, i + 1);
        int way2 = Max;
        if (j > 0) {
            way2 = solve(a, i + 1, j - 1);
        }
        int res = Math.min(way1, way2);
        dp[i][j] = res;
        return res;
    }
}
