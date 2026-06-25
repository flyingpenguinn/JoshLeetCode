import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class DistinctGatePathsToLca {
    // todo multi state lca
    private static final long MOD = 1_000_000_007L;

    private int bits;
    private int[] depth;
    private int[][] up;
    private long[][][][] jump;

    public int distinctPaths(int n, int[] parent, int[][] gates, int[][] queries) {
        bits = 1;
        while ((1 << bits) <= n) {
            bits++;
        }

        depth = new int[n];
        up = new int[bits][n];
        jump = new long[bits][n][2][2];

        buildBase(n, parent, gates);
        buildLifting(n);

        int ans = 0;

        for (int[] q : queries) {
            int a = q[0];
            int ac = q[1];
            int b = q[2];
            int bc = q[3];

            int lca = getLca(a, b);

            long aw = countWays(a, ac, lca);
            long bw = countWays(b, bc, lca);

            int ways = (int) (aw * bw % MOD);
            ans ^= ways;
        }

        return ans;
    }

    private void buildBase(int n, int[] parent, int[][] gates) {
        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        up[0][0] = 0;

        jump[0][0][0][0] = 1;
        jump[0][0][1][1] = 1;

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(0);

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : tree[u]) {
                depth[v] = depth[u] + 1;
                up[0][v] = u;

                long red = gates[v][0];
                long blue = gates[v][1];
                long white = gates[v][2];

                jump[0][v][0][0] = blue;
                jump[0][v][0][1] = white;
                jump[0][v][1][0] = white;
                jump[0][v][1][1] = red;

                q.offer(v);
            }
        }
    }

    private void buildLifting(int n) {
        for (int k = 1; k < bits; k++) {
            for (int u = 0; u < n; u++) {
                int mid = up[k - 1][u];

                up[k][u] = up[k - 1][mid];
                jump[k][u] = multiply(
                        jump[k - 1][u],
                        jump[k - 1][mid]
                );
            }
        }
    }

    private int getLca(int a, int b) {
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int diff = depth[a] - depth[b];

        for (int k = 0; k < bits; k++) {
            if (((diff >> k) & 1) != 0) {
                a = up[k][a];
            }
        }

        if (a == b) {
            return a;
        }

        for (int k = bits - 1; k >= 0; k--) {
            if (up[k][a] != up[k][b]) {
                a = up[k][a];
                b = up[k][b];
            }
        }

        return up[0][a];
    }

    private long countWays(int node, int card, int ancestor) {
        long[] ways = new long[2];
        ways[card] = 1;

        int diff = depth[node] - depth[ancestor];

        for (int k = 0; k < bits; k++) {
            if (((diff >> k) & 1) != 0) {
                ways = apply(ways, jump[k][node]);
                node = up[k][node];
            }
        }

        return (ways[0] + ways[1]) % MOD;
    }

    private long[] apply(long[] ways, long[][] mat) {
        long[] next = new long[2];

        for (int start = 0; start < 2; start++) {
            for (int end = 0; end < 2; end++) {
                next[end] += ways[start] * mat[start][end];
                next[end] %= MOD;
            }
        }

        return next;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        long[][] res = new long[2][2];

        for (int start = 0; start < 2; start++) {
            for (int mid = 0; mid < 2; mid++) {
                for (int end = 0; end < 2; end++) {
                    res[start][end] += a[start][mid] * b[mid][end];
                    res[start][end] %= MOD;
                }
            }
        }

        return res;
    }
}
