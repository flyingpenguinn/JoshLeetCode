import java.util.*;

public class MinCostToBuyApples {
    private Map<Long, Map<Long, Long>> g = new HashMap<>();
    private long Max = (long) 2e18;

    public long[] minCost(int n, int[][] roads, int[] appleCost, int k) {

        for (int[] r : roads) {
            long s = r[0] - 1;
            long e = r[1] - 1;
            long v = r[2];
            g.computeIfAbsent(s, p -> new HashMap<>()).put(e, v);
            g.computeIfAbsent(e, p -> new HashMap<>()).put(s, v);
        }
        // System.out.println(g);
        long[] res = new long[n];
        for (int i = 0; i < n; ++i) {
            long cres = Max;
            long[] dist = calc(i, n);
            for (int j = 0; j < n; ++j) {
                if (dist[j] >= Max) {
                    continue;
                }
                long cur = dist[j] + dist[j] * k + appleCost[j];
                // System.out.println(i+"-->"+j+" = "+cur);
                cres = Math.min(cur, cres);
            }
            res[i] = cres;
        }
        return res;
    }

    private long[] calc(long start, int n) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[1], y[1]));
        long[] dist = new long[n];
        Set<Long> done = new HashSet<>();
        Arrays.fill(dist, Max);
        dist[(int) start] = 0;
        pq.offer(new long[]{start, 0});
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long index = top[0];
            if (done.contains(index)) {
                continue;
            }
            done.add(index);
            long cd = top[1];
            Map<Long, Long> tm = g.getOrDefault(index, new HashMap<>());
            for (long ne : tm.keySet()) {
                long nd = cd + tm.get(ne);

                if (dist[(int) ne] > nd) {
                    dist[(int) ne] = nd;
                    pq.offer(new long[]{ne, nd});
                }
            }
        }
        //  System.out.println(start+" "+Arrays.toString(dist));
        return dist;
    }
}
