import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinTimeVisitDisappearingNodes {
    // TLE without done array!
    private int Max = (int) (1e9);
    private Map<Integer, Map<Integer, Integer>> g = new HashMap<>();

    private void addEdge(int v1, int v2, int vd) {
        Map<Integer, Integer> cm = g.getOrDefault(v1, new HashMap<>());
        if (cm.containsKey(v2)) {
            int cur = cm.get(v2);
            if (vd < cur) {
                cm.put(v2, vd);
            } else {
                // do nothing
            }
        } else {
            cm.put(v2, vd);
        }
        g.put(v1, cm);
    }

    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int vd = e[2];
            addEdge(v1, v2, vd);
            addEdge(v2, v1, vd);
        }
        int[] dist = new int[n];
        boolean[] done = new boolean[n];
        Arrays.fill(dist, Max);
        pq.offer(new int[]{0, 0});
        dist[0] = 0;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int v = top[0];
            int cd = top[1];
            if (done[v]) {
                continue;
            }
            done[v] = true;
            //System.out.println(v+" "+cd);
            Map<Integer, Integer> nes = g.getOrDefault(v, new HashMap<>());
            for (int ne : nes.keySet()) {
                int delta = nes.get(ne);
                int reach = cd + delta;
                if (disappear[ne] <= reach) {
                    continue;
                }
                if (dist[ne] <= reach) {
                    continue;
                }
                dist[ne] = reach;
                pq.offer(new int[]{ne, reach});
            }
        }
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            if (dist[i] >= Max) {
                res[i] = -1;
            } else {
                res[i] = dist[i];
            }
        }
        return res;
    }
}
