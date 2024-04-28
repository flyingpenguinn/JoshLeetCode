public class RightTriangles {
    public long numberOfRightTriangles(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        long[] cs = new long[n];
        long[] rs = new long[m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == 1) {
                    ++cs[j];
                    ++rs[i];
                }
            }
        }
        long res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == 1) {
                    long cc = cs[j] - 1;
                    long rc = rs[i] - 1;
                    res += cc * rc;
                }
            }
        }
        return res;
    }
}
