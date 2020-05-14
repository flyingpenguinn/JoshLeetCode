import base.ArrayUtils;

import java.util.Arrays;

/*
LC#741
In a N x N grid representing a field of cherries, each cell is one of three possible integers.



0 means the cell is empty, so you can pass through;
1 means the cell contains a cherry, that you can pick up and pass through;
-1 means the cell contains a thorn that blocks your way.


Your task is to collect maximum number of cherries possible by following the rules below:



Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.




Example 1:

Input: grid =
[[0, 1, -1],
 [1, 0, -1],
 [1, 1,  1]]
Output: 5
Explanation:
The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.


Note:

grid is an N by N 2D array, with 1 <= N <= 50.
Each grid[i][j] is an integer in the set {-1, 0, 1}.
It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
 */
public class CherryPickup {
    // 1. handle it as if it's two people from i, j and p,q separately walk to 0 left or upward
    // 2. below is plain dp but conveys the key idea: two persons at i,j and p,q intially must move at the same time, i.e. i+j == p+q is always held
    // we can make them arrive at the same point at the same time and then only one of them pick the cherry
    // because they start from the same point, they must spend same steps (distance) in reaching a point as they can only go up or left
    // as long as they both move in the same speed, they will reach at the same time because the distance is the same
    public int cherryPickup(int[][] g) {
        int m = g.length;
        int n = g[0].length;
        dp = new int[m][n][m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        int rc = doc(g, m - 1, n - 1, m - 1, n - 1);
        return rc < 0 ? 0 : rc;
    }

    int Min = -100000000;
    int[][][][] dp;

    int doc(int[][] g, int i, int j, int p, int q) {

        if (i < 0 || j < 0 || p < 0 || q < 0) {
            return Min;
        }
        if (i == 0 && j == 0 && p == 0 && q == 0) {
            // must do this otherwise 0,0 would return Min
            return g[i][j];
        }
        if (g[i][j] < 0 || g[p][q] < 0) {
            return Min;
        }
        if (dp[i][j][p][q] != -1) {
            return dp[i][j][p][q];
        }
        int cur = g[i][j];
        if (i != p || j != q) {
            cur += g[p][q];
        }

        int max = Min;
        int w1 = doc(g, i, j - 1, p, q - 1) + cur;
        max = Math.max(max, w1);

        int w2 = doc(g, i, j - 1, p - 1, q) + cur;
        max = Math.max(max, w2);
        int w3 = doc(g, i - 1, j, p, q - 1) + cur;
        max = Math.max(max, w3);
        int w4 = doc(g, i - 1, j, p - 1, q) + cur;
        max = Math.max(max, w4);

        dp[i][j][p][q] = max;
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new CherryPickup().cherryPickup(ArrayUtils.read("[[0,1,-1],[1,0,-1],[1,1,1]]")));
        //  System.out.println(new CherryPickupBetterDp().cherryPickup(ArrayUtils.read("[[0,1,-1],[1,0,-1],[1,1,1]]")));
    }
}

class CherryPickupBetterDp {
    // i+j == p+q, so we dont really need to have q in the dp
    int[][][] dp;

    public int cherryPickup(int[][] g) {
        int m = g.length;
        int n = g[0].length;
        int total = m + n;
        dp = new int[total][m][n];
        for (int k = 0; k < total; k++) {
            for (int i = 0; i < m; i++) {
                Arrays.fill(dp[k][i], -1);
            }
        }
        int rc = doc(g, m + n - 2, m - 1, m - 1);
        return rc < 0 ? 0 : rc;
    }

    int Min = -100000000;


    int doc(int[][] g, int k, int i, int p) {
        int j = k - i;
        int q = k - p;
        if (i < 0 || j < 0 || p < 0 || q < 0) {
            return Min;
        }
        if (i == 0 && j == 0 && p == 0 && q == 0) {
            return g[i][j];
        }
        if (g[i][j] < 0 || g[p][q] < 0) {
            return Min;
        }
        if (dp[k][i][p] != -1) {
            return dp[k][i][p];
        }
        int cur = g[i][j];
        if (i != p || j != q) {
            cur += g[p][q];
        }
        int max = Min;
        // j-1, q-1
        int w1 = doc(g, k - 1, i, p) + cur;
        max = Math.max(max, w1);
        // j-1, p-1
        int w2 = doc(g, k - 1, i, p - 1) + cur;
        max = Math.max(max, w2);
        // i-1, q-1
        int w3 = doc(g, k - 1, i - 1, p) + cur;
        max = Math.max(max, w3);
        // i-1, p-1
        int w4 = doc(g, k - 1, i - 1, p - 1) + cur;
        max = Math.max(max, w4);
        dp[k][i][p] = max;
        return max;
    }
}