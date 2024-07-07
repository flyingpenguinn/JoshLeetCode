public class CountSubmatriceWithEqualXandY {
    public int numberOfSubmatrices(char[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] xs = new int[m][n];
        int[][] ys = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                xs[i][j] = v(xs, i - 1, j) + v(xs, i, j - 1) - v(xs, i - 1, j - 1) + (a[i][j] == 'X' ? 1 : 0);
                ys[i][j] = v(ys, i - 1, j) + v(ys, i, j - 1) - v(ys, i - 1, j - 1) + (a[i][j] == 'Y' ? 1 : 0);
                if (xs[i][j] == ys[i][j] && xs[i][j] > 0) {
                    ++res;
                }
                //  System.out.println(i+" "+j+" "+xs[i][j]+" "+ys[i][j]);
            }

        }
        return res;
    }

    private int v(int[][] x, int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        return x[i][j];
    }
}
