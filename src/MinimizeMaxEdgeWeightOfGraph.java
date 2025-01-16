import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimizeMaxEdgeWeightOfGraph {
    // threshold is actually useless... outgoing edge is at most 1 anyway in a dfs tree from 0
    public int minMaxWeight(int n, int[][] edges, int threshold) {
        int maxedge = 0;
        for (int[] e : edges) {
            int w = e[2];
            maxedge = Math.max(maxedge, w);
        }
        int l = 0;
        int u = maxedge;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (good(n, edges, threshold, mid)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l > maxedge ? -1 : l;
    }

    private boolean good(int n, int[][] edges, int threshold, int mid) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        int[] outd = new int[n];
        for (int[] e : edges) {
            int se = e[0];
            int ee = e[1];
            int w = e[2];
            if (w > mid) {
                continue;
            }
            g.computeIfAbsent(ee, p -> new ArrayList<>()).add(se);
        }
        int[] st = new int[n];
        dfs(g, 0, st, outd);
        for (int i = 0; i < n; ++i) {
            if (st[i] == 0) {
                return false;
            }
            if (outd[i] > threshold) {
                return false;
            }
        }
        return true;
    }

    private void dfs(Map<Integer, List<Integer>> g, int i, int[] st, int[] outd) {
        st[i] = 1;
        for (int ne : g.getOrDefault(i, new ArrayList<>())) {
            if (st[ne] == 1) {
                continue;
            }
            if (st[ne] == 2) {
                continue;
            }
            ++outd[ne];
            dfs(g, ne, st, outd);
        }
        st[i] = 2;
    }
}
