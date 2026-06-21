import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ShortestPathWithAtmostKConsecIdenticalChars {
    private Map<Integer, Integer>[] g;

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        g = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new HashMap<>();
        }
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            int nw = w;
            if (g[u].containsKey(v)) {
                int oldw = g[u].get(v);
                nw = Math.min(oldw, w);
            }
            g[u].put(v, nw);
        }
        int Max = (int) 1e9;
        // path till i and consecutive chain len
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dist[i], Max);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        pq.offer(new int[]{0, 0, 1});
        dist[0][1] = 0;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int cnode = top[0];
            int cd = top[1];
            int consec = top[2];
            if (cnode == n - 1) {
                return cd;
            }
            if (cd > dist[cnode][consec]) {
                continue;
            }
            int clabel = labels.charAt(cnode) - 'a';
            for (int ne : g[cnode].keySet()) {
                int nw = g[cnode].get(ne);
                int nlabel = labels.charAt(ne) - 'a';
                int newconsec = (nlabel == clabel) ? consec + 1 : 1;
                if (newconsec > k) {
                    continue;
                }
                int nd = cd + nw;
                if (dist[ne][newconsec] > nd) {
                    dist[ne][newconsec] = nd;
                    pq.offer(new int[]{ne, nd, newconsec});
                }
            }
        }

        return -1;
    }
}
