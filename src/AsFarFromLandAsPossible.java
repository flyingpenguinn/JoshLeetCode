/*
LC#1162
Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized and return the distance.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

If no land or water exists in the grid, return -1.



Example 1:



Input: [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation:
The cell (1, 1) is as far as possible from all the land with distance 2.
Example 2:



Input: [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation:
The cell (2, 2) is as far as possible from all the land with distance 4.


Note:

1 <= grid.length == grid[0].length <= 100
grid[i][j] is 0 or 1
 */

public class AsFarFromLandAsPossible {
    // almost same as LC#542 01 matrix
    // first iteration from top to bottom to check upper/lefter
    int Max = 1000000;

    public int maxDistance(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    int up = i == 0 ? Max : dist[i - 1][j];
                    int left = j == 0 ? Max : dist[i][j - 1];
                    dist[i][j] = Math.min(up, left) + 1;
                    //  System.out.println("r1 " +i+" "+j+" "+dist[i][j]);
                }

            }
        }
        // then from the bottom to check lower/righer
        int max = -1;
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    int down = i == rows - 1 ? Max : dist[i + 1][j];
                    int right = j == cols - 1 ? Max : dist[i][j + 1];
                    int mindr = Math.min(down, right) + 1;
                    dist[i][j] = Math.min(dist[i][j], mindr);
                    max = Math.max(max, dist[i][j]);
                    //  System.out.println("r2 "+i+" "+j+" "+dist[i][j]);

                }
            }
        }
        if (max >= Max) {
            // no land
            return -1;
        }
        return max;
    }
}
