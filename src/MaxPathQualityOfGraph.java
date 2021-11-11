import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxPathQualityOfGraph {
    private int res = 0;
    private Map<Integer,Integer>[] g;
    private int[] values;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        g = new HashMap[n];
        this.values = values;
        for (int i = 0; i < n; ++i) {
            g[i] = new HashMap<>();
        }
        for (int[] e : edges) {
            g[e[0]].put(e[1], e[2]);
            g[e[1]].put(e[0], e[2]);
        }
        int[] v = new int[n];
        v[0] = 1;
        dfs(0, 0, maxTime, v, values[0]);
        return res;
    }

    private void dfs(int i, int time, int maxTime, int[] v, int q) {
        if (i == 0) {
            res = Math.max(res, q);
        }
        for (int ne : g[i].keySet()) {
            int delta = g[i].get(ne);
            if (time + delta > maxTime) {
                continue;
            }
            int nq = q;
            if (v[ne]==0) {
                nq += values[ne];
            }
            ++v[ne];
            dfs(ne, time + delta, maxTime, v, nq);
            --v[ne];
        }
    }
}
