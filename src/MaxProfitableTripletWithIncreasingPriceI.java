public class MaxProfitableTripletWithIncreasingPriceI {
    public int maxProfit(int[] prices, int[] profits) {
        int n = prices.length;
        int res = -1;
        for (int i = 0; i < n; ++i) {
            int cp = prices[i];
            int maxjp = -1;
            for (int j = 0; j < i; ++j) {
                if (prices[j] < cp) {
                    //   System.out.println("jp "+profits[j]);
                    maxjp = Math.max(maxjp, profits[j]);
                }
            }
            int maxkp = -1;
            for (int k = i + 1; k < n; ++k) {
                if (prices[k] > cp) {
                    // System.out.println("kp "+profits[k]);
                    maxkp = Math.max(maxkp, profits[k]);
                }
            }
            // System.out.println(i+" "+maxjp+" "+maxkp);
            if (maxjp != -1 && maxkp != -1) {
                int cur = maxjp + profits[i] + maxkp;
                res = Math.max(res, cur);
            }
        }
        return res;
    }
}
