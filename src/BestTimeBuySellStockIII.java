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
    // enumerate the starting point of the 2nd transaction
    // on its right we can do max-i
    // on its left we can do maxl till i-1
    public int maxProfit(int[] a) {
        // iterate the 2nd buy point. its profit is
        // best profit from left + maxright-this price
        // a[i]>=0
        if(a==null || a.length==0){
            return 0;
        }
        int n= a.length;
        int[] rightMax = new int[n];
        rightMax[n-1] = a[n-1];
        for(int i=n-2; i>=0; i--){
            rightMax[i] = Math.max(a[i], rightMax[i+1]);
        }
        int leftMin = Integer.MAX_VALUE;
        int leftProfit = 0;
        int r = 0;
        for(int i=0; i<n-1; i++){ // 2nd buy! so wont touch n-1
            int curProfit = leftProfit+ rightMax[i+1]-a[i]; // left + right of i - a[i]
            r = Math.max(r, curProfit);
            leftProfit = Math.max(leftProfit, a[i]-leftMin);
            leftMin = Math.min(leftMin, a[i]);
        }
        return r;
    }
}
