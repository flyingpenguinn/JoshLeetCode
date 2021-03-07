import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class NumberOfRetrictedPathsFromFirstToLast {
    public int countRestrictedPaths(int n, int[][] edges) {
        Map<Integer, Integer>[] g = new HashMap[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new HashMap<>();
        }
        for (int[] e : edges) {
            g[e[0]].put(e[1], e[2]);
            g[e[1]].put(e[0], e[2]);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        int[] dist = new int[n + 1];
        pq.offer(new int[]{n, 0});
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;
        boolean[] done = new boolean[n + 1];
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[0];
            int cd = top[1];
            if (done[i]) {
                continue;
            }
            done[i] = true;
            for (int j : g[i].keySet()) {
                int w = g[i].get(j);
                if (dist[j] > cd + w) {
                    dist[j] = cd + w;
                    pq.offer(new int[]{j, dist[j]});
                }
            }
        }
        dp = new Long[n + 1];
        long rt = dfs(n, g, dist);
        return (int) rt;

    }

    private int mod = 1000000007;
    private Long[] dp;

    private long dfs(int i, Map<Integer, Integer>[] g, int[] dist) {
        if (i == 1) {
            return 1;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        long res = 0;
        for (int j : g[i].keySet()) {
            if (dist[j] > dist[i]) {
                res += dfs(j, g, dist);
                res %= mod;
            }
        }
        dp[i] = res;
        return res;
    }
}
