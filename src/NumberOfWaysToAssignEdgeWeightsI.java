import java.util.ArrayList;
import java.util.List;

public class NumberOfWaysToAssignEdgeWeightsI {
    private List<Integer>[] tree;
    private int[] depth;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        tree = new ArrayList[n + 1];
        depth = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            tree[i] = new ArrayList();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            tree[v1].add(v2);
            tree[v2].add(v1);
        }
        dfs(1, -1, 1);
        int maxdepth = -1;
        for (int i = 1; i <= n; ++i) {
            if (depth[i] > maxdepth) {
                maxdepth = depth[i];
            }
        }
        long single = count(maxdepth - 1);
        return (int) single;
    }

    private long Mod = (long) (1e9 + 7);

    private void dfs(int i, int p, int dep) {
        depth[i] = dep;
        for (int ne : tree[i]) {
            if (ne == p) {
                continue;
            }
            dfs(ne, i, dep + 1);
        }
    }

    private long count(long edges) {
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
