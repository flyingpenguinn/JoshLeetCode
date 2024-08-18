import java.util.Arrays;

public class MaxEnergyBoostFromTwoDrinks {
    public long maxEnergyBoost(int[] a, int[] b) {
        int n = a.length;
        dp = new long[n][2];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        long way1 = 0L + a[0] + solve(a, b, 1, 0);
        long way2 = 0L + b[0] + solve(a, b, 1, 1);
        long res = Math.max(way1, way2);
        return res;
    }

    private long[][] dp;

    private long solve(int[] a, int[] b, int i, int j) {
        int n = a.length;
        if (i >= n) {
            return 0L;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        long way1 = 0;
        if (j == 0) {
            way1 = 0L + a[i] + solve(a, b, i + 1, j);
        } else {
            way1 = 0L + b[i] + solve(a, b, i + 1, j);
        }
        long way2 = 0;
        if (j == 0) {
            way2 = solve(a, b, i + 1, 1);
        } else {
            way2 = solve(a, b, i + 1, 0);
        }
        long res = Math.max(way1, way2);
        dp[i][j] = res;
        return res;
    }
}
