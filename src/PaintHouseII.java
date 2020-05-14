import base.ArrayUtils;

import java.util.Arrays;

/*
LC#265
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
Follow up:
Could you solve it in O(nk) runtime?
 */
public class PaintHouseII {
    // we just need min and second min from i+1
    int Max = 1000000000;

    public int minCostII(int[][] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }
        int k = a[0].length;
        int[] dp = new int[k + 1];
        int min = 0;
        int min2 = 0;

        for (int i = n - 1; i >= 0; i--) {
            int nmin = Max;
            int nmin2 = Max;

            for (int j = 0; j < k; j++) {
                if (dp[j] == min) {
                    // cant use j so use  next in line
                    dp[j] = a[i][j] + min2;
                } else {
                    dp[j] = a[i][j] + min;
                }
                if (dp[j] < nmin) {
                    nmin2 = nmin;
                    nmin = dp[j];
                } else if (dp[j] < nmin2) {
                    nmin2 = dp[j];
                }
            }
            min = nmin;
            min2 = nmin2;
        }
        return min;
    }

}
