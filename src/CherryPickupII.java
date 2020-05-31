import java.util.Arrays;

public class CherryPickupII {
    // they can't walk to the same position
    int[][][] dp;

    public int cherryPickup(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        dp = new int[m][n][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return dom(a, 0, 0, a[0].length - 1);
    }

    int[] dirs = {0, 1, -1};

    private int dom(int[][] a, int r, int c1, int c2) {
        int m = a.length;
        int n = a[0].length;
        if (c1 == c2 || c1 < 0 || c1 >= n || c2 < 0 || c2 >= n) {
            return Integer.MIN_VALUE;
        }
        if (r == m) {
            return 0;
        }
        if (dp[r][c1][c2] != -1) {
            return dp[r][c1][c2];
        }
        int max = 0;
        int curpick = a[r][c1] + a[r][c2];
        for (int d1 : dirs) {
            for (int d2 : dirs) {
                int nc1 = c1 + d1;
                int nc2 = c2 + d2;
                int cur = dom(a, r + 1, nc1, nc2);
                max = Math.max(max, cur);
            }
        }
        dp[r][c1][c2] = curpick + max;
        return dp[r][c1][c2];
    }
}
