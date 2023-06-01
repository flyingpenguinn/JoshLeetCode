import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class FindShortestPathWithKHops {
    // typical shortest path with states...
    private int MAX = (int) 1e9;

    public int shortestPathWithHops(int n, int[][] edges, int s, int d, int k) {
        List<int[]>[] g = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int w = e[2];
            g[v1].add(new int[]{v2, w});
            g[v2].add(new int[]{v1, w});
        }
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dist[i], MAX);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        pq.offer(new int[]{s, k, 0});
        for (int i = 0; i <= k; ++i) {
            dist[s][i] = 0;
        }
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[0];
            int remk = top[1];
            int cd = top[2];
            if (i == d) {
                return cd;
            }

            for (int[] nep : g[i]) {
                int ne = nep[0];
                int w = nep[1];
                int nd = cd + w;
                if (dist[ne][remk] > nd) {
                    pq.offer(new int[]{ne, remk, nd});
                    dist[ne][remk] = nd;
                }
                nd = cd;
                if (remk > 0 && dist[ne][remk - 1] > nd) {
                    pq.offer(new int[]{ne, remk - 1, nd});
                    dist[ne][remk - 1] = nd;
                }
            }
        }
        return -1;
    }
}
