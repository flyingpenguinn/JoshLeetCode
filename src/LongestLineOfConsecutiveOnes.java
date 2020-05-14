import static java.lang.Math.*;

/*
LC562

Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3
Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class LongestLineOfConsecutiveOnes {
    // check line length when a cell is its end point
    // only rely on i-1 so can convert to 2d dp
    public int longestLine(int[][] a) {
        int m = a.length;
        if (m == 0) {
            return 0;
        }
        int n = a[0].length;
        // i,j as right bottom, row direction, col direction, and diagonal
        int[][][] dp = new int[m][n][4];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    continue;
                }
                dp[i][j][0] = (i == 0 ? 0 : dp[i - 1][j][0]) + 1;
                dp[i][j][1] = (j == 0 ? 0 : dp[i][j - 1][1]) + 1;
                dp[i][j][2] = ((i == 0 || j == 0) ? 0 : dp[i - 1][j - 1][2]) + 1;
                dp[i][j][3] = ((i == 0 || j + 1 == n) ? 0 : dp[i - 1][j + 1][3]) + 1;
                for (int k = 0; k < 4; k++) {
                    max = Math.max(max, dp[i][j][k]);
                }
            }
        }
        return max;
    }
}
