public class BestTimeBuySellStockWithFee {
    // almost identical with buysell II just -f
    public int maxProfit(int[] p, int f) {
        int n = p.length;
        int[] dp = new int[n];
        dp[n - 1] = 0;
        int maxstub = p[n - 1] - f;
        for (int i = n - 2; i >= 0; i--) {
            // System.out.println(maxstub);
            int buy = maxstub - p[i];
            int cstub = p[i] - f + dp[i + 1];
            maxstub = Math.max(cstub, maxstub);
            int nobuy = dp[i + 1];
            dp[i] = Math.max(buy, nobuy);
            //    System.out.println(dp[i]);

        }
        return dp[0];
    }
}
