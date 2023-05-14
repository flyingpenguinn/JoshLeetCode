public class MaxMovesInGrid {
    public int maxMoves(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] dp = new int[m][n];
        int res = 0;
        for (int j = n - 1; j >= 0; --j) {
            for (int i = 0; i < m; ++i) {
                dp[i][j] = 1;
                if (j + 1 < n) {
                    if (a[i][j + 1] > a[i][j]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][j + 1] + 1);
                    }
                    if (i - 1 >= 0 && a[i - 1][j + 1] > a[i][j]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j + 1] + 1);
                    }
                    if (i + 1 < m && a[i + 1][j + 1] > a[i][j]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i + 1][j + 1] + 1);
                    }
                }
                if (j == 0) {
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res - 1;
    }
}
