import java.util.HashMap;
import java.util.Map;

public class FindWeighteMedianNodeInTree {
    // lca!
    private Map<Integer, Long>[] t;
    private int[][] lift;
    private long[][] distlift;
    private int[] level;
    private long[] dist;
    private final int BITS = 18;

    private int getlca(int v1, int v2) {
        if (level[v1] < level[v2]) {
            int tmp = v1;
            v1 = v2;
            v2 = tmp;
        }
        int diff = level[v1] - level[v2];
        for (int k = 0; k < BITS; ++k) {
            if (((diff >> k) & 1) == 1) {
                v1 = lift[k][v1];
            }
        }
        if (v1 == v2) {
            return v1;
        }
        for (int k = BITS - 1; k >= 0; --k) {
            if (lift[k][v1] != lift[k][v2]) {
                v1 = lift[k][v1];
                v2 = lift[k][v2];
            }
        }
        return lift[0][v1];
    }


    private void dfs(int i, int parent, int cl, long cd) {
        level[i] = cl;
        dist[i] = cd;
        for (int ne : t[i].keySet()) {
            if (parent == ne) {
                continue;
            }
            long cw = t[i].get(ne);
            lift[0][ne] = i;
            distlift[0][ne] = cw;
            dfs(ne, i, cl + 1, cd + cw);
        }
    }

    public int[] findMedian(int n, int[][] edges, int[][] queries) {
        t = new HashMap[n];
        lift = new int[BITS][n];
        distlift = new long[BITS][n];
        level = new int[n];
        dist = new long[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new HashMap<Integer, Long>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            long w = e[2];
            t[v1].put(v2, w);
            t[v2].put(v1, w);
        }
        dfs(0, -1, 0, 0);
        for (int k = 1; k < BITS; ++k) {
            for (int i = 0; i < n; ++i) {
                final int km1 = lift[k - 1][i];
                lift[k][i] = lift[k - 1][km1];
                distlift[k][i] = distlift[k - 1][km1] + distlift[k - 1][i];
            }
        }
        int qn = queries.length;
        int[] res = new int[qn];
        for (int i = 0; i < qn; ++i) {
            int v1 = queries[i][0];
            int v2 = queries[i][1];
            int lca = getlca(v1, v2);
            long lcatov1 = dist[v1] - dist[lca];
            long lcatov2 = dist[v2] - dist[lca];
            long total = lcatov1 + lcatov2;
            long half = (long) Math.ceil(1.0 * total / 2);
            long half2 = (long) Math.floor(1.0 * total / 2);
            if (lca == v1) {
                res[i] = find(v2, half2, false);
            } else if (lca == v2) {
                res[i] = find(v1, half, true);
            } else if (lcatov1 >= half) {
                res[i] = find(v1, half, true);
            } else {
                res[i] = find(v2, half2, false);
            }
        }
        return res;
    }

    private int find(int v, long t, boolean liftatend) {
        for (int k = BITS - 1; k >= 0; --k) {
            if (distlift[k][v] <= t) {
                t -= distlift[k][v];
                v = lift[k][v];
            }
        }
        if (liftatend && t > 0) {
            return lift[0][v];
        } else {
            return v;
        }
    }
}
