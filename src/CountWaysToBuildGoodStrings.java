public class CountWaysToBuildGoodStrings {
    private long Mod = (long) (1e9 + 7);
    private Long[] dp;

    public int countGoodStrings(int low, int high, int zero, int one) {
        dp = new Long[high + 1];
        return (int) solve(0, low, high, zero, one);
    }

    private long solve(int i, int l, int h, int zc, int oc) {
        // System.out.println(i);
        long res = 0;
        if (i >= h + 1) {
            return 0;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        if (i == h) {
            return 1L;
        } else if (i >= l) {
            // must be >=l. note we are dealing with ith position now. we do not have the ith position filled yet
            res = 1;
        }
        long way1 = solve(i + zc, l, h, zc, oc);
        long way2 = solve(i + oc, l, h, zc, oc);
        res += way1;
        res %= Mod;
        res += way2;
        res %= Mod;
        // System.out.println(i+" "+res);
        dp[i] = res;
        return res;
    }
}
