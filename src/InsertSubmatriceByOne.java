public class InsertSubmatriceByOne {
    // just iterate each row to handle 1d...
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] mat = new int[n][n];
        for (int[] q : queries) {
            int x1 = q[0];
            int y1 = q[1];
            int x2 = q[2];
            int y2 = q[3];
            for (int i = x1; i <= x2; ++i) {
                ++mat[i][y1];
                if (y2 + 1 < n) {
                    --mat[i][y2 + 1];
                }
            }
        }
        int[][] res = new int[n][n];
        for (int i = 0; i < n; ++i) {
            int sum = 0;
            for (int j = 0; j < n; ++j) {
                sum += mat[i][j];
                res[i][j] = sum;
            }
        }
        return res;

    }
}
