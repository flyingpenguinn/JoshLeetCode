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
    // can't use projection there could be holes
    // calc the common parts with i=1 and j-1
    public int surfaceArea(int[][] g) {
        int m = g.length;
        int n = g[0].length;
        int area = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = g[i][j];
                if (v > 0) {
                    area += 4 * v + 2;
                    if (j - 1 >= 0) {
                        int com = Math.min(g[i][j - 1], v);
                        area -= 2 * com;
                    }
                    if (i - 1 >= 0) {
                        int com = Math.min(g[i - 1][j], v);
                        area -= 2 * com;
                    }
                }
            }
        }
        return area;
    }
}
