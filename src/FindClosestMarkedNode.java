import java.util.*;

public class FindClosestMarkedNode {
    private int[][] g;
    private int MAX = (int) (1e9);

    public int minimumDistance(int n, List<List<Integer>> edges, int s, int[] marked) {
        g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], MAX);
        }
        for (List<Integer> e : edges) {
            int v1 = e.get(0);
            int v2 = e.get(1);
            int w = e.get(2);
            g[v1][v2] = Math.min(g[v1][v2], w);
        }
        Set<Integer> set = new HashSet<>();
        for (int m : marked) {
            set.add(m);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        pq.offer(new int[]{s, 0});
        int res = MAX;
        int[] dist = new int[n];
        Arrays.fill(dist, MAX);
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int v = top[0];
            int cd = top[1];
            if (set.contains(v)) {
                res = Math.min(res, cd);
            }
            for (int i = 0; i < n; ++i) {
                int nd = cd + g[v][i];
                if (dist[i] > nd) {
                    dist[i] = nd;
                    pq.offer(new int[]{i, nd});
                }
            }
        }
        return res >= MAX ? -1 : res;
    }
}
