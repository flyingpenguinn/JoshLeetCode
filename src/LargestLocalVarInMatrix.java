public class LargestLocalVarInMatrix {
    public int[][] largestLocal(int[][] a) {
        int n = a.length;
        int[][] res = new int[n - 3 + 1][n - 3 + 1];
        for (int i = 0; i + 3 - 1 < n; ++i) {
            for (int j = 0; j + 3 - 1 < n; ++j) {
                int max = 0;
                for (int k = i; k < i + 3; ++k) {
                    for (int p = j; p < j + 3; ++p) {
                        max = Math.max(max, a[k][p]);
                    }
                }
                res[i][j] = max;
            }
        }
        return res;
    }
}
