public class NumberOfIncreasingPathsInGrid {
    private Long[][] dp;
    private long mod = (long) (1e9 + 7);
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int countPaths(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        dp = new Long[m][n];
        long res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res += solve(a, i, j);
                res %= mod;
            }
        }
        return (int) res;
    }

    private long solve(int[][] a, int i, int j) {
        int m = a.length;
        int n = a[0].length;
        long res = 1;
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] > a[i][j]) {
                res += solve(a, ni, nj);
                res %= mod;
            }
        }
        dp[i][j] = res;
        return res;
    }
}
