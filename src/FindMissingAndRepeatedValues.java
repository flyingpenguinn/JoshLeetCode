public class FindMissingAndRepeatedValues {
    public int[] findMissingAndRepeatedValues(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] c = new int[m * n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ++c[a[i][j]];
            }
        }
        int[] res = new int[2];
        for (int i = 1; i <= m * n; ++i) {
            if (c[i] == 2) {
                res[0] = i;
            } else if (c[i] == 0) {
                res[1] = i;
            }
        }
        return res;
    }
}
