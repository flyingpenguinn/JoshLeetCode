import java.util.ArrayList;
import java.util.List;

/*

LC#1260
Given a 2D grid of size n * m and an integer k. You need to shift the grid k times.

In one shift operation:

Element at grid[i][j] becomes at grid[i][j + 1].
Element at grid[i][m - 1] becomes at grid[i + 1][0].
Element at grid[n - 1][m - 1] becomes at grid[0][0].
Return the 2D grid after applying shift operation k times.



Example 1:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[9,1,2],[3,4,5],[6,7,8]]
Example 2:


Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
Example 3:

Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
Output: [[1,2,3],[4,5,6],[7,8,9]]


Constraints:

1 <= grid.length <= 50
1 <= grid[i].length <= 50
-1000 <= grid[i][j] <= 1000
0 <= k <= 100
 */
public class Shift2DGrid {
    //TODO do in better way
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;

        while (k > 0) {
            int[] lastcol = new int[rows];
            for (int i = 0; i < rows; i++) {
                lastcol[i] = grid[i][cols - 1];  // need to remember last col otherwise it's overridden...
            }
            for (int i = 0; i < rows; i++) {
                for (int j = cols - 1; j >= 1; j--) {
                    grid[i][j] = grid[i][j - 1];
                }
            }
            for (int i = rows - 1; i >= 1; i--) {
                grid[i][0] = lastcol[i - 1];
            }
            grid[0][0] = lastcol[rows - 1];
            k--;
        }
        List<List<Integer>> lr = new ArrayList<>();
        for (int[] ri : grid) {
            List<Integer> lri = new ArrayList<>();
            for (int i = 0; i < ri.length; i++) {
                lri.add(ri[i]);
                ;
            }
            lr.add(lri);
        }
        return lr;
    }
}
