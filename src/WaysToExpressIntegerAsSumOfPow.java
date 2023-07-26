public class WaysToExpressIntegerAsSumOfPow {
    private long Mod = (long) (1e9 + 7);
    private Long[][] dp;

    public int numberOfWays(int n, int x) {
        dp = new Long[n + 1][n + 1];
        long rt = solve(n, 1, x);
        return (int) rt;
    }

    private long solve(long n, int i, long x) {
        if (n == 0) {
            return 1;
        }
        if (i > n) {
            return 0;
        }
        if (i == n + 1) {
            return n == 0 ? 1 : 0;
        }
        int in = (int) n;
        int ii = (int) i;
        if (dp[in][ii] != null) {
            return dp[in][ii];
        }
        long way1 = solve(n, i + 1, x);
        long eaten = (long) Math.pow(i, x);
        long way2 = 0;
        if (n >= eaten) {
            way2 = solve(n - eaten, i + 1, x);
        }
        long res = way1 + way2;
        res %= Mod;
        dp[in][ii] = res;
        return res;
    }
}
