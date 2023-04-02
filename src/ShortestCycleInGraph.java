import java.util.*;

public class ShortestCycleInGraph {
    // just like dijkastra, but check ne and cur are not in parent relationship
    private List<Integer>[] g;
    private int MAX = (int) 1e9;

    public int findShortestCycle(int n, int[][] edges) {
        g = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            g[v1].add(v2);
            g[v2].add(v1);
        }
        int res = MAX;
        for (int i = 0; i < n; ++i) {
            Deque<Integer> q = new ArrayDeque<>();
            int[] dist = new int[n];
            int[] parent = new int[n];
            Arrays.fill(dist, MAX);
            Arrays.fill(parent, -1);
            q.offer(i);
            dist[i] = 0;
            while (!q.isEmpty()) {
                int cur = q.poll();
                int cd = dist[cur];
                int nd = cd + 1;
                for (int ne : g[cur]) {
                    if (dist[ne] > nd) {
                        dist[ne] = nd;
                        parent[ne] = cur;
                        q.offer(ne);
                    } else if (parent[ne] != cur && parent[cur] != ne) {
                        res = Math.min(res, dist[cur] + dist[ne] + 1);
                    }
                }
            }
        }
        return res >= MAX ? -1 : res;
    }
}
