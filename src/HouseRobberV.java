import java.util.Arrays;

public class HouseRobberV {
    public long rob(int[] a, int[] b) {
        int n = a.length;
        dp = new long[n][2];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return solve(a, 0, 0, b);
    }

    private long[][] dp;

    private long solve(int[] a, int i, int j, int[] b) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        long way1 = solve(a, i + 1, 0, b);
        long way2 = 0;
        if (i - 1 >= 0 && j == 1 && b[i] == b[i - 1]) {
            way2 = 0;
        } else {
            way2 = (long) (a[i]) + solve(a, i + 1, 1, b);
        }
        long res = Math.max(way1, way2);
        dp[i][j] = res;
        return res;
    }
}
