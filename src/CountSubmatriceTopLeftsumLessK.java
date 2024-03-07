public class CountSubmatriceTopLeftsumLessK {
    public int countSubmatrices(int[][] a, int k) {
        int m = a.length;
        int n = a[0].length;
        int[][] sum = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 && j == 0) {
                    sum[i][j] = a[i][j];
                } else if (i == 0) {
                    sum[i][j] = sum[i][j - 1] + a[i][j];
                } else if (j == 0) {
                    sum[i][j] = sum[i - 1][j] + a[i][j];
                } else {
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + a[i][j];
                }
                if (sum[i][j] <= k) {
                    ++res;
                }
            }

        }
        return res;
    }
}
