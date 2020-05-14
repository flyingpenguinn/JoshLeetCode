/*
LC#62

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 7 x 3 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28
 */

public class UniquePaths {

    //with rolling array to save space
    // space must be On as we accumulate values through 2 loops. so can't simplify further. if it's single loop then O1 is possible
    public int uniquePaths(int m, int n) {
        // because we do rows first
        int[] dp = new int[n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    dp[j] = 1;
                } else {
                    dp[j] += (j + 1 < n) ? dp[j + 1] : 0;
                }
            }
        }
        return dp[0];
    }
}

class UniquePathsBottomUp {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = 1;
                } else {
                    int right = j + 1 < n ? dp[i][j + 1] : 0;
                    int down = i + 1 < m ? dp[i + 1][j] : 0;
                    dp[i][j] = right + down;
                }
            }
        }
        return dp[0][0];
    }
}

class UniquePathsMemoization {
    int[][] dp = null;

    public int uniquePaths(int m, int n) {
        dp = new int[m][n];
        return doUniquePath(0, 0, m - 1, n - 1);
    }

    private int doUniquePath(int rs, int cs, int re, int ce) {
        if (rs == cs && cs == ce) {
            return 1;
        }
        if (dp[rs][cs] != 0) {
            return dp[rs][cs];
        }
        int count = 0;
        if (rs + 1 <= re) {
            count += doUniquePath(rs + 1, cs, re, ce);
        }
        if (cs + 1 <= ce) {
            count += doUniquePath(rs, cs + 1, re, ce);
        }
        dp[rs][cs] = count;
        return count;
    }
}
