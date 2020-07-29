import java.util.Arrays;

/*
LC#309
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */
public class BestTimeBuySellStockWithCooldown {
    // like buy sell k times, simplify the inner loop to a max over j>i
    // almost the same as buysell II, just +dp[i+2] not +dp[i+1]
    public int maxProfit(int[] prices) {
        // check null etc
        // dp[i] = pj-pi+dp[j+k] find the j that makes this a max
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int k = 2; // you can buy another at sell time T + k
        int[] dp = new int[n + 1]; // max profit we can get from i to n-1
        int maxafter = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], maxafter - prices[i]);
            int bestlater = i + k < n ? dp[i + k] : 0;
            maxafter = Math.max(maxafter, bestlater + prices[i]);
        }
        return dp[0];
    }
}
