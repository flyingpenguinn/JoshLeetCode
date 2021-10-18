import java.util.*;

public class TheTimeWhenNetworkBecomesIdle {
    public int networkBecomesIdle(int[][] es, int[] p) {
        int n = p.length;
        List<Integer>[] g = new ArrayList[n];
        for(int i=0; i<n; ++i){
            g[i] = new ArrayList<>();
        }
        for (int[] e : es) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        int[] dist = new int[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.offerLast(0);
        Set<Integer> seen = new HashSet<>();
        dist[0] = 0;
        seen.add(0);
        while (!q.isEmpty()) {
            int top = q.pollFirst();
            for (int ne : g[top]) {
                if (!seen.contains(ne)) {
                    seen.add(ne);
                    dist[ne] = dist[top] + 1;
                    q.offerLast(ne);
                }
            }
        }
        int res = 0;
        for (int i = 1; i < n; ++i) {
            int time = 2 * dist[i];
            // -1: the reply can arrive at the same time as we are about to send
            int lastSent = ((time - 1) / p[i]) * p[i];
            int cur = lastSent + time + 1; // +1: the next second
            res = Math.max(res, cur);
        }
        return res;
    }
}
