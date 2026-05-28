import java.util.ArrayList;
import java.util.List;

public class CountNonadjSubtreesInARootedTree {
    // subtree convolution template
    /* hallmark:

    void dfs(u):
        init dp[u]
           for child v:
            dfs(v)
            merge current aggregate at u × contribution from v

    the merge is typically:

    for (int cur = 0; cur < K; cur++) {      // current aggregate at u
    for (int sub = 0; sub < K; sub++) {  // contribution from child subtree
        ndp[combine(cur, sub)] += dpU[cur] * dpChild[sub];
    }
    }
     */
    private List<Integer>[] tree;
    private long[][][] dp;
    private long Mod = (long) (1e9 + 7);

    public int countValidSubsets(int[] parent, int[] a, int k) {
        int n = parent.length;
        tree = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            tree[i] = new ArrayList();
        }
        // count ways in subtree i, sum mod k, selected/not selected i
        dp = new long[n][k][2];
        for (int i = 0; i < n; ++i) {
            int p = parent[i];
            if (p != -1) {
                tree[p].add(i);
            }
        }
        solve(0, a, k);
        long res = dp[0][0][0] + dp[0][0][1] - 1;
        res = (res + Mod) % Mod;
        return (int) res;
    }

    private void solve(int i, int[] a, int k) {
        dp[i][0][0] = 1;
        int mod = a[i] % k;
        dp[i][mod][1] = 1;
        for (int ne : tree[i]) {
            solve(ne, a, k);
            mergeconv(ne, i, k);
        }
    }

    private void mergeconv(int ne, int i, int k) {
        long[][] ndp = new long[k][2];
        for (int cur = 0; cur < k; ++cur) {
            for (int nev = 0; nev < k; ++nev) {
                int summod = (cur + nev) % k;
                ndp[summod][0] += dp[i][cur][0] * (dp[ne][nev][0] + dp[ne][nev][1]);
                ndp[summod][0] %= Mod;
                ndp[summod][1] += dp[i][cur][1] * dp[ne][nev][0];
                ndp[summod][1] %= Mod;

            }
        }
        dp[i] = ndp;
    }
}
