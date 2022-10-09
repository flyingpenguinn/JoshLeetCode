public class MaximizeTotalTastinessOfFruits {
    private Integer[][][] dp;

    public int maxTastiness(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
        int n = price.length;
        dp = new Integer[n][maxAmount + 1][maxCoupons + 1];
        return solve(price, tastiness, maxAmount, maxCoupons, 0);
    }

    private int solve(int[] p, int[] t, int ma, int mc, int i) {
        int n = p.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][ma][mc] != null) {
            return dp[i][ma][mc];
        }
        int way1 = solve(p, t, ma, mc, i + 1);
        int way2 = 0;
        if (ma - p[i] >= 0) {
            way2 = t[i] + solve(p, t, ma - p[i], mc, i + 1);
        }
        int way3 = 0;
        int cp = p[i] / 2;
        if (mc > 0 && ma - cp >= 0) {
            way3 = t[i] + solve(p, t, ma - cp, mc - 1, i + 1);
        }
        int res = Math.max(way1, Math.max(way2, way3));
        dp[i][ma][mc] = res;
        return res;
    }
}
