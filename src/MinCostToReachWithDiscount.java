import java.util.*;

public class MinCostToReachWithDiscount {
    // LC 2093, redo 2026-11-25 (first written 2021-12-01), one shot.
    // Dijkstra on state (node, discounts left): each edge either pays w (same count) or w / 2 (count - 1).
    // Decide at pop; the first pop of n - 1 in any state is the answer.
    private Map<Integer, Integer>[] g;

    public int minimumCost(int n, int[][] highways, int dc) {
        g = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new HashMap<>();
        }
        for (int[] h : highways) {
            int v1 = h[0];
            int v2 = h[1];
            int w = h[2];
            g[v1].put(v2, w);
            g[v2].put(v1, w);
        }
        int[][] dist = new int[n][dc + 1];
        int Max = (int) 1e9;
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dist[i], Max);
        }
        dist[0][dc] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        pq.offer(new int[]{0, dc, 0});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int node = top[0];
            int cdc = top[1];
            int cd = top[2];
            if (dist[node][cdc] != cd) {
                continue;
            }
            if (node == n - 1) {
                return cd;
            }
            for (int ne : g[node].keySet()) {
                int nw = g[node].get(ne);
                int nd = cd + nw;
                if (dist[ne][cdc] > nd) {
                    dist[ne][cdc] = nd;
                    pq.offer(new int[]{ne, cdc, nd});
                }
                if (cdc - 1 >= 0) {
                    int nd2 = cd + nw / 2;
                    int ncdc = cdc - 1;
                    if (dist[ne][ncdc] > nd2) {
                        dist[ne][ncdc] = nd2;
                        pq.offer(new int[]{ne, ncdc, nd2});
                    }
                }
            }
        }
        return -1;
    }
}
