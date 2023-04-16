import java.util.Arrays;
import java.util.PriorityQueue;

public class DesignGraphWithShortestPathCalculator {
    class Graph {
        private int[][] g;
        private int n;
        private int MAX = (int) 1e9;

        public Graph(int n, int[][] edges) {
            this.n = n;
            g = new int[n][n];
            for (int i = 0; i < n; ++i) {
                Arrays.fill(g[i], MAX);
            }
            for (int[] e : edges) {
                addEdge(e);
            }
        }

        public void addEdge(int[] e) {
            int v1 = e[0];
            int v2 = e[1];
            int cost = e[2];
            g[v1][v2] = cost;
        }

        public int shortestPath(int node1, int node2) {
            int[] dist = new int[n];
            Arrays.fill(dist, MAX);
            dist[node1] = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
            pq.offer(new int[]{node1, 0});
            boolean[] done = new boolean[n];
            while (!pq.isEmpty()) {
                int[] top = pq.poll();
                int i = top[0];
                int cd = top[1];
                if (i == node2) {
                    return cd;
                }
                if (done[i]) {
                    continue;
                }
                done[i] = true;
                for (int j = 0; j < n; ++j) {
                    if (g[i][j] == MAX) {
                        continue;
                    }
                    int nd = cd + g[i][j];
                    if (nd < dist[j]) {
                        dist[j] = nd;
                        pq.offer(new int[]{j, nd});
                    }
                }
            }
            return -1;
        }
    }
}
