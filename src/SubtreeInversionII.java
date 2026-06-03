import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubtreeInversionII {
    // subtree convolution template 3

    // dp[u][parity][d]:
// parity = flips from ancestors, controls signs going down.
// d = nearest flipped node inside u-subtree, distance from u, capped at k.
// d = k means none within distance < k.

// Base states must be real:
// flip u     -> d = 0
// not flip u -> d = k
// d = 1..k-1 can only appear after merging children. NEG to begin with

// Child parity is fixed:
// if u not flipped -> child parity = parity
// if u flipped     -> child parity = parity ^ 1

// Merge:
// child distance from u = childD + 1.
// If u flipped, childDist must be >= k.
// Else nearest existing flipped node and child nearest flipped node
// must satisfy d + childDist >= k.

    private int[] sum;
    private List<Integer>[] t;
    private int Min = (int) -1e9;

    public int subtreeInversionSum(int[][] edges, int[] a, int k) {
        int en = edges.length;
        int n = en + 1;
        t = new ArrayList[n];
        dp = new int[n][2][k + 1];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        sum = new int[n];
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            t[v1].add(v2);
            t[v2].add(v1);
        }
        dfs1(0, -1, a);
        dfs2(0, -1, k, a);
        int res = Integer.MIN_VALUE;
        for (int j = 0; j <= k; ++j) {
            res = Math.max(res, dp[0][0][j]);
        }
        return res;
    }

    int dfs1(int i, int p, int[] a) {
        sum[i] = a[i];
        for (int ne : t[i]) {
            if (ne == p) {
                continue;
            }
            int later = dfs1(ne, i, a);
            sum[i] += later;
        }
        return sum[i];
    }

    // at index i, flipped or not from ancestor, and dist to nearest flipped is d
    private int[][][] dp;

    // at node i
    private void dfs2(int i, int p, int k, int[] a) {
        // node 1, whether this node flipped from higher up or not. dist to last flipped node.
        // if dist to last flipped =0, this node is flipped
        Arrays.fill(dp[i][0], Min);
        Arrays.fill(dp[i][1], Min);

        dp[i][0][0] = -a[i];
        dp[i][1][0] = a[i];

        // if dist is already k we can freely choose
        dp[i][0][k] = a[i];
        dp[i][1][k] = -a[i];

        for (int ne : t[i]) {
            if (ne == p) {
                continue;
            }
            dfs2(ne, i, k, a);
            mergeconv(ne, i, k);
        }
    }

    private void mergeconv(int ne, int i, int k) {
        int[][] ndp = new int[2][k + 1];
        Arrays.fill(ndp[0], Min);
        Arrays.fill(ndp[1], Min);
        for (int cur = 0; cur <= k; ++cur) {
            for (int csign = 0; csign <= 1; ++csign) {
                for (int sub = 0; sub <= k; ++sub) {
                    if (cur == 0 && sub + 1 < k) {
                        continue;
                    }
                    if (cur + sub + 1 < k) {
                        continue;
                    }
                    int ssign = cur == 0 ? csign ^ 1 : csign;
                    int ndist = Math.min(k, Math.min(cur, sub + 1));
                    int sum = dp[i][csign][cur] + dp[ne][ssign][sub];
                    ndp[csign][ndist] = Math.max(ndp[csign][ndist], sum);
                }
            }
        }
        dp[i] = ndp;
    }

}
