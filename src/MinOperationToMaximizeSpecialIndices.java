import java.util.Arrays;
import java.util.Map;

public class MinOperationToMaximizeSpecialIndices {
    private long Max = (long) 1e18;
    private long Min = -Max;
    private long Mod = (long) (1e9 + 7);

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public long minIncrease(int[] a) {
        int n = a.length;
        long way1 = solve(a, 1);
        if (n % 2 == 1) {
            return way1;
        } else {

            dp = new long[n][2];
            for (int i = 0; i < n; ++i) {

                Arrays.fill(dp[i], -1);

            }
            long way2 = solvedp(a, 1, 1);
            long way3 = solvedp(a, 2, 0);
            return Math.min(way2, way3);
        }
    }

    private long[][] dp;

    private long solvedp(int[] a, int i, int remskip) {
        int n = a.length;
        if (i >= n - 1) {
            return 0;
        }
        if (dp[i][remskip] != -1) {
            return dp[i][remskip];
        }
        int maxv = Math.max(a[i - 1], a[i + 1]);
        long exp = maxv + 1;
        long cv = a[i];
        long diff = Math.max(0, exp - cv);
        long way1 = diff + solvedp(a, i + 2, remskip);
        long way2 = Max;
        if (remskip > 0) {
            way2 = solvedp(a, i + 1, remskip - 1);
        }
        long cur = Math.min(way1, way2);
        dp[i][remskip] = cur;
        return cur;
    }

    private long solve(int[] a, int start) {
        int n = a.length;
        long res = 0;

        for (int i = start; i < n - 1; i += 2) {
            int maxv = Math.max(a[i - 1], a[i + 1]);
            long exp = maxv + 1;
            long cv = a[i];
            long diff = Math.max(0, exp - cv);
            res += diff;
        }
        return res;
    }
}
