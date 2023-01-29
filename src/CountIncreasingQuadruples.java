public class CountIncreasingQuadruples {
    public long countQuadruplets(int[] a) {
        int n = a.length;
        int[][] smaller = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                smaller[i][j] = (j == 0 ? 0 : smaller[i][j - 1]);
                if (a[j] < a[i]) {
                    ++smaller[i][j];
                }
            }
        }
        //   System.out.println(Arrays.deepToString(smaller));
        int[][] bigger = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j > i; --j) {
                bigger[i][j] = (j == n - 1 ? 0 : bigger[i][j + 1]);
                if (a[j] > a[i]) {
                    ++bigger[i][j];
                }
            }
        }

        //   System.out.println(Arrays.deepToString(smaller));
        long res = 0;
        for (int j = 0; j < n; ++j) {
            for (int k = j + 1; k < n; ++k) {
                if (a[j] > a[k]) {
                    long way1 = (j == 0 ? 0 : smaller[k][j - 1]);
                    long way2 = (k == n - 1 ? 0 : bigger[j][k + 1]);
                    res += way1 * way2;
                }
            }
        }
        return res;
    }
}
