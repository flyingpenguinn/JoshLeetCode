public class MinOperationsToMakeColumnStrictlyIncreasing {
    public int minimumOperations(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int res = 0;
        for (int j = 0; j < n; ++j) {
            for (int i = 1; i < m; ++i) {
                int diff = a[i - 1][j] + 1 - a[i][j];
                int added = Math.max(diff, 0);
                a[i][j] += added;
                res += added;
            }
        }
        return res;
    }
}
