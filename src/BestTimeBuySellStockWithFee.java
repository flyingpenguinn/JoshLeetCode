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
    /* f(i) = {
                max{ f(j+1)+a(j)-a(i) } +tf  j>i
                or just f(i+1)
        }
        almost identical with buysell 2
     */
    public int maxProfit(int[] a, int fee) {
        int n = a.length;
        int[] dp = new int[n + 1];
        int maxlater = 0;
        for (int i = n - 1; i >= 0; i--) {
            int cur = maxlater - a[i] - fee;
            dp[i] = Math.max(cur, dp[i + 1]);
            maxlater = Math.max(maxlater, dp[i + 1] + a[i]);
        }
        return dp[0];
    }
}
