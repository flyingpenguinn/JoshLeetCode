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
    public int maxProfit(int t, int[] a) {
        int n = a.length;
        if (t >= n / 2) {
            int res = 0;
            for (int i = 1; i < n; i++) {
                if (a[i] > a[i - 1]) {
                    res += a[i] - a[i - 1];
                }
            }
            return res;
        }
        int[][] dp = new int[n + 1][t + 1];
        // dp[i][k]: max profit from i...n-1 buy k times.may not use a[i] at all
        for (int k = 1; k <= t; k++) {
            int maxafter = 0;
            // maxafter is for some j, a[j]+dp[j+1][k-1].
            for (int i = n - 1; i >= 0; i--) {
                dp[i][k] = Math.max(maxafter - a[i], dp[i + 1][k]);
                // for each i, the max profit is some j so that a[j]-a[i]+dp[j+1][k-1] is the max. that's why we keep maxafter
                maxafter = Math.max(maxafter, dp[i + 1][k - 1] + a[i]);
            }
        }
        return dp[0][t];
    }

    public static void main(String[] args) {
        System.out.println(new BestTimeBuySellStocksIv().maxProfit(2, ArrayUtils.read1d("2,1,4,5,2,9,7")));
        System.out.println(new BestTimeBuySellStocksIv().maxProfit(2, ArrayUtils.read1d("3,2,6,5,0,3")));
        System.out.println(new BestTimeBuySellStocksIv().maxProfit(1, ArrayUtils.read1d("2,4,1")));
        System.out.println(new BestTimeBuySellStocksIv().maxProfit(2, ArrayUtils.read1d("2,4,1")));
    }

}
