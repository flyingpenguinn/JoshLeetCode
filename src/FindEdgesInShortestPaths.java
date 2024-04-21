import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FindEdgesInShortestPaths {
    // for a path to be working, 0->u->v->n-1 must be = that shortest path 0->n-1
    private Map<Long, Map<Long, Long>> g = new HashMap<>();
    private long Max = (long) 1e15;
    private long mind0n = Max;

    public boolean[] findAnswer(int n, int[][] edges) {
        for (int[] e : edges) {
            long v1 = e[0];
            long v2 = e[1];
            long w = e[2];
            g.computeIfAbsent(v1, p -> new HashMap<>()).put(v2, w);
            g.computeIfAbsent(v2, p -> new HashMap<>()).put(v1, w);
        }
        long[] dist0 = new long[n];
        long[] distn1 = new long[n];
        rund(0, dist0);
        rund(n - 1, distn1);
        int en = edges.length;
        boolean[] res = new boolean[en];
        for (int i = 0; i < en; ++i) {
            int[] e = edges[i];
            int v1 = e[0];
            int v2 = e[1];
            long w = e[2];
            long way1 = dist0[v1] + distn1[v2] + w;
            long way2 = distn1[v1] + dist0[v2] + w;
            long mincur = Math.min(way1, way2);
            if (mincur == mind0n) {
                res[i] = true;
            } else {
                res[i] = false;
            }
        }
        return res;
    }

    private void rund(long start, long[] dist) {
        int n = dist.length;
        Arrays.fill(dist, Max);
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[1], y[1]));
        boolean[] done = new boolean[n];
        dist[(int) start] = 0;
        pq.offer(new long[]{start, 0});
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long cur = top[0];
            long cd = top[1];
            if (start == 0 && cur == n - 1) {
                mind0n = Math.min(mind0n, cd);
            }
            if (done[(int) cur]) {
                continue;
            }
            done[(int) cur] = true;
            Map<Long, Long> cm = g.getOrDefault(cur, new HashMap<>());
            for (long ne : cm.keySet()) {
                long delta = cm.get(ne);
                long nd = cd + delta;
                if (dist[(int) ne] > nd) {
                    dist[(int) ne] = nd;
                    pq.offer(new long[]{ne, nd});
                }
            }
        }
    }
}
