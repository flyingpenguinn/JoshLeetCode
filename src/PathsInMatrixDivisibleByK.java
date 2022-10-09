public class PathsInMatrixDivisibleByK {
    private Long[][][] dp;
    private long mod = (long) (1e9 + 7);

    public int numberOfPaths(int[][] a, int k) {
        int m = a.length;
        int n = a[0].length;
        dp = new Long[m][n][k];
        return (int) solve(a, k, 0, 0, 0);
    }


    private long solve(int[][] a, int k, int i, int j, int cur) {
        int m = a.length;
        int n = a[0].length;
        cur += a[i][j];
        cur %= k;
        long res = 0;
        if (i == m - 1 && j == n - 1) {
            return cur == 0 ? 1 : 0;
        }
        if (dp[i][j][cur] != null) {
            return dp[i][j][cur];
        }
        if (i == m - 1) {
            res = solve(a, k, i, j + 1, cur);
        } else if (j == n - 1) {
            res = solve(a, k, i + 1, j, cur);
        } else {
            res = solve(a, k, i, j + 1, cur) + solve(a, k, i + 1, j, cur);
        }
        res %= mod;
        dp[i][j][cur] = res;
        return res;
    }
}
