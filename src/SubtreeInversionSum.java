import java.util.ArrayList;
import java.util.List;

public class SubtreeInversionSum {
    private int n, K;
    private int[] nums;
    private List<Integer>[] adj;
    private long[][][] dp;
    private static final long UNVISITED = Long.MIN_VALUE;

    public long subtreeInversionSum(int[][] edges, int[] nums, int k) {
        this.n    = edges.length + 1;
        this.K    = k;
        this.nums = nums;

        // build adjacency
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        // dp[u][dist][parity], init to UNVISITED
        dp = new long[n][K + 1][2];
        for (int u = 0; u < n; u++) {
            for (int d = 0; d <= K; d++) {
                dp[u][d][0] = dp[u][d][1] = UNVISITED;
            }
        }

        // kick off from root=0, dist=K (so root may invert), parity=0
        return dfs(0, -1, K, 0);
    }

    private long dfs(int u, int parent, int dist, int parity) {
        // if already computed, return cached
        if (dp[u][dist][parity] != UNVISITED) {
            return dp[u][dist][parity];
        }

        // OPTION A: do NOT invert here
        long base = parity == 0 ? nums[u] : -nums[u];
        long best = base;
        for (int v : adj[u]) {
            if (v == parent) continue;
            int nextDist = Math.min(dist + 1, K);
            best += dfs(v, u, nextDist, parity);
        }

        // OPTION B: invert here, only if dist >= K
        if (dist >= K) {
            long flipped = parity == 0 ? -nums[u] : nums[u];
            long cand    = flipped;
            for (int v : adj[u]) {
                if (v == parent) continue;
                // after flipping, children have dist=1 and toggled parity
                cand += dfs(v, u, 1, parity ^ 1);
            }
            best = Math.max(best, cand);
        }

        // cache & return
        dp[u][dist][parity] = best;
        return best;
    }
}
