import java.util.Arrays;

public class MaxAmountOfMoneyRobotCanEarn {
    // note dp initial state must not be -1 as some state can just = -1
    private int Min = (int) -1e9;

    public int maximumAmount(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        dp = new int[m][n][3];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], -12000);
            }
        }
        return solve(a, 0, 0, 2);
    }

    private int[][][] dp;

    private int solve(int[][] a, int i, int j, int k) {
        int m = a.length;
        int n = a[0].length;
        if (i >= m || j >= n || k < 0) {
            return Min;
        }
        if (i == m - 1 && j == n - 1) {
            if (a[i][j] >= 0) {
                return a[i][j];
            } else {
                if (k > 0) {
                    return 0;
                } else {
                    return a[i][j];
                }
            }
        }
        if (dp[i][j][k] != -12000) {
            return dp[i][j][k];
        }
        int way1 = a[i][j] + solve(a, i + 1, j, k);
        int way2 = a[i][j] + solve(a, i, j + 1, k);
        if (a[i][j] < 0 && k > 0) {
            int way1new = solve(a, i + 1, j, k - 1);
            way1 = Math.max(way1, way1new);
            int way2new = solve(a, i, j + 1, k - 1);
            way2 = Math.max(way2, way2new);
        }
        int res = Math.max(way1, way2);
        dp[i][j][k] = res;
        return res;
    }
}
