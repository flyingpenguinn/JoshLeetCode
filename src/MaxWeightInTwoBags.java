public class MaxWeightInTwoBags {
    private Integer[][][] dp;

    public int maxWeight(int[] a, int w1, int w2) {
        int n = a.length;
        dp = new Integer[n][w1 + 1][w2 + 1];
        return solve(a, 0, w1, w2);
    }

    private int solve(int[] a, int i, int w1, int w2) {
        int n = a.length;
        if (w1 < 0 || w2 < 0) {
            return Integer.MIN_VALUE;
        }
        if (i == n) {
            return 0;
        }
        if (dp[i][w1][w2] != null) {
            return dp[i][w1][w2];
        }
        int way1 = solve(a, i + 1, w1, w2);
        int way2 = a[i] + solve(a, i + 1, w1 - a[i], w2);
        int way3 = a[i] + solve(a, i + 1, w1, w2 - a[i]);
        int res = Math.max(way1, Math.max(way2, way3));
        dp[i][w1][w2] = res;
        return res;
    }
}
