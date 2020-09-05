public class MatrixDiagonalSum {
    public int diagonalSum(int[][] a) {
        int m = a.length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            res += a[i][i];
            if (i != m - 1 - i) { // no duplicates...
                res += a[i][m - 1 - i];
            }
        }
        return res;
    }
}
