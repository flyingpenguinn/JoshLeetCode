import java.util.Arrays;

public class MaxConsistentColsInGrid {
    private int[][] dp;

    public int maxConsistentColumns(int[][] a, int limit) {
        int m = a.length;
        int n = a[0].length;
        dp = new int[n][n + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return solve(a, 0, n, limit);
    }

    private int solve(int[][] a, int j, int pre, int limit) {
        int m = a.length;
        int n = a[0].length;
        if (j == n) {
            return 0;
        }
        if (dp[j][pre] != -1) {
            return dp[j][pre];
        }
        int way1 = solve(a, j + 1, pre, limit);
        int way2 = 0;
        if (pre == n) {
            way2 = 1 + solve(a, j + 1, j, limit);
        } else {
            boolean bad = false;
            for (int i = 0; i < m; ++i) {
                if (Math.abs(a[i][j] - a[i][pre]) > limit) {
                    bad = true;
                    break;
                }
            }
            if (!bad) {
                way2 = 1 + solve(a, j + 1, j, limit);
            }
        }
        int res = Math.max(way1, way2);
        dp[j][pre] = res;
        return res;
    }
}
