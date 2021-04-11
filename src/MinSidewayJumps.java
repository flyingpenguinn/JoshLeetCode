public class MinSidewayJumps {
    private Integer[][] dp;

    public int minSideJumps(int[] a) {
        dp = new Integer[a.length][4];
        return solve(a, 0, 1);
    }

    private int Max = 10000000;

    // position, lane. note lane here is 0,1,2 but a[i] is 1,2,3 hence -1
    private int solve(int[] a, int i, int j) {
        int n = a.length;
        if (i == n - 1) {
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int way1 = Max;
        if (a[i + 1] - 1 != j) {
            way1 = solve(a, i + 1, j);
        }
        int way2 = Max;
        int j1 = (j + 1) % 3;
        // shouldnt jump to blocked cell or equally bad cell
        if (a[i] - 1 != j1 && a[i + 1] - 1 != j1) {
            way2 = 1 + solve(a, i + 1, j1);
        }
        int way3 = Max;
        int j2 = (j + 2) % 3;
        if (a[i + 1] - 1 != j2 && a[i] - 1 != j2) {
            way3 = 1 + solve(a, i + 1, j2);
        }
        int rt = Math.min(way1, Math.min(way2, way3));
        dp[i][j] = rt;
        return rt;
    }
}
