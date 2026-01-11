
/*
LC#308
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
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQueryMutable2d {

    // 2d BIT, o(logm)* o(lgn) for updates and queries
    class NumMatrix {
        int[][] a;
        int[][] oa;

        public NumMatrix(int[][] input) {
            int rs = input.length;
            int cs = input[0].length;
            a = new int[rs + 1][cs + 1];
            oa = input;
            for (int i = 0; i < rs; i++) {
                for (int j = 0; j < cs; j++) {
                    // index from 1
                    u(i + 1, j + 1, oa[i][j], 0);
                }
            }
        }

        public void update(int i, int j, int v) {
            u(i + 1, j + 1, v, oa[i][j]);
            oa[i][j] = v;
        }

        private void u(int i, int j, int v, int ov) {
            int d = v - ov;
            int oj = j;
            while (i < a.length) {
                j = oj;
                // must reset j !
                // 3,6-->3,8; 3,6-->4,6-->4,8
                while (j < a[0].length) {
                    a[i][j] += d;
                    j += j & (-j);
                }
                i += i & (-i);
            }
        }

        private int sumRegion(int r1, int c1, int r2, int c2) {
            int s1 = p(r2 , c2 );
            int s2 = p(r1-1, c2 );
            int s3 = p(r2, c1-1);
            int s4 = p(r1-1, c1-1);
            return s1 - s2 - s3 + s4;
        }

        private int p(int i, int j) {
            int oj = j;
            int r = 0;
            while (i > 0) {
                j = oj;
                while (j > 0) {
                    r += a[i][j];
                    j -= (j & (-j));
                }
                i -= (i & (-i));
            }
            return r;
        }
    }

    /**
     * Your NumMatrix object will be instantiated and called as such:
     * NumMatrix obj = new NumMatrix(matrix);
     * obj.update(row,col,val);
     * int param_2 = obj.sumRegion(row1,col1,row2,col2);
     */


// use the way similar to 2d max subarray sum. o(row) for updates, or o(col) for queries
    class NumMatrixOn {
        int[][] a;
        int[][] sum;

        public NumMatrixOn(int[][] m) {
            this.a = m;
            sum = new int[a.length][a[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    int pre = i == 0 ? 0 : sum[i - 1][j];
                    sum[i][j] = pre + a[i][j];
                }
            }
        }

        public void update(int row, int col, int val) {
            a[row][col] = val;
            for (int i = row; i < a.length; i++) {
                sum[i][col] += val - a[i][col];
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sumcol1 = 0;
            int sumall = 0;
            for (int j = 0; j < col2; j++) {
                int item = sum[row2][j] - (row1 == 0 ? 0 : sum[row1 - 1][j]);
                sumall += item;
                if (j == col1 - 1) {
                    sumcol1 = sumall;
                }
            }
            return sumall - sumcol1;
        }
    }

}

