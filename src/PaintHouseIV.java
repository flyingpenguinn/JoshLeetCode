import java.util.Arrays;

public class PaintHouseIV {
    private long[][] dp;

    public long minCost(int n, int[][] a) {
        dp = new long[n / 2][31];
        for (int i = 0; i < dp.length; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return solve(a, 0, 30, n / 2);
    }

    private long solve(int[][] a, int i, int last, int n) {
        int an = a.length;
        if (i == n) {
            return 0L;
        }
        if (dp[i][last] != -1) {
            return dp[i][last];
        }
        int j = an - 1 - i;
        long res = (long) 1e18;
        for (int k = 0; k < 3; ++k) {
            for (int p = 0; p < 3; ++p) {
                if (k == p) {
                    continue;
                }
                int tag = k * 10 + p;
                int lastp = last % 10;
                int lastk = last / 10;
                int ccost = a[i][k] + a[j][p];
                if (last < 30 && (lastk == k || lastp == p)) {
                    continue;
                }
                final long later = solve(a, i + 1, tag, n);
                long cres = ccost + later;
                res = Math.min(res, cres);
            }
        }
        dp[i][last] = res;
        return res;
    }
}
