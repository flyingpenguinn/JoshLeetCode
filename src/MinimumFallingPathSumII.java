import java.util.Arrays;

/*
LC#1289
Given a square grid of integers arr, a falling path with non-zero shifts is a choice of exactly one element from each row of arr, such that no two elements chosen in adjacent rows are in the same column.

Return the minimum sum of a falling path with non-zero shifts.



Example 1:

Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
Output: 13
Explanation:
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is [1,5,7], so the answer is 13.


Constraints:

1 <= arr.length == arr[i].length <= 200
-99 <= arr[i][j] <= 99
 */
public class MinimumFallingPathSumII {
    int[][] dp;

    public int minFallingPathSum(int[][] a) {
        int rows = a.length;
        int cols = a[0].length;
        dp = new int[rows][cols + 1];
        if (rows == 0) {
            return 0;
        }
        for (int i = 0; i < rows; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        return path(a, 0, cols);
    }

    // calculating i row, at j in i-1 row
    private int path(int[][] a, int i, int j) {
        int rows = a.length;
        int cols = a[0].length;
        if (i == rows) {
            return 0;
        }
        if (dp[i][j] != Integer.MIN_VALUE) {
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < cols; k++) {
            if (k == j) {
                continue;
            }
            int cur = a[i][k] + path(a, i + 1, k);
            min = Math.min(min, cur);
        }
        dp[i][j] = min;
        return min;
    }
}
