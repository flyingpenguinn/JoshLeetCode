import java.util.*;

public class MinCostRepairEdgesTraverse {
    public int minCost(int n, int[][] edges, int k) {
        long l = 0;
        long u = (long) 1e9;
        long maxres = (long) 1e9 + 1;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            if (mindist(n, edges, mid) <= k) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l >= maxres ? -1 : (int) l;
    }

    private long Max = (long) 1e18;

    private long mindist(int n, int[][] es, long t) {
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : es) {
            int v1 = e[0];
            int v2 = e[1];
            int w = e[2];
            if (w <= t) {
                g[v1].add(v2);
                g[v2].add(v1);
            }
        }
        //   System.out.println("t="+t+" g="+g);
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        long[] dist = new long[n];

        Arrays.fill(dist, Max);
        dist[0] = 0;
        while (!q.isEmpty()) {
            int top = q.poll();
            long cd = dist[top];
            //  System.out.println(top+" cd="+cd);
            if (top == n - 1) {
                break;
            }
            for (int ne : g[top]) {
                if (dist[ne] <= cd + 1) {
                    continue;
                }
                dist[ne] = cd + 1;
                q.offer(ne);
            }
        }
        return dist[n - 1];
    }
}
