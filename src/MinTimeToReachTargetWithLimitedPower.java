import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinTimeToReachTargetWithLimitedPower {
    private Map<Long, Long>[] g;
    private long Max = (long) 1e18;

    public long[] minTimeMaxPower(int n, int[][] edges, int power, int[] cost, int source, int target) {
        g = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new HashMap<>();
        }
        for (int[] e : edges) {
            long v1 = e[0];
            long v2 = e[1];
            long time = e[2];
            if (g[(int) v1].containsKey(v2)) {
                long oldtime = g[(int) v1].get(v2);
                long ntime = Math.min(oldtime, time);
                g[(int) v1].put(v2, ntime);
            } else {
                g[(int) v1].put(v2, time);
            }

        }
        long[][] dist = new long[n][power + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dist[i], Max);
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[2], y[2]));
        pq.offer(new long[]{source, power, 0});
        dist[source][power] = 0;
        long resdist = Max;
        long respower = -Max;
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long cnode = top[0];
            long cpower = top[1];
            long cdist = top[2];
            if (cnode == target) {
                if (resdist >= Max) {
                    resdist = cdist;
                    respower = cpower;
                } else if (cdist == resdist) {
                    respower = Math.max(respower, cpower);
                } else {
                    break;
                }
            }
            long npower = cpower - cost[(int) cnode];
            if (npower < 0) {
                continue;
            }
            for (long ne : g[(int) cnode].keySet()) {
                long ctime = g[(int) cnode].get(ne);
                long ndist = cdist + ctime;
                if (dist[(int) ne][(int) npower] > ndist) {
                    dist[(int) ne][(int) npower] = ndist;
                    pq.offer(new long[]{ne, npower, ndist});
                }
            }
        }
        if (resdist >= Max) {
            return new long[]{-1, -1};
        } else {
            return new long[]{resdist, respower};
        }
    }
}
