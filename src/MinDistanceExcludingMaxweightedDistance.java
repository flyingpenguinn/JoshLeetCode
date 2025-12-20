import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinDistanceExcludingMaxweightedDistance {
    // equivalent to excluding one edge at most
    private Map<Integer, Map<Integer, Long>> g = new HashMap<>();
    private long Max = (long) 1e18;

    class Item {
        int index;
        long dist;
        int skipped;

        public Item(int index, long dist, int skipped) {
            this.index = index;
            this.dist = dist;
            this.skipped = skipped;
        }
    }

    public long minCostExcludingMax(int n, int[][] edges) {
        int en = edges.length;
        // System.out.println(n+" "+en);
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            long w = e[2];
            g.computeIfAbsent(v1, p -> new HashMap<>()).put(v2, w);
            g.computeIfAbsent(v2, p -> new HashMap<>()).put(v1, w);
        }
        long[][] dist = new long[n][2];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dist[i], Max);
        }
        boolean[][] done = new boolean[n][2];
        PriorityQueue<Item> pq = new PriorityQueue<>((x, y) -> {
            if (x.dist != y.dist) {
                return Long.compare(x.dist, y.dist);
            } else {
                return Integer.compare(x.skipped, y.skipped);
            }
        });
        // node, dist, skipped.
        pq.offer(new Item(0, 0L, 0));
        dist[0][0] = 0;
        dist[0][1] = 0;
        while (!pq.isEmpty()) {
            Item top = pq.poll();
            int index = top.index;
            long cdist = top.dist;
            int skipped = top.skipped;
            if (index == n - 1) {
                return cdist;
            }
            if (done[index][skipped]) {
                continue;
            }
            done[index][skipped] = true;
            for (int ne : g.getOrDefault(index, new HashMap<>()).keySet()) {
                long w = g.get(index).get(ne);
                long ndist = cdist + w;
                if (dist[ne][skipped] > ndist) {
                    dist[ne][skipped] = ndist;
                    pq.offer(new Item(ne, ndist, skipped));
                }
                if (skipped == 0 && dist[ne][1] > cdist) {
                    dist[ne][1] = cdist;
                    pq.offer(new Item(ne, cdist, 1));
                }
            }
        }
        return -1;
    }
}
