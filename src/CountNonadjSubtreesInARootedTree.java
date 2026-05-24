import java.util.ArrayList;
import java.util.List;

public class CountNonadjSubtreesInARootedTree {
    // subtree convolution template
    static final long MOD = 1_000_000_007L;

    public int countValidSubsets(int[] parent, int[] a, int k) {
        int n = parent.length;

        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            g[parent[i]].add(i);
        }

        long[][] dp = dfs(0, g, a, k);

        long ans = (dp[0][0] + dp[1][0] - 1 + MOD) % MOD;
        return (int) ans;
    }

    // res[0][r]: u not selected, subtree sum mod k == r
    // res[1][r]: u selected, subtree sum mod k == r
    private long[][] dfs(int u, List<Integer>[] g, int[] a, int k) {
        long[][] cur = new long[2][k];

        cur[0][0] = 1;
        cur[1][a[u] % k] = 1;

        for (int v : g[u]) {
            long[][] child = dfs(v, g, a, k);

            long[] childAny = new long[k];
            for (int r = 0; r < k; r++) {
                childAny[r] = (child[0][r] + child[1][r]) % MOD;
            }

            long[][] next = new long[2][k];

            // u not selected: child can be selected or not selected
            merge(next[0], cur[0], childAny, k);

            // u selected: child cannot be selected
            merge(next[1], cur[1], child[0], k);

            cur = next;
        }

        return cur;
    }

    private void merge(long[] target, long[] x, long[] y, int k) {
        for (int i = 0; i < k; i++) {
            if (x[i] == 0) continue;

            for (int j = 0; j < k; j++) {
                if (y[j] == 0) continue;

                int nr = (i + j) % k;
                target[nr] = (target[nr] + x[i] * y[j]) % MOD;
            }
        }
    }
}
