/*
LC#892
On a N * N grid, we place some 1 * 1 * 1 cubes.

Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).

Return the total surface area of the resulting shapes.



Example 1:

Input: [[2]]
Output: 10
Example 2:

Input: [[1,2],[3,4]]
Output: 34
Example 3:

Input: [[1,0],[0,2]]
Output: 16
Example 4:

Input: [[1,1,1],[1,0,1],[1,1,1]]
Output: 32
Example 5:

Input: [[2,2,2],[2,1,2],[2,2,2]]
Output: 46


Note:

1 <= N <= 50
0 <= grid[i][j] <= 50
 */
public class SurfaceAreaOf3DShapes {
    // count he parts where this cell is bigger than neighbors
    public int surfaceArea(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int res = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    continue;
                }
                res += 2;
                for (int[] d : dirs) {
                    int ni = i + d[0];
                    int nj = j + d[1];
                    if (ni < 0 || ni >= m || nj < 0 || nj >= n) {
                        res += a[i][j];
                    } else {
                        // if < then 0 can't over subtract
                        res += Math.max(a[i][j] - a[ni][nj], 0);
                    }
                }
            }
        }
        return res;
    }
}
