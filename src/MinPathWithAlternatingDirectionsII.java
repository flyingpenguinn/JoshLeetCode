public class MinPathWithAlternatingDirectionsII {
    public long minCost(int m, int n, int[][] waitCost) {
        long[][] dp = new long[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                long c1 = (i + 1) * (j + 1);
                if (i == 0 && j == 0) {
                    dp[i][j] = c1;
                } else if (i == 0) {
                    long c2 = waitCost[i][j - 1];
                    if (j - 1 != 0) {
                        dp[i][j] = dp[i][j - 1] + c1 + c2;
                    } else {
                        dp[i][j] = dp[i][j - 1] + c1;
                    }
                } else if (j == 0) {
                    long c2 = waitCost[i - 1][j];
                    if (i - 1 != 0) {
                        dp[i][j] = dp[i - 1][j] + c1 + c2;
                    } else {
                        dp[i][j] = dp[i - 1][j] + c1;
                    }
                } else {
                    long c21 = waitCost[i - 1][j];
                    long c22 = waitCost[i][j - 1];
                    long way1 = dp[i - 1][j] + c1 + c21;
                    long way2 = dp[i][j - 1] + c1 + c22;
                    dp[i][j] = Math.min(way1, way2);
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
