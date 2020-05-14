import java.util.Arrays;

/*
LC#576
There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.



Example 1:

Input: m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:

Example 2:

Input: m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:



Note:

Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].
 */
public class OutOfBoundaryPaths {

    // every time we step out of boundary there is one path
    long[][][] dp;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    long Mod = 1000000007;

    public int findPaths(int m, int n, int k, int i, int j) {
        dp = new long[m][n][k + 1];
        for (int s = 0; s < m; s++) {
            for (int t = 0; t < n; t++) {
                Arrays.fill(dp[s][t], -1);
            }
        }
        return (int) dof(i, j, m, n, k);
    }

    long dof(int i, int j, int m, int n, int k) {
        if (k == 0) {
            return out(i, j, m, n) ? 1 : 0;
        }
        if (out(i, j, m, n)) {
            return 1;
        }
        if (dp[i][j][k] != -1) {
            return dp[i][j][k];
        }
        long ways = 0;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            ways += dof(ni, nj, m, n, k - 1);
            ways %= Mod;
        }
        dp[i][j][k] = ways;
        return dp[i][j][k];
    }

    boolean out(int i, int j, int m, int n) {
        return (i < 0 || i >= m || j < 0 || j >= n);
    }
}
