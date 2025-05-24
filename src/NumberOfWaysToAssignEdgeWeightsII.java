import java.util.ArrayList;
import java.util.List;

public class NumberOfWaysToAssignEdgeWeightsII {
    private List<Integer>[] tree;
    private int[] dist;
    private int[][] up;
    private int BITS = 18;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        tree = new ArrayList[n + 1];
        up = new int[BITS][n + 1];
        dist = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            tree[i] = new ArrayList();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            tree[v1].add(v2);
            tree[v2].add(v1);
        }
        dfs(1, -1, 0);
        for (int i = 1; i <= n; ++i) {
            for (int k = 1; k < BITS; ++k) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }
        int qn = queries.length;
        int[] res = new int[qn];
        int ri = 0;
        for (int[] q : queries) {
            int v1 = q[0];
            int v2 = q[1];
            int dist = getdist(v1, v2);
            res[ri++] = (int) count(dist);
        }
        return res;
    }

    private int getdist(int v1, int v2) {
        int lca = getlca(v1, v2);
        int res = dist[v1] + dist[v2] - 2 * dist[lca];
        return res;
    }

    private int getlca(int v1, int v2) {
        int d1 = dist[v1];
        int d2 = dist[v2];
        if (d1 < d2) {
            return getlca(v2, v1);
        }
        // d1>d2
        int diff = d1 - d2;
        for (int k = 0; k < BITS; ++k) {
            if (((diff >> k) & 1) == 1) {
                v1 = up[k][v1];
            }
        }
        if (v1 == v2) {
            return v1;
        }
        for (int k = BITS - 1; k >= 0; --k) {
            if (up[k][v1] != up[k][v2]) {
                v1 = up[k][v1];
                v2 = up[k][v2];
            }
        }
        return up[0][v1];
    }


    private long Mod = (long) (1e9 + 7);

    private void dfs(int i, int p, int dep) {
        dist[i] = dep;
        for (int ne : tree[i]) {
            if (ne == p) {
                continue;
            }
            up[0][ne] = i;
            dfs(ne, i, dep + 1);
        }
    }

    private long count(long edges) {
        if (edges == 0) {
            return 0;
        }
        return pow2(edges - 1);
    }

    private long pow2(long n) {
        if (n == 0) {
            return 1;
        }
        long half = pow2(n / 2);
        long halfpow = half * half;
        halfpow %= Mod;
        if (n % 2 == 1) {
            halfpow *= 2;
            halfpow %= Mod;
        }
        return halfpow;
    }
}
