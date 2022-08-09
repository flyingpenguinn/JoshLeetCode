import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class FindClosestNodeToGivenTwoNodes {
    private int Max = (int) 1e9;

    public int closestMeetingNode(int[] es, int n1, int n2) {
        int n = es.length;
        int[] dist1 = find(es, n1);
        int[] dist2 = find(es, n2);
        int res = Max;
        int resi = -1;
        for (int i = 0; i < n; ++i) {
            int d1 = dist1[i];
            int d2 = dist2[i];
            int cur = Math.max(d1, d2);
            if (cur < res) {
                res = cur;
                resi = i;
            }
        }
        return res >= Max ? -1 : resi;
    }

    private int[] find(int[] es, int s) {
        int n = es.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Max);
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{s, 0});
        dist[s] = 0;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int ind = top[1];
            int i = top[0];
            int ne = es[i];
            if (ne == -1) {
                continue;
            }
            if (dist[ne] == Max) {
                dist[ne] = dist[i] + 1;
                q.offer(new int[]{ne, dist[ne]});
            }
        }
        return dist;
    }
}
