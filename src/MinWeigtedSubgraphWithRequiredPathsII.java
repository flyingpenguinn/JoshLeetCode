import java.util.HashMap;
import java.util.Map;

public class MinWeigtedSubgraphWithRequiredPathsII {
    // The key node we are looking for is the lca of two of them
    private Map<Integer, Integer>[] t;
    private int[] dist;
    private int[] level;
    private int[][] lift;
    private int BITS = 18;

    private int getdist(int v1, int v2) {
        int lca = getlca(v1, v2);
        return dist[v1] + dist[v2] - 2 * dist[lca];
    }

    private int getlca(int v1, int v2) {
        if (level[v1] < level[v2]) {
            int tmp = v1;
            v1 = v2;
            v2 = tmp;
        }
        int diff = level[v1] - level[v2];
        for (int k = 0; k < BITS; k++) {
            if (((diff >> k) & 1) == 1) {
                v1 = lift[k][v1];
            }
        }
        if (v1 == v2) {
            return v1;
        }
        for (int k = BITS - 1; k >= 0; k--) {
            if (lift[k][v1] != lift[k][v2]) {
                v1 = lift[k][v1];
                v2 = lift[k][v2];
            }
        }
        return lift[0][v1];
    }

    public int[] minimumWeight(int[][] edges, int[][] qs) {
        int n = edges.length + 1;
        t = new HashMap[n];
        lift = new int[BITS][n];
        level = new int[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new HashMap();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int w = e[2];
            t[v1].put(v2, w);
            t[v2].put(v1, w);
        }
        dist = new int[n];
        dfs(0, -1, 0, 0);
        for (int k = 1; k < BITS; k++) {
            for (int i = 0; i < n; i++) {
                lift[k][i] = lift[k - 1][lift[k - 1][i]];
            }
        }
        int qn = qs.length;
        int[] res = new int[qn];
        for (int i = 0; i < qn; ++i) {
            int v1 = qs[i][0];
            int v2 = qs[i][1];
            int v3 = qs[i][2];
            int cur1 =  calc(v1, v2, v3);
            int cur2 =  calc(v2, v3, v1);
            int cur3 =  calc(v1, v3, v2);
            int cur = Math.min(cur1, Math.min(cur2, cur3));
            res[i] = cur;
        }
        return res;
    }

    private int calc(int v1, int v2, int v3){
        int lca = getlca(v1, v2);
        int cur = getdist(v1, lca) + getdist(v2, lca) + getdist(v3, lca);
        return cur;
    }

    private void dfs(int i, int parent, int cd, int cl) {
        dist[i] = cd;
        level[i] = cl;
        for (int ne : t[i].keySet()) {
            int w = t[i].get(ne);
            if (parent == ne) {
                continue;
            }
            lift[0][ne] = i;
            dfs(ne, i, cd + w, cl + 1);

        }
    }
}
