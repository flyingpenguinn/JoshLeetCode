import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkRecoveryPathways {
    private long Max = (long) (2e15);

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        int l = 0;
        int u = (int) 1e9;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            Map<Integer, Map<Integer, Long>> g = buildgraph(edges, online, mid);
            long cdist = getdist(n, g);
            if (cdist <= k) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private Map<Integer, Map<Integer, Long>> buildgraph(int[][] edges, boolean[] online, int t) {
        Map<Integer, Map<Integer, Long>> g = new HashMap<>();
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            if (!online[v1] || !online[v2]) {
                continue;
            }
            long w = e[2];
            if (w < t) {
                continue;
            }
            if (g.containsKey(v1) && g.get(v1).containsKey(v2)) {
                long cur = g.get(v1).get(v2);
                long val = Math.min(cur, w);
                g.get(v1).put(v2, val);
            } else {
                g.computeIfAbsent(v1, p -> new HashMap<>()).put(v2, w);
            }
        }
        return g;
    }

    private long getdist(int n, Map<Integer, Map<Integer, Long>> g) {
        long[] dist = new long[n];
        Arrays.fill(dist, Max);
        dist[0] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[1], y[1]));
        pq.offer(new long[]{0, 0});
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            int index = (int) top[0];
            long cd = top[1];
            if (index == n - 1) {
                return cd;
            }
            for (int ne : g.getOrDefault(index, new HashMap<>()).keySet()) {
                long cost = g.get(index).get(ne);
                long nd = cd + cost;
                if (dist[ne] > nd) {
                    dist[ne] = nd;
                    pq.offer(new long[]{ne, nd});
                }
            }
        }
        return Max;
    }
}
