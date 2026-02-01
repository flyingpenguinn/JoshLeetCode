import java.util.Arrays;

public class MaxPartitionScore {
    // TODO convex optimize dp
    public long minPartitionScore(int[] a, int k) {
        int n = a.length;

        long[] p = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] + a[i - 1];
        }

        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        Arrays.fill(prev, INF);
        prev[0] = 0;

        for (int t = 1; t <= k; t++) {
            long[] cur = new long[n + 1];
            Arrays.fill(cur, INF);
            solve(t, n, t - 1, n - 1, prev, cur, p);
            prev = cur;
        }

        return prev[n];
    }

    private void solve(int l, int r, int optL, int optR, long[] prev, long[] cur, long[] p) {
        if (l > r) {
            return;
        }

        int m = (l + r) >>> 1;

        int end = Math.min(optR, m - 1);
        long bestV = Long.MAX_VALUE / 4;
        int bestK = optL;

        for (int j = optL; j <= end; j++) {
            long v = prev[j] + cost(j, m, p);
            if (v < bestV) {
                bestV = v;
                bestK = j;
            }
        }

        cur[m] = bestV;

        solve(l, m - 1, optL, bestK, prev, cur, p);
        solve(m + 1, r, bestK, optR, prev, cur, p);
    }

    private long cost(int j, int i, long[] p) {
        long s = p[i] - p[j];
        return s * (s + 1) / 2;
    }
}
