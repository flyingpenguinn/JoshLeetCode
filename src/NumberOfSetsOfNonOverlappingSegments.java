public class NumberOfSetsOfNonOverlappingSegments {
    // dp[i][0]==1 because we dont need to cover all points
    // also this is the condition we will use in recursion too
    private int mod = 1000000007;

    public int numberOfSets(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = 1L;
        }
        for (int j = 1; j <= k; j++) {
            long sum = 0;
            for (int i = n - 1; i >= 0; i--) {
                dp[i][j] = dp[i + 1][j] + sum;
                dp[i][j] %= mod;
                sum += dp[i][j - 1];
                sum %= mod;
            }
        }
        return (int) dp[0][k];
    }
}
