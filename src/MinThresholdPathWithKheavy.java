import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinThresholdPathWithKheavy {
    private Map<Integer, Integer>[] g;
    private int Max = (int) (1e9 + 5);

    public int minimumThreshold(int n, int[][] es, int s, int t, int k) {
        int l = 0;
        int u = Max;
        g = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new HashMap<>();
        }
        for (int[] e : es) {
            int v1 = e[0];
            int v2 = e[1];
            int w = e[2];

            g[v1].put(v2, Math.min(g[v1].getOrDefault(v2, Max), w));
            g[v2].put(v1, Math.min(g[v2].getOrDefault(v1, Max), w));

        }
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (good(mid, k, s, t)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (l >= Max) {
            return -1;
        }
        return l;
    }

    private boolean good(int mid, int k, int s, int t) {
        int n = g.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Max);
        dist[s] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        pq.offer(new int[]{s, 0});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int nc = top[0];
            int cd = top[1];
            if (cd > k) {
                break;
            }
            if (nc == t) {
                return true;
            }
            for (int ne : g[nc].keySet()) {
                if (dist[ne] <= cd) {
                    continue;
                }
                int nw = g[nc].get(ne);
                int nd = cd;
                if (nw > mid) {
                    nd = cd + 1;
                }
                dist[ne] = nd;
                pq.offer(new int[]{ne, nd});
            }
        }
        return false;
    }
}
