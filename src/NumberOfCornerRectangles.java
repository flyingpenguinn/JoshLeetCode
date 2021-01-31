import base.ArrayUtils;

public class NumberOfCornerRectangles {
    // like 2d max subarray, loop begin and end rows, and loop on col
    public int countCornerRectangles(int[][] grid) {
        // enumerate right bottom corner
        int rows = grid.length;
        int cols = grid[0].length;
        int c = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < rows; j++) {
                int cc = 0;
                for (int k = 0; k < cols; k++) {
                    if (grid[i][k] == grid[j][k] && grid[i][k] == 1) {
                        cc++;
                    }
                }
                c += cc * (cc - 1) / 2;
            }
        }
        return c;
    }
}
