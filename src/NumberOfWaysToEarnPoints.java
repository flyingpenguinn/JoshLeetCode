public class NumberOfWaysToEarnPoints {
    private Long[][] dp;
    private long mod = (long) (1e9 + 7);

    public int waysToReachTarget(int t, int[][] a) {
        int n = a.length;
        dp = new Long[n][t + 1];
        return (int) solve(a, 0, t);
    }

    private long solve(int[][] a, int i, int t) {
        int n = a.length;
        if (i == n) {
            return t == 0 ? 1 : 0;
        }
        if (dp[i][t] != null) {
            return dp[i][t];
        }
        long cur = 0;
        long count = a[i][0];
        long score = a[i][1];
        long res = 0;
        for (int j = 0; j <= count; ++j) {
            cur = j * score;
            if (cur <= t) {
                res += solve(a, i + 1, t - (int) (cur));
                res %= mod;
            }
        }
        dp[i][t] = res;
        return res;
    }
}
