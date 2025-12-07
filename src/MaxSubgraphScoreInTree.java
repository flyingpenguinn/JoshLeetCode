import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxSubgraphScoreInTree {
    private Map<Integer, Set<Integer>> g = new HashMap<>();
    private int[] down;
    private int[] val;
    private int[] res2;

    public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
        down = new int[n];
        val = new int[n];
        for (int i = 0; i < n; ++i) {
            if (good[i] == 1) {
                val[i] = 1;
            } else {
                val[i] = -1;
            }
        }

        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            g.computeIfAbsent(v1, k -> new HashSet<>()).add(v2);
            g.computeIfAbsent(v2, k -> new HashSet<>()).add(v1);
        }
        dfs1(0, -1);
        res2 = new int[n];
        dfs2(0, -1, 0);
        return res2;
    }

    private int dfs1(int i, int p) {
        int res = val[i];
        for (int ne : g.getOrDefault(i, new HashSet<>())) {
            if (ne == p) {
                continue;
            }
            int later = dfs1(ne, i);
            if (later >= 0) {
                res += later;
            }
        }
        down[i] = res;
        return res;
    }

    private void dfs2(int i, int p, int upv) {
        final int realup = Math.max(upv, 0);
        int cur = down[i] + realup;
        res2[i] = cur;
        for (int ne : g.getOrDefault(i, new HashSet<>())) {
            if (ne == p) {
                continue;
            }

            int withoutne = down[i];
            if (down[ne] > 0) {
                withoutne -= down[ne];
            }
            int nupv = realup + withoutne;
            dfs2(ne, i, nupv);
        }
    }
}
