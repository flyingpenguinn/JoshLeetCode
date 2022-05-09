public class CheckValidParenthesisStringPath {
    private Integer[][][] dp;

    public boolean hasValidPath(char[][] a) {
        int m = a.length;
        int n = a[0].length;
        dp = new Integer[m][n][m + n + 1];
        int start = a[0][0] == '(' ? 1 : -1;
        return solve(a, 0, 0, start) == 1;
    }

    // k is open left
    private int solve(char[][] a, int i, int j, int k) {
        int m = a.length;
        int n = a[0].length;
        if (k < 0) {
            return 0;
        }
        if (i == m - 1 && j == n - 1) {
            return k == 0 ? 1 : 0;
        }
        if (dp[i][j][k] != null) {
            return dp[i][j][k];
        }
        int way1 = 0;
        int way2 = 0;
        if (j + 1 < n) {
            int nk = a[i][j + 1] == '(' ? 1 : -1;
            way1 = solve(a, i, j + 1, k + nk);
        }
        if (i + 1 < m) {
            int nk = a[i + 1][j] == '(' ? 1 : -1;
            way2 = solve(a, i + 1, j, k + nk);
        }
        dp[i][j][k] = (way1 == 1 || way2 == 1) ? 1 : 0;
        return dp[i][j][k];
    }
}
