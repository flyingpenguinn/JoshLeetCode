import java.util.Arrays;

public class MergeOperationsForMinTravelTime {
    private int[] tpsum;
    private int[][][][] dp;
    private int sumtime = 101;

    public int minTravelTime(int l, int n, int k, int[] p, int[] t) {
        tpsum = new int[n];
        tpsum[0] = t[0];
        for (int i = 1; i < n; ++i) {
            tpsum[i] = tpsum[i - 1] + t[i];
        }
        dp = new int[n][n][sumtime][k + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int i2 = 0; i2 < sumtime; ++i2) {
                    Arrays.fill(dp[i][j][i2], -1);
                }
            }
        }
        return solve(p, t, 1, 0, t[0], k, l, n);
    }

    private int Max = (int) 1e9;

    private int solve(int[] p, int[] t, int i, int pre, int pretime, int k, int l, int n) {
        if (k < 0) {
            return Max;
        }
        if (i == n - 1) {
            if (k > 0) {
                return Max;
            }
            int ctsum = pretime;
            int cpos = p[i] - p[pre];
            int cur = cpos * ctsum;
            return cur;
        }
        if (dp[i][pre][pretime][k] != -1) {
            return dp[i][pre][pretime][k];
        }
        int way1 = solve(p, t, i + 1, pre, pretime, k - 1, l, n);
        int ctsum = pretime;
        int cpos = p[i] - p[pre];
        int cur = cpos * ctsum;
        int cpretime = tpsum[i] - tpsum[pre];
        int way2 = cur + solve(p, t, i + 1, i, cpretime, k, l, n);
        int res = Math.min(way1, way2);
        dp[i][pre][pretime][k] = res;
        return res;
    }
}
