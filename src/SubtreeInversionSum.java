import java.util.ArrayList;
import java.util.List;

public class SubtreeInversionSum {
    private int n, K;
    private int[] nums;
    private List<Integer>[] adj;
    private long[][] f, g;

    public long subtreeInversionSum(int[][] edges, int[] nums, int K) {
        this.n    = edges.length + 1;
        this.K    = K;
        this.nums = nums;

        // build adjacency list
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        // dp arrays
        f = new long[n][K + 1];
        g = new long[n][K + 1];

        dfs(0, -1);
        return f[0][0];
    }

    private void dfs(int u, int parent) {
        long[] tf = new long[K + 1];
        long[] tg = new long[K + 1];

        for (int v : adj[u]) {
            if (v == parent) continue;
            dfs(v, u);
            for (int d = 0; d <= K; d++) {
                tf[d] += f[v][d];
                tg[d] += g[v][d];
            }
        }

        // not inverting u: consume one distance unit
        for (int d = 1; d <= K; d++) {
            f[u][d] = nums[u] + tf[d - 1];
            g[u][d] = nums[u] + tg[d - 1];
        }

        // inverting u: use up one inversion here, K-1 for children
        f[u][0] = -nums[u] - tg[K - 1];
        g[u][0] = -nums[u] - tf[K - 1];

        // suffix max/min to make “exactly d” → “up to d”
        for (int d = K - 1; d >= 0; d--) {
            f[u][d] = Math.max(f[u][d], f[u][d + 1]);
            g[u][d] = Math.min(g[u][d], g[u][d + 1]);
        }
    }
}
