/*
LC#123
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeBuySellStockIII {
    public int maxProfit(int[] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }
        int[] ri = new int[n];
        ri[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            ri[i] = Math.max(ri[i + 1], a[i]);
        }
        int min = Integer.MAX_VALUE;
        int maxl = 0;
        int max = 0;
        // enumerate the starting point of the 2nd transaction
        for (int i = 0; i < n; i++) {
            int p = ri[i] - a[i] + maxl;
            max = Math.max(max, p);
            min = Math.min(a[i], min);

            maxl = Math.max(a[i] - min, maxl);
        }
        return max;
    }
}
