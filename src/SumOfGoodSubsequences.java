public class SumOfGoodSubsequences {
    private long Mod = (long) (1e9 + 7);

    public int sumOfGoodSubsequences(int[] a) {
        int n = a.length;
        int maxv = 0;
        for (int ai : a) {
            maxv = Math.max(maxv, ai);
        }
        long[] dp = new long[maxv + 5];
        long[] count = new long[maxv + 5];
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            if (v - 1 >= 0) {
                long cvm1 = count[v - 1];
                dp[v] += dp[v - 1] + cvm1 * v;
                dp[v] %= Mod;
                count[v] += cvm1;
                count[v] %= Mod;
            }
            long cvp1 = count[v + 1];
            dp[v] += dp[v + 1] + cvp1 * v;
            dp[v] %= Mod;
            count[v] += cvp1;
            count[v] %= Mod;
            dp[v] += v;
            dp[v] %= Mod;
            count[v] += 1;
            count[v] %= Mod;
        }
        long sum = 0;
        for (int i = 0; i < dp.length; ++i) {
            sum += dp[i];
            sum %= Mod;
        }
        return (int) sum;
    }
}
