import java.util.ArrayDeque;
import java.util.Arrays;

public class MaxPartitionFactor {
    // binary search + bipartite graph check!
    public int maxPartitionFactor(int[][] points) {
        int n = points.length;
        if (n == 2) return 0;
        int[][] fenoradilk = points;
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = points[i][0];
            y[i] = points[i][1];
        }
        long mx1 = Long.MIN_VALUE, mn1 = Long.MAX_VALUE, mx2 = Long.MIN_VALUE, mn2 = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long t1 = (long) x[i] + y[i];
            long t2 = (long) x[i] - y[i];
            if (t1 > mx1) mx1 = t1;
            if (t1 < mn1) mn1 = t1;
            if (t2 > mx2) mx2 = t2;
            if (t2 < mn2) mn2 = t2;
        }
        long hi = Math.max(mx1 - mn1, mx2 - mn2);
        Integer[] ord = new Integer[n];
        for (int i = 0; i < n; i++) ord[i] = i;
        Arrays.sort(ord, (a, b) -> Integer.compare(x[a], x[b]));
        int[] idx = new int[n];
        for (int i = 0; i < n; i++) idx[i] = ord[i];
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) pos[idx[i]] = i;
        long l = 0, u = hi;
        while (l < u) {
            long m = l + (u - l + 1) / 2;
            if (ok(m, x, y, idx, pos)) l = m;
            else u = m - 1;
        }
        return (int) l;
    }

    private boolean ok(long d, int[] x, int[] y, int[] idx, int[] pos) {
        int n = x.length;
        int[] c = new int[n];
        Arrays.fill(c, -1);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int s = 0; s < n; s++) {
            if (c[s] != -1) continue;
            c[s] = 0;
            q.add(s);
            while (!q.isEmpty()) {
                int v = q.pollFirst();
                int pv = pos[v];
                for (int t = pv - 1; t >= 0; t--) {
                    int j = idx[t];
                    long dx = (long) x[v] - x[j];
                    if (dx >= d) break;
                    long dy = Math.abs((long) y[v] - y[j]);
                    if (dx + dy < d) {
                        if (c[j] == -1) {
                            c[j] = c[v] ^ 1;
                            q.add(j);
                        } else if (c[j] == c[v]) return false;
                    }
                }
                for (int t = pv + 1; t < n; t++) {
                    int j = idx[t];
                    long dx = (long) x[j] - x[v];
                    if (dx >= d) break;
                    long dy = Math.abs((long) y[v] - y[j]);
                    if (dx + dy < d) {
                        if (c[j] == -1) {
                            c[j] = c[v] ^ 1;
                            q.add(j);
                        } else if (c[j] == c[v]) return false;
                    }
                }
            }
        }
        return true;
    }
}
