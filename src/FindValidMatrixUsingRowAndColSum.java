public class FindValidMatrixUsingRowAndColSum {
    // set row/col as big first then if col is too big we throw excesses to a later col
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            res[i][0] = rowSum[i];
        }
        for (int j = 0; j < n - 1; j++) {
            long csum = 0;
            for (int i = 0; i < m; i++) {
                csum += res[i][j];
            }
            // must be long to avoid overflow
            long diff = csum - colSum[j];
            for (int i = 0; i < m; i++) {
                long copy = Math.min(res[i][j], (int) Math.ceil(diff)); // we can't go negative hence...
                res[i][j] -= copy;
                res[i][j + 1] += copy;
                diff -= copy;
            }
        }
        return res;
    }
}

class FindValidMatrixEasier {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = Math.min(rowSum[i], colSum[j]);
                rowSum[i] -= res[i][j];
                colSum[j] -= res[i][j];
            }
        }
        return res;
    }
}
