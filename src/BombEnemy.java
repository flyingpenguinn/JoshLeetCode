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
    public int maxKilledEnemies(char[][] a) {
        int m = a.length;
        if (m == 0) {
            return 0;
        }
        int n = a[0].length;
        int[][] r = new int[m][n];
        // left
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 'W') {
                    sum = 0;
                } else if (a[i][j] == 'E') {
                    sum++;
                } else {
                    r[i][j] += sum;
                }
            }
        }
        // right
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (a[i][j] == 'W') {
                    sum = 0;
                } else if (a[i][j] == 'E') {
                    sum++;
                } else {
                    r[i][j] += sum;
                }
            }
        }
        // up
        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int i = 0; i < m; i++) {
                if (a[i][j] == 'W') {
                    sum = 0;
                } else if (a[i][j] == 'E') {
                    sum++;
                } else {
                    r[i][j] += sum;
                }
            }
        }
        int max = 0;
        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int i = m - 1; i >= 0; i--) {
                if (a[i][j] == 'W') {
                    sum = 0;
                } else if (a[i][j] == 'E') {
                    sum++;
                } else {
                    r[i][j] += sum;
                    max = Math.max(max, r[i][j]);
                }
            }
        }
        return max;
    }
}
