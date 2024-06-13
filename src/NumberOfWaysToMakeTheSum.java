public class NumberOfWaysToMakeTheSum {
    // use the bottom up way for knapsack!
    private int[] choices = {1, 2, 6};
    private long Mod = (long) 1e9 + 7;

    public int numberOfWays(int n) {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 0; i < choices.length; ++i) {
            for (int j = choices[i]; j <= n; ++j) {
                dp[j] += dp[j - choices[i]];
                dp[j] %= Mod;
            }
        }
        long res = dp[n];
        if (n >= 4) {
            res += dp[n - 4];
            res %= Mod;
        }
        if (n >= 8) {
            res += dp[n - 8];
            res %= Mod;
        }
        return (int) res;
    }
}
