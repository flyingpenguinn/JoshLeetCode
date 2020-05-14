/*
LC#1254
Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.



Example 1:



Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation:
Islands in gray are closed because they are completely surrounded by water (group of 1s).
Example 2:



Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:

Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2


Constraints:

1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1
 */
public class NumberOfClosedIsland {
    // how many islands don't have connection to the border
    int rows = 0;
    int cols = 0;

    public int closedIsland(int[][] g) {
        rows = g.length;
        cols = g[0].length;
        int r = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (g[i][j] == 0) {
                    boolean c = dfs(g, i, j);
                    if (c) {
                        r++;
                    }
                }
            }
        }
        return r;

    }

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    boolean dfs(int[][] g, int i, int j) {
        g[i][j] = 2;
        boolean rt = i > 0 && i < rows - 1 && j > 0 && j < cols - 1;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < rows && nj >= 0 && nj < cols && g[ni][nj] == 0) {
                boolean cr = dfs(g, ni, nj);
                rt = rt && cr;
            }
        }
        return rt;
    }

}
