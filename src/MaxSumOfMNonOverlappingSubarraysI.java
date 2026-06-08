import java.util.Map;
import java.util.TreeMap;

public class MaxSumOfMNonOverlappingSubarraysI {
    private void update(Map<Long, Long> m, long k, long d) {
        long nv = m.getOrDefault(k, 0L) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    private long pv(long[] psum, int i) {
        return i < 0 ? 0 : psum[i];
    }

    public long maximumSum(int[] a, int m, int l, int r) {
        int n = a.length;
        // we are trying to pick pth array from ith index.
        // note for 0 we return 0
        long[][] dp = new long[n + 1][m + 1];

        long[] psum = new long[n];
        for (int i = 0; i < n; ++i) {
            psum[i] = (i == 0 ? 0 : psum[i - 1]) + a[i];
        }
        for (int p = 1; p <= m; ++p) {
            TreeMap<Long, Long> tm = new TreeMap<>();
            dp[n][p] = Min;
            // if in the end we pick nothing then we are not picking pth, so Min
            for (int i = n - 1; i >= 0; --i) {

                int tail = i + r + 1;
                if (tail <= n) {
                    long tv = pv(psum, tail - 1) + dp[tail][p - 1];
                    update(tm, tv, -1);
                }
                int head = i + l;
                if (head <= n) {
                    long hv = pv(psum, head - 1) + dp[head][p - 1];
                    update(tm, hv, 1);
                }
                long way2 = tm.isEmpty() ? Min : tm.lastKey() - pv(psum, i - 1);
                long way1 = dp[i + 1][p];
                long cur = Math.max(way1, way2);
                dp[i][p] = cur;
            }
        }
        long res = Min;
        for (int p = 1; p <= m; ++p) {
            res = Math.max(res, dp[0][p]);
        }
        return res;
    }

    private long Min = (long) -1e18;
}
