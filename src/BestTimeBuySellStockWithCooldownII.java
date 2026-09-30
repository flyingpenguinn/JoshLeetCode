public class BestTimeBuySellStockWithCooldownII {
    public int maxProfit(int[] prices, int cooldown, int[] costs) {
        int n = prices.length;
        int[] dp = new int[n+1];
        for(int i=n-1; i>=0; --i){
            dp[i] = Math.max(dp[i], dp[i+1]);
            for(int j=i+1; j<n; ++j){
                int late = Math.min(j+cooldown+1, n);
                int cur = prices[j]+dp[late] - prices[i] - costs[j-i];
                dp[i] = Math.max(dp[i], cur);
            }
        }
        return dp[0];
    }
}
