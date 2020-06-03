/*
LC#714
Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

Example 1:
Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Note:

0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.
 */
public class BestTimeBuySellStockWithFee {
    // almost identical with buysell II just -f
    public int maxProfit(int[] a, int fee) {
        int n = a.length;
        int[] dp = new int[n + 1];
        int maxj1 = 0;
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            int cur = maxj1 - a[i] - fee;
            max = Math.max(max, cur);
            int curj1 = dp[i + 1] + a[i];
            maxj1 = Math.max(maxj1, curj1);
            dp[i] = Math.max(cur, dp[i + 1]);
        }
        return max;
    }
}
