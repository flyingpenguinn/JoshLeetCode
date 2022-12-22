public class CheckIfThereIsPathWithEqualZeroOne {
    private Integer[][][] dp;

    public boolean isThereAPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int nums = m + n - 1;
        dp = new Integer[m][n][nums * 2 + 1];
        return solve(grid, 0, 0, 0) == 1;
    }

    private int solve(int[][] a, int i, int j, int b) {
        int m = a.length;
        int n = a[0].length;

        int delta = a[i][j] == 0 ? -1 : 1;
        int nb = b + delta;
        if (i == m - 1 && j == n - 1) {
            return nb == 0 ? 1 : 0;
        }
        int rb = b + m + n - 1;
        if (dp[i][j][rb] != null) {
            return dp[i][j][rb];
        }
        int way1 = 0;
        if (i + 1 < m) {
            way1 = solve(a, i + 1, j, nb);
        }
        int way2 = 0;
        if (j + 1 < n) {
            way2 = solve(a, i, j + 1, nb);
        }
        int res = (way1 == 1 || way2 == 1) ? 1 : 0;
        dp[i][j][rb] = res;
        return res;
    }
}
