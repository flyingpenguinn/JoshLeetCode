import java.util.*;

public class EarliestActivationTimes {
    // Weekly 529 Q3, redo 2026-11-24 as the event version (contest version: a max-heap of size req per node).
    // Each push is an arrival event; decide only at pop time ("有延迟的处理，一律在它从堆里被弹出的时候再操作").
    // Pops come in time order, so the req-th popped arrival of a node is its activation time.
    // req itself is the guard: it reaches 0 exactly once, so no dist check is needed; later arrivals are skipped.
    // Contest WA: counted at push time and took the minimum arrival instead of the req-th smallest.
    public long[] earliestActivationTimes(int n, int[][] edges, int[] req) {
        Map<Integer, Integer>[] g = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new HashMap<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int del = e[2];
            g[v1].put(v2, del);
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[1], y[1]));
        pq.offer(new long[]{0, 0});
        long[] dist = new long[n];
        Arrays.fill(dist, -1);
        req[0] = 1;
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            int node = (int) top[0];
            long cd = top[1];
            if (req[node] <= 0) {
                continue;
            }
            --req[node];
            if (req[node] > 0) {
                continue;
            }
            dist[node] = cd;
            for (int ne : g[node].keySet()) {
                long ccost = g[node].get(ne);
                long nd = cd + ccost;
                pq.offer(new long[]{ne, nd});
            }
        }
        return dist;
    }
}
