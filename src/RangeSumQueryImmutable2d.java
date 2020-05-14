/*
LC#304
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQueryImmutable2d {

    // typical DP
    class NumMatrix {
        int[][] sum;

        public NumMatrix(int[][] a) {
            int m = a.length;
            if (m == 0) {
                return;
            }
            int n = a[0].length;
            sum = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 && j == 0) {
                        sum[i][j] = a[0][0];
                    } else if (i == 0) {
                        sum[i][j] = sum[i][j - 1] + a[i][j];
                    } else if (j == 0) {
                        sum[i][j] = sum[i - 1][j] + a[i][j];
                    } else {
                        sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + a[i][j];
                    }
                }
            }
        }

        public int sumRegion(int r1, int c1, int r2, int c2) {
            if (sum == null) {
                return 0;
            }
            if (r1 == 0 && c1 == 0) {
                return sum[r2][c2];
            } else if (r1 == 0) {
                return sum[r2][c2] - sum[r2][c1 - 1];
            } else if (c1 == 0) {
                return sum[r2][c2] - sum[r1 - 1][c2];
            } else {
                return sum[r2][c2] - sum[r1 - 1][c2] - sum[r2][c1 - 1] + sum[r1 - 1][c1 - 1];
            }
        }
    }
}
