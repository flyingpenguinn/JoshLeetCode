public class CountWaysToDistinguishCandies {
    // set partition... s(n,k) = s(n-1,k-1) + k(s (n-1, k) )
    private int Mod = 1000000007;

    public int waysToDistribute(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= i && j <= k; j++) {
                dp[i][j] = dp[i - 1][j - 1] + j * dp[i - 1][j];
                dp[i][j] %= Mod;
            }
        }
        return (int) dp[n][k];
    }
}