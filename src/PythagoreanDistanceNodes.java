import java.util.*;

public class PythagoreanDistanceNodes {
    private List<Integer>[] t;
    private long[] dx;
    private long[] dy;
    private long[] dz;
    private int Max = (int) 1e9;

    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        t = new ArrayList[n];
        dx = new long[n];
        dy = new long[n];
        dz = new long[n];
        Arrays.fill(dx, Max);
        Arrays.fill(dy, Max);
        Arrays.fill(dz, Max);
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            t[v1].add(v2);
            t[v2].add(v1);
        }
        calc(x, dx);
        calc(y, dy);
        calc(z, dz);
        int res = 0;
        for (int i = 0; i < n; ++i) {
            long v1 = dx[i];
            long v2 = dy[i];
            long v3 = dz[i];
            long[] cur = new long[]{v1, v2, v3};
            Arrays.sort(cur);
            if (cur[0] * cur[0] + cur[1] * cur[1] == cur[2] * cur[2]) {
                ++res;
            }

        }
        return res;
    }

    private void calc(int st, long[] dist) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(st);
        dist[st] = 0;
        while (!q.isEmpty()) {
            int top = q.poll();
            for (int ne : t[top]) {
                if (dist[ne] > dist[top] + 1) {
                    dist[ne] = dist[top] + 1;
                    q.offer(ne);
                }
            }
        }
    }
}
