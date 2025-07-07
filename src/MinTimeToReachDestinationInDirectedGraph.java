import java.util.*;

public class MinTimeToReachDestinationInDirectedGraph {
    static class Edge {
        int to;
        int start;
        int end;

        public Edge(int to, int start, int end) {
            this.to = to;
            this.start = start;
            this.end = end;
        }
    }

    private final Map<Integer, Set<Edge>> g = new HashMap<>();
    private int Max = (int) 1e9;

    public int minTime(int n, int[][] edges) {
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int start = e[2];
            int end = e[3];
            g.computeIfAbsent(u, k -> new HashSet<>()).add(new Edge(v, start, end));
        }
        // node, time
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        pq.offer(new int[]{0, 0});
        int[] dist = new int[n];
        Arrays.fill(dist, Max);
        dist[0] = 0;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int node = top[0];
            int ct = top[1];
            if (node == n - 1) {
                return ct;
            }
            for (Edge ne : g.getOrDefault(node, new HashSet<>())) {
                int edgestart = ne.start;
                int edgeend = ne.end;
                if (ct > edgeend) {
                    continue;
                }
                int nt = Math.max(ct, edgestart)+1;
                int tonode = ne.to;
                if (dist[tonode] > nt) {
                    dist[tonode] = nt;
                    pq.offer(new int[]{tonode, nt});
                }
            }
        }
        return -1;
    }
}
