import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MinCostToBuyApplesII {
    private List<int[]>[] g;

    public int[] minCost(int n, int[] prices, int[][] roads) {
        g = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] r : roads) {
            int v1 = r[0];
            int v2 = r[1];
            int cost = r[2];
            int tax = r[3];
            g[v1].add(new int[]{v2, cost, tax});
            g[v2].add(new int[]{v1, cost, tax});
        }
        long[] dist1 = new long[n];
        long[] dist2 = new long[n];
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            shortest(i, dist1, 1);
            shortest(i, dist2, 2);
            long best = prices[i];
            for (int j = 0; j < n; ++j) {
                long cur = prices[j] + dist1[j] + dist2[j];
                best = Math.min(best, cur);

            }
            res[i] = (int) best;
        }
        return res;
    }

    private long Max = (long) 1e18;

    private void shortest(int i, long[] dist, int type) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[1], y[1]));
        Arrays.fill(dist, Max);
        dist[i] = 0;
        pq.offer(new long[]{i, 0});
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            int cur = (int) top[0];
            long cd = top[1];
            if (cd > dist[cur]) {
                continue;
            }
            for (int[] nes : g[cur]) {
                int ne = nes[0];
                long ncost = nes[1];
                long ntax = nes[2];
                long nd = 0;
                if (type == 1) {
                    nd = cd + ncost * ntax;
                } else {
                    nd = cd + ncost;
                }
                if (dist[ne] > nd) {
                    dist[ne] = nd;
                    pq.offer(new long[]{ne, nd});
                }
            }
        }
    }
}
