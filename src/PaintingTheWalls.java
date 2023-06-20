public class PaintingTheWalls {
    private Integer[][] dp;

    public int paintWalls(int[] a, int[] b) {
        int n = a.length;
        dp = new Integer[n][n + OFFSET];
        int[][] ps = new int[n][2];
        for (int i = 0; i < n; ++i) {
            ps[i][0] = a[i];
            ps[i][1] = b[i];
        }
        return solve(ps, 0, 0);

    }

    private int Max = (int) (1e9);
    private int OFFSET = 600;

    private int solve(int[][] ps, int i, int time) {
        int n = ps.length;
        int rem = n - i;

        if (time >= rem) {
            return 0;
        }
        if (i == n) {
            return time < 0 ? Max : 0;
        }
        if (dp[i][time + OFFSET] != null) {
            return dp[i][time + OFFSET];
        }
        int way1 = ps[i][0] + solve(ps, i + 1, time + ps[i][1]);
        int way2 = solve(ps, i + 1, time - 1);

        int res = Math.min(way1, way2);
        dp[i][time + OFFSET] = res;
        return res;
    }
}
