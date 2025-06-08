public class BestTimeBuySellStockV {
    public long maximumProfit(int[] a, int k) {
        int n = a.length;
        long Min = (long) -1e9;
        long[][] dp = new long[n + 1][k + 2];
        for (int p = 1; p <= k; ++p) {
            long maxdp1 = Min;
            long maxdp2 = Min;
            for (int i = n - 1; i >= 0; --i) {
                dp[i][p] = dp[i + 1][p];
                long way1 = maxdp1 - a[i];
                long way2 = maxdp2 + a[i];
                long bestnow = Math.max(way1, way2);
                dp[i][p] = Math.max(bestnow, dp[i][p]);
                maxdp1 = Math.max(maxdp1, dp[i + 1][p - 1] + a[i]);
                maxdp2 = Math.max(maxdp2, dp[i + 1][p - 1] - a[i]);
            }
        }
        return dp[0][k];
    }
}
