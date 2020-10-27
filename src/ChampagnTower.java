public class ChampagnTower {
    // can become 1d dp because we only rely on i-1
    public double champagneTower(int p, int r, int c) {
        double[][] dp = new double[r + 1][r + 1];
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                if (i == 0) {
                    dp[i][j] = p;
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] <= 1 ? 0 : (dp[i - 1][j] - 1) / 2.0;
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] <= 1 ? 0 : (dp[i - 1][j - 1] - 1) / 2.0;
                } else {
                    double left = dp[i - 1][j - 1] <= 1 ? 0 : (dp[i - 1][j - 1] - 1) / 2.0;
                    double right = dp[i - 1][j] <= 1 ? 0 : (dp[i - 1][j] - 1) / 2.0;
                    dp[i][j] = left + right;
                }
            }
        }
        return dp[r][c] <= 1.0 ? dp[r][c] : 1.0;
    }
}
