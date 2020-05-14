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
    public int maxProfit(int[] p) {
        int n = p.length;
        // max p from i
        int[] dp = new int[3];
        int maxa = 0;
        for (int i = n - 1; i >= 0; i--) {
            int cur = maxa - p[i];

            // buy or no buy
            dp[i % 3] = Math.max(cur, dp[(i + 1) % 3]);
            int cura = p[i] + dp[(i + 2) % 3];
            maxa = Math.max(maxa, cura);
        }
        return dp[0];
    }
}
