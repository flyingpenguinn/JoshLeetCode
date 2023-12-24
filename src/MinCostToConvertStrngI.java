import java.util.Arrays;
import java.util.PriorityQueue;

public class MinCostToConvertStrngI {
    // dijkstra
    private long[][] g = new long[26][26];
    private int MAX = (int) 1e15;

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        for (int i = 0; i < 26; ++i) {
            Arrays.fill(g[i], MAX);
        }
        for (int i = 0; i < original.length; ++i) {
            int start = original[i] - 'a';
            int end = changed[i] - 'a';
            g[start][end] = Math.min(g[start][end], cost[i]);
        }
        int n = source.length();
        long res = 0;
        Long[][] dp = new Long[26][26];
        for (int i = 0; i < n; ++i) {
            int sind = source.charAt(i) - 'a';
            int tind = target.charAt(i) - 'a';
            long cur = 0;
            if (dp[sind][tind] != null) {
                cur = dp[sind][tind];
            } else {
                cur = dist(sind, tind);
                dp[sind][tind] = cur;
            }

            if (cur >= MAX) {
                return -1;
            } else {
                res += cur;
            }
        }
        return res;
    }

    private long dist(int s, int t) {
        long[] rd = new long[26];
        Arrays.fill(rd, MAX);
        rd[s] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[1], y[1]));
        pq.offer(new long[]{s, 0});
        boolean[] done = new boolean[26];
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            int cv = (int) top[0];
            if (done[cv]) {
                continue;
            }
            done[cv] = true;
            long cd = top[1];
            if (cv == t) {
                return cd;
            }

            for (int i = 0; i < 26; ++i) {
                long nd = g[cv][i] + cd;
                if (rd[i] > nd) {
                    rd[i] = nd;
                    pq.offer(new long[]{i, nd});
                }
            }
        }
        return MAX;
    }
}
