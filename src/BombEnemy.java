/*
LC#361
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note: You can only put the bomb at an empty cell.

Example:

Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
Output: 3
Explanation: For the given grid,

0 E 0 0
E 0 W E
0 E 0 0

Placing a bomb at (1,1) kills 3 enemies.
 */
public class BombEnemy {

    // lik e01 matrix and candy problem. we can segregate the dependencies on non overlapping directions...
    public class Solution {
        public int maxKilledEnemies(char[][] g) {
            int m = g.length;
            if (m == 0) return 0;
            int n = g[0].length;
            int[][] res = new int[m][n];

            // Left to right, then right to left
            calcRow(g, 0, n, 1, res);
            calcRow(g, n - 1, -1, -1, res);
            // Top to bottom, then bottom to top
            calcCol(g, 0, m, 1, res);
            calcCol(g, m - 1, -1, -1, res);

            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    ans = Math.max(ans, res[i][j]);
                }
            }
            return ans;
        }

        private void calcRow(char[][] g, int jStart, int jEnd, int jStep, int[][] res) {
            int m = g.length;
            for (int i = 0; i < m; i++) {
                int count = 0;
                for (int j = jStart; j != jEnd; j += jStep) {
                    if (g[i][j] == 'E') {
                        count++;
                    } else if (g[i][j] == 'W') {
                        count = 0;
                    } else { // empty cell '0'
                        res[i][j] += count;
                    }
                }
            }
        }

        private void calcCol(char[][] g, int iStart, int iEnd, int iStep, int[][] res) {
            int n = g[0].length;
            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int i = iStart; i != iEnd; i += iStep) {
                    if (g[i][j] == 'E') {
                        count++;
                    } else if (g[i][j] == 'W') {
                        count = 0;
                    } else { // empty cell '0'
                        res[i][j] += count;
                    }
                }
            }
        }
    }

}
