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

    public static void main(String[] args) {
        System.out.println(new NumberOfCorderRectangleDp().countCornerRectangles(ArrayUtils.read("[[0,1,0],[1,0,1],[1,0,1],[0,1,0]]")));
    }
}

class NumberOfCorderRectangleDp {
    public int countCornerRectangles(int[][] grid) {
        // for each row, how many 1s will get added?
        int rows = grid.length;
        int cols = grid[0].length;
        // col1 and col2, up to now, how many shared 1s between them
        int c = 0;
        int[][] dp = new int[cols][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    for (int k = j + 1; k < cols; k++) {
                        if (grid[i][k] == 1) {
                            c += dp[j][k];
                            dp[j][k]++;
                        }
                    }
                }
            }
        }
        return c;
    }
}