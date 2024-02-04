public class FindGridOfRegionAvg {
    public int[][] resultGrid(int[][] a, int t) {
        int m = a.length;
        int n = a[0].length;
        int[][] sum = new int[m][n];
        int[][] count = new int[m][n];
        for (int i = 1; i + 1 < m; ++i) {
            for (int j = 1; j + 1 < n; ++j) {
                if (isregion(a, i, j, t)) {
                    setregion(a, i, j, sum, count);
                }
            }
        }
        int[][] res = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (count[i][j] == 0) {
                    res[i][j] = a[i][j];
                } else {
                    res[i][j] = sum[i][j] / count[i][j];
                }
            }
        }
        return res;
    }

    private void setregion(int[][] a, int i, int j, int[][] sum, int[][] count) {
        int csum = 0;
        for (int k = i - 1; k <= i + 1; ++k) {
            for (int p = j - 1; p <= j + 1; ++p) {
                csum += a[k][p];
            }
        }
        for (int k = i - 1; k <= i + 1; ++k) {
            for (int p = j - 1; p <= j + 1; ++p) {
                sum[k][p] += csum / 9;
                ++count[k][p];
            }
        }
    }

    private boolean isregion(int[][] a, int i, int j, int t) {
        for (int k = i - 1; k <= i + 1; ++k) {
            for (int p = j - 1; p <= j + 1; ++p) {
                if (k + 1 <= i + 1 && Math.abs(a[k][p] - a[k + 1][p]) > t) {
                    return false;
                }
                if (p + 1 <= j + 1 && Math.abs(a[k][p] - a[k][p + 1]) > t) {
                    return false;
                }
            }
        }
        return true;
    }
}
