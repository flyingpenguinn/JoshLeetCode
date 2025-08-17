import java.util.Arrays;

public class MinCostPathWithTeleports {
    public int minCost(int[][] g, int k) {
        int m = g.length;
        int n = g[0].length;
        int l = m * n;
        int[] xs = new int[l];
        int[] ys = new int[l];
        int[] vs = new int[l];
        int p = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                xs[p] = i;
                ys[p] = j;
                vs[p] = g[i][j];
                ++p;
            }
        }
        Integer[] ord = new Integer[l];
        for (int i = 0; i < l; ++i) {
            ord[i] = i;
        }
        Arrays.sort(ord, (a, b) -> Integer.compare(vs[a], vs[b]));
        int[] rk = new int[l];
        int u = 0;
        for (int i = 0; i < l; ++i) {
            if (i == 0 || vs[ord[i]] != vs[ord[i - 1]]) {
                ++u;
            }
            rk[ord[i]] = u - 1;
        }
        long inf = (long) 4e18;
        long[][] d = new long[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(d[i], inf);
        }
        d[0][0] = 0;
        long ans = inf;
        for (int t = 0; t <= k; ++t) {
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i > 0) {
                        d[i][j] = Math.min(d[i][j], d[i - 1][j] + g[i][j]);
                    }
                    if (j > 0) {
                        d[i][j] = Math.min(d[i][j], d[i][j - 1] + g[i][j]);
                    }
                }
            }
            ans = Math.min(ans, d[m - 1][n - 1]);
            if (t == k) {
                break;
            }
            long[] bm = new long[u];
            Arrays.fill(bm, inf);
            for (int idx = 0; idx < l; ++idx) {
                int r = rk[idx];
                int i = xs[idx];
                int j = ys[idx];
                if (d[i][j] < bm[r]) {
                    bm[r] = d[i][j];
                }
            }
            long[] sb = new long[u];
            for (int r = u - 1; r >= 0; --r) {
                if (r == u - 1) {
                    sb[r] = bm[r];
                } else {
                    sb[r] = Math.min(bm[r], sb[r + 1]);
                }
            }
            long[][] nd = new long[m][n];
            for (int i = 0; i < m; ++i) {
                Arrays.fill(nd[i], inf);
            }
            for (int idx = 0; idx < l; ++idx) {
                int r = rk[idx];
                int i = xs[idx];
                int j = ys[idx];
                long v = Math.min(d[i][j], sb[r]);
                nd[i][j] = v;
            }
            d = nd;
        }
        return (int) ans;
    }
}
