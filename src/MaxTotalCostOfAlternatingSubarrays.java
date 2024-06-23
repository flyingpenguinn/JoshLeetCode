import java.util.Arrays;

public class MaxTotalCostOfAlternatingSubarrays {
    //  each number keeps a sign of their status. instead of enumerating i, i+1, i+2...
    public long maximumTotalCost(int[] a) {
        int n = a.length;
        dp = new long[n][2];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return solve(a, 0, 1);
    }

    private long[][] dp;

    private long solve(int[] a, int i, int pos) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][pos] != -1) {
            return dp[i][pos];
        }
        long sign = pos == 1 ? 1 : -1;
        long way1 = 1L * sign * a[i] + solve(a, i + 1, pos ^ 1);
        long way2 = 1L * sign * a[i] + solve(a, i + 1, 1);
        long res = Math.max(way1, way2);
        dp[i][pos] = res;
        return res;
    }
}
