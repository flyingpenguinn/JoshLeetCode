public class MaxCoinCollection {
    private Long[][][][] dp;

    public long maxCoins(int[] lane1, int[] lane2) {
        int n = lane1.length;
        long[][] lane = new long[2][n];
        for (int i = 0; i < n; ++i) {
            lane[0][i] = lane1[i];
            lane[1][i] = lane2[i];
        }
        dp = new Long[n][2][3][2];
        long res = Min;
        for (int i = 0; i < n; ++i) {
            long cur = solve(lane, i, 0, 2, 0);
            res = Math.max(res, cur);
        }
        return res;
    }

    private long Min = (long) -1e18;

    private long solve(long[][] lane, int i, int j, int s, int k) {
        int n = lane[0].length;
        if (s < 0) {
            return Min;
        }
        if (i == n) {
            return k == 1 ? 0 : Min;
        }
        if (dp[i][j][s][k] != null) {
            return dp[i][j][s][k];
        }
        long way1 = lane[j][i] + solve(lane, i + 1, j, s, 1);
        long way2 = solve(lane, i, j ^ 1, s - 1, k);
        long way3 = k == 1 ? 0 : Min;
        long res = Math.max(way1, Math.max(way2, way3));
        dp[i][j][s][k] = res;
        return res;
    }
}
