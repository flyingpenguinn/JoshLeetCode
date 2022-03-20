import base.ArrayUtils;

import java.util.*;

public class MinWeightedSubgraphWithRequiredPaths {
    /*

   o Dijkstra 3 times.

First time: store the shortest distance from node a to all other nodes in array da.

Second time: store the shortest distance from node b to all other nodes in array db.

Third time: store the shortest distance from node dest to all other nodes via Reversed Graph in array dd.

The answer is the minimum da[i] + db[i] + dd[i] (0 <= i < N).
     */

    private int n;
    private long Max = (long) 1e18;

    private void update(Map<Integer, Map<Integer, Integer>> g, int i, int j, int w) {
        Map<Integer, Integer> item = g.getOrDefault(i, new HashMap<>());
        if (item.containsKey(j)) {
            item.put(j, Math.min(item.get(j), w));
        } else {
            item.put(j, w);
        }
        g.put(i, item);
    }

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        this.n = n;
        Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> rg = new HashMap<>();
        for (int[] e : edges) {
            int start = e[0];
            int end = e[1];
            int w = e[2];
            update(g, start, end, w);
            update(rg, end, start, w);
        }
        long[] d1 = shortest(src1, g);
        long[] d2 = shortest(src2, g);
        long[] d3 = shortest(dest, rg);
        long res = Max;
        for (int i = 0; i < n; ++i) {
            if (d1[i] < Max && d2[i] < Max && d3[i] < Max) {
                long cur = d1[i] + d2[i] + d3[i];
                res = Math.min(res, cur);
            }
        }
        return res == Max ? -1 : res;
    }

    private long[] shortest(int src, Map<Integer, Map<Integer, Integer>> g) {
        long[] dist = new long[n];
        boolean[] done = new boolean[n];
        Arrays.fill(dist, Max);
        dist[src] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[1], y[1]));
        pq.offer(new long[]{src, 0});
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            int i = (int) top[0];
            if (done[i]) {
                continue;
            }
            done[i] = true;
            long cd = top[1];
            for (int ne : g.getOrDefault(i, new HashMap<>()).keySet()) {
                long ndist = cd + g.get(i).get(ne) ;
                if (ndist < dist[ne]) {
                    dist[ne] = ndist;
                    pq.offer(new long[]{ne, ndist});
                }
            }
        }
        return dist;
    }


    public static void main(String[] args) {
        System.out.println(new MinWeightedSubgraphWithRequiredPaths().minimumWeight(5, ArrayUtils.read("[[4,2,20],[4,3,46],[0,1,15],[0,1,43],[0,1,32],[3,1,13]]"), 0, 4, 1));
    }
}
