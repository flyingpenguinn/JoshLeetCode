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
    public int maxProfit(int kl, int[] p) {
        int n = p.length;
        if (kl >= n / 2) {
            int r = 0;
            for (int i = 1; i < n; i++) {
                if (p[i] > p[i - 1]) {
                    r += p[i] - p[i - 1];
                }
            }
            return r;
        }
        int[][] dp = new int[n + 1][2];
        // i must be in inner loop for accu purpose
        for (int k = 1; k <= kl; k++) {
            int maxa = 0;
            for (int i = n - 1; i >= 0; i--) {
                int cur = maxa - p[i];
                dp[i][k % 2] = Math.max(cur, dp[i + 1][k % 2]);
                int cura = p[i] + dp[i + 1][(k - 1) % 2];
                maxa = Math.max(cura, maxa);
            }
        }
        return dp[0][kl % 2];
    }

    public static void main(String[] args) {
        System.out.println(new BestTimeBuySellStocksIv().maxProfit(2, ArrayUtils.read1d("2,1,4,5,2,9,7")));
        System.out.println(new BestTimeBuySellStocksIv().maxProfit(2, ArrayUtils.read1d("3,2,6,5,0,3")));
        System.out.println(new BestTimeBuySellStocksIv().maxProfit(1, ArrayUtils.read1d("2,4,1")));
        System.out.println(new BestTimeBuySellStocksIv().maxProfit(2, ArrayUtils.read1d("2,4,1")));
    }

}

class BestTimeBuySellRawDp {
    public int maxProfit(int ts, int[] a) {
        int n = a.length;
        // max profit from i transactions, up to jth day
        int[][] dp = new int[2][n];
        int max = 0;
        for (int k = 1; k <= ts; k++) {
            // enumerate sell point
            int kth = k % 2;
            for (int i = 1; i < n; i++) {
                // maxi is the part that we can calc in passing when we get dp[kth][i]
                int maxi = Integer.MIN_VALUE;
                for (int j = 0; j < i; j++) {
                    maxi = Math.max(maxi, (j == 0 ? 0 : dp[(k - 1) % 2][j - 1]) - a[j]);
                }
                dp[kth][i] = maxi + a[i];
                dp[kth][i] = Math.max(dp[kth][i - 1], dp[kth][i]);
                max = Math.max(max, dp[kth][i]);
            }
        }
        return max;
    }
}
