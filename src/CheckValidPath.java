/*
LC#1391
Given a m x n grid. Each cell of the grid represents a street. The street of grid[i][j] can be:
1 which means a street connecting the left cell and the right cell.
2 which means a street connecting the upper cell and the lower cell.
3 which means a street connecting the left cell and the lower cell.
4 which means a street connecting the right cell and the lower cell.
5 which means a street connecting the left cell and the upper cell.
6 which means a street connecting the right cell and the upper cell.


You will initially start at the street of the upper-left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.

Notice that you are not allowed to change any street.

Return true if there is a valid path in the grid or false otherwise.



Example 1:


Input: grid = [[2,4,3],[6,5,2]]
Output: true
Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).
Example 2:


Input: grid = [[1,2,1],[1,2,1]]
Output: false
Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)
Example 3:

Input: grid = [[1,1,2]]
Output: false
Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).
Example 4:

Input: grid = [[1,1,1,1,1,1,3]]
Output: true
Example 5:

Input: grid = [[2],[2],[2],[2],[2],[2],[6]]
Output: true


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
1 <= grid[i][j] <= 6
 */
public class CheckValidPath {

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[][][] nm = {
            null,
            {{1, 3}, {1, 4}, null, null},
            {null, null, {2, 5, 6}, {2, 3, 4}},
            {null, {1, 4, 6}, {2, 5, 6}, null},
            {{1, 3, 5}, null, {2, 5, 6}, null},
            {null, {1, 4, 6}, null, {2, 3, 4}},
            {{1, 3, 5}, null, null, {2, 3, 4}}};

    boolean[][] v;

    public boolean hasValidPath(int[][] g) {
        v = new boolean[g.length][g[0].length];
        return doh(g, 0, 0);
    }

    private boolean doh(int[][] g, int i, int j) {
        int m = g.length;
        int n = g[0].length;
        v[i][j] = true;
        if (i == m - 1 && j == n - 1) {
            return true;
        }
        for (int di = 0; di < dirs.length; di++) {
            int ni = i + dirs[di][0];
            int nj = j + dirs[di][1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && !v[ni][nj]) {
                int[] nextarray = nm[g[i][j]][di];
                if (nextarray == null) {
                    continue;
                }
                for (int k = 0; k < nextarray.length; k++) {
                    if (nextarray[k] == g[ni][nj]) {
                        boolean good = doh(g, ni, nj);
                        if (good) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
