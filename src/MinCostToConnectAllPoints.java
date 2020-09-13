import java.util.*;

public class MinCostToConnectAllPoints {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[]{i, j, dist});
            }
        }
        int[] pa = new int[n];
        Arrays.fill(pa, -1);
        Collections.sort(edges, (x, y) -> Integer.compare(x[2], y[2]));
        int res = 0;
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int pv1 = find(v1, pa);
            int pv2 = find(v2, pa);
            if (pv1 == pv2) {
                continue;
            }
            pa[pv1] = pv2;
            res += e[2];
        }
        return res;
    }


    private int find(int i, int[] pa) {
        int father = pa[i];
        if (father == -1) {
            return i;
        } else {
            int rt = find(father, pa);
            pa[i] = rt;
            return rt;
        }
    }
}

class MinCostToConnectAllPointsPrim {
    // prim is almost identical to dijkastra. only diff is here dist means the min dist from node to ONE CERTAIN node in the selected set
    // while in dijkastra it's the dist to the source point
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        pq.offer(new int[]{0, 0});
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] done = new boolean[n];
        dist[0] = 0;
        int res = 0;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int node = top[0];
            if (done[node]) {
                continue;
            }
            done[node] = true;
            int curdist = top[1];
            res += curdist;
            for (int i = 0; i < n; i++) {
                int ndist = Math.abs(points[i][0] - points[node][0]) + Math.abs(points[i][1] - points[node][1]);
                if (!done[i] && ndist < dist[i]) {
                    dist[i] = ndist;
                    pq.offer(new int[]{i, ndist});
                }
            }
        }
        return res;
    }
}
