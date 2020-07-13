import base.ArrayUtils;

import java.util.Arrays;

/*
LC#188
Say you have an array for which the i-th element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */
public class BestTimeBuySellStocksIv {
    // dp[i][k] = Math.max(dp[i+1][k], maxj: p[j]-p[i]+dp[j+1][k-1] ). we can keep the max p[j]+dp[j+1][k-1] while we scan i
    public int maxProfit(int times, int[] a) {
        if (a == null || a.length == 0 || times <= 0) {
            return 0;
        }
        int n = a.length;
        if (times >= n - 1) {
            int r = 0;
            for (int i = 1; i < n; i++) {
                if (a[i] > a[i - 1]) {
                    r += a[i] - a[i - 1];
                }
            }
            return r;
        }
        int[][] dp = new int[n][times + 1];
        // dp[i][0]=0, dp[n-1][k] = 0, dp[n][k] = 0
        for (int k = 1; k <= times; k++) {
            int maxLater = a[n - 1]; // dp[n-1+1][] = 0
            for (int i = n - 2; i >= 0; i--) {
                // note this is similar to what we did in stock II just adding k here
                dp[i][k] = Math.max(dp[i + 1][k], maxLater - a[i]);
                maxLater = Math.max(dp[i + 1][k - 1] + a[i], maxLater);
            }
        }
        return dp[0][times];
    }

    public static void main(String[] args) {
        System.out.println(new BestTimeBuySellStocksIv().maxProfit(2, ArrayUtils.read1d("2,1,4,5,2,9,7")));
        System.out.println(new BestTimeBuySellStocksIv().maxProfit(2, ArrayUtils.read1d("3,2,6,5,0,3")));
        System.out.println(new BestTimeBuySellStocksIv().maxProfit(1, ArrayUtils.read1d("2,4,1")));
        System.out.println(new BestTimeBuySellStocksIv().maxProfit(2, ArrayUtils.read1d("2,4,1")));
    }

}
