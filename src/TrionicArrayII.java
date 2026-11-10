import base.ArrayUtils;

import java.util.Arrays;

public class TrionicArrayII {
    // (i, phase) dp: st is the phase at i-1 (0 = not started, 1 = up, 2 = down, 3 = up, 4 = done)
    // kept = length of the current phase capped at 2, since every phase needs at least 2 elements
    // "continue" and "switch/stop" are independent choices: two ifs, not if/else
    private long[][][] dp;

    public long maxSumTrionic(int[] a) {
        int n = a.length;
        dp = new long[n][5][3];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 5; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return solve(a, 0, 0, 0);
    }

    private long Min = (long) -1e18;

    private long solve(int[] a, int i, int st, int kept) {
        int n = a.length;
        kept = Math.min(kept, 2);
        if (st == 4) {
            return 0;
        }
        if (i == n) {
            return st == 3 || st == 4 ? 0 : Min;
        }
        if (dp[i][st][kept] != -1) {
            return dp[i][st][kept];
        }
        long way1 = Min;
        long way2 = Min;
        if (st == 0) {
            way1 = solve(a, i + 1, 0, 0);
            way2 = a[i] + solve(a, i + 1, 1, 1);
        } else if (st == 1) {
            if (a[i] > a[i - 1]) {
                way1 = a[i] + solve(a, i + 1, 1, kept + 1);
            }
            if (a[i] < a[i - 1] && kept > 1) {
                way2 = a[i] + solve(a, i + 1, 2, 2);
            }
        } else if (st == 2) {
            if (a[i] < a[i - 1]) {
                way1 = a[i] + solve(a, i + 1, 2, kept + 1);
            }
            if (a[i] > a[i - 1] && kept > 1) {
                way2 = a[i] + solve(a, i + 1, 3, 2);
            }
        } else if (st == 3) {
            if (a[i] > a[i - 1]) {
                way1 = a[i] + solve(a, i + 1, 3, kept + 1);
            }
            if (kept > 1) {
                way2 = solve(a, i + 1, 4, 0);
            }
        }
        long res = Math.max(way1, way2);
        dp[i][st][kept] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new TrionicArrayII().maxSumTrionic(ArrayUtils.read1d("[2,993,-791,-635,-569]")));
    }
}

class TrionicArrayIIKadane {
    // bottom-up: best sum ending at i in phase 1 / 2 / 3. Start phase 1 with the pair (a[i-1], a[i]) so no length counter is needed
    public long maxSumTrionic(int[] a) {
        int n = a.length;
        long Min = (long) -1e18;
        long inc1 = Min, dec = Min, inc2 = Min, res = Min;
        for (int i = 1; i < n; ++i) {
            long ni1 = Min, nd = Min, ni2 = Min;
            if (a[i] > a[i - 1]) {
                ni1 = Math.max(inc1, a[i - 1]) + a[i];
                ni2 = Math.max(inc2, dec) + a[i];
            } else if (a[i] < a[i - 1]) {
                nd = Math.max(dec, inc1) + a[i];
            }
            inc1 = ni1;
            dec = nd;
            inc2 = ni2;
            res = Math.max(res, inc2);
        }
        return res;
    }
}
