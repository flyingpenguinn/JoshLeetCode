public class SunOfConsecutiveSubsequences {
    // dp[i][j][k] three d dp
    private long Mod = (long) 1e9 + 7;

    public int getSum(int[] a) {
        int n = a.length;
        int max = 0;
        for (int ai : a) {
            max = Math.max(max, ai);
        }
        long[][][] dp = new long[max + 2][2][2];
        // v
        // 0 == sum, 1==count
        // 0=== +1, 1=== -1
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            int prev = a[i] - 1;
            long psum = dp[prev][0][0];
            long pcount = dp[prev][1][0];

            int after = a[i] + 1;
            long asum = dp[after][0][1];
            long acount = dp[after][1][1];

            long cur1 = psum + v * pcount + v;
            long cur2 = asum + v * acount + v;
            res += cur1;
            res %= Mod;
            res += cur2;
            res -= v;
            res %= Mod;

            dp[v][0][0] += cur1;
            dp[v][0][0] %= Mod;
            dp[v][0][1] += cur2;
            dp[v][0][1] %= Mod;
            dp[v][1][0] += pcount + 1;
            dp[v][1][1] += acount + 1;
        }
        return (int) res;
    }
}
