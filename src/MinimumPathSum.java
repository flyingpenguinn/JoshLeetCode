import base.ArrayUtils;

import java.util.Arrays;

/*
LC#64
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MinimumPathSum {
    int Max = Integer.MAX_VALUE;

    public int minPathSum(int[][] g) {
        int m = g.length;
        if (m == 0) {
            return 0;
        }
        int n = g[0].length;
        int[] dp = new int[n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    dp[j] = g[i][j];
                } else {
                    int right = j + 1 < n ? dp[j + 1] : Max;
                    // cant just = dp[j] otherwise last row==0
                    int down = i + 1 < m ? dp[j] : Max;
                    int min = Math.min(right, down);
                    dp[j] = min + g[i][j];
                }
            }
        }
        return dp[0];

    }

    public static void main(String[] args) {
        int[][] grid = ArrayUtils.read("[[1,3,1],[1,5,1],[4,2,1]]");
        System.out.println(new MinimumPathSum().minPathSum(grid));
    }
}
