public class MaxSumOfHourGlass {
    public int maxSum(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int res = 0;
        for (int i = 1; i + 1 < m; ++i) {
            for (int j = 1; j + 1 < n; ++j) {
                int v1 = a[i - 1][j - 1];
                int v2 = a[i - 1][j];
                int v3 = a[i - 1][j + 1];
                int v4 = a[i][j];
                int v5 = a[i + 1][j - 1];
                int v6 = a[i + 1][j];
                int v7 = a[i + 1][j + 1];
                int cur = v1 + v2 + v3 + v4 + v5 + v6 + v7;
                res = Math.max(cur, res);
            }
        }
        return res;
    }
}
