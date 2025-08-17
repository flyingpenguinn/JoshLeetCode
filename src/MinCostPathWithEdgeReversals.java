import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinCostPathWithEdgeReversals {
    private Map<Integer, Integer>[] g;

    public int minCost(int n, int[][] edges) {
        g = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new HashMap<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int w = e[2];
            int cw = Integer.MAX_VALUE;
            if (g[v1].containsKey(v2)) {
                cw = g[v1].get(v2);
            }
            g[v1].put(v2, Math.min(cw, w));
            if (g[v2].containsKey(v1)) {
                cw = g[v2].get(v1);
            }
            g[v2].put(v1, Math.min(cw, 2 * w));
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        pq.offer(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int node = top[0];
            int cd = top[1];
            if (node == n - 1) {
                return cd;
            }
            for (int ne : g[node].keySet()) {
                int cw = g[node].get(ne);
                int nd = cd + cw;
                if (dist[ne] > nd) {
                    dist[ne] = nd;
                    pq.offer(new int[]{ne, nd});
                }
            }
        }
        return -1;
    }
}
