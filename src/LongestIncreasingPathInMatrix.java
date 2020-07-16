import base.ArrayUtils;

/*
LC#329
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums =
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:

Input: nums =
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathInMatrix {
    // becaues the path could be zigzag, can't use iterative way. if it's down/right only then we can use that
    /*
    The key observation is that the sequence is strictly increasing, so it can not have loops. So we have the following:

longest(i,j) = longest increasing path from (i,j) to (k,l) + longest(k,l)
where longest(i,j) is longest increasing path starting with (i,j).
     */

    // dag, no circle!
    public int longestIncreasingPath(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return 0;
        }
        int m = a.length;
        int n = a[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = solve(a, i, j, dp);
                max = Math.max(max, cur);
            }
        }
        return max;
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int solve(int[][] a, int i, int j, int[][] dp) {
        int m = a.length;
        int n = a[0].length;
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int res = 1;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] > a[i][j]) {
                int later = solve(a, ni, nj, dp);
                res = Math.max(res, later + 1);
            }
        }
        dp[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LongestIncreasingPathInMatrix().longestIncreasingPath(ArrayUtils.read("[[7,8,9],[9,7,6],[7,2,3]]")));
    }
}