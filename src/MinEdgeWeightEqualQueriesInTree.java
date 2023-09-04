import java.util.ArrayList;
import java.util.List;

public class MinEdgeWeightEqualQueriesInTree {
    // binary lifting to get LCA
    /*
    There are at most 26 different edge weights. For each node, we can count the number of edges of each weight from root to the current node.
And then for each query (x, y),
The number of edges of a particular weight = num[x] + num[y] - 2 * num[LCA(x, y)].

     */
    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        List<Pair>[] con = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            con[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            con[e[0]].add(new Pair(e[1], e[2] - 1));
            con[e[1]].add(new Pair(e[0], e[2] - 1));
        }

        int[][] all = new int[n][26];
        int[][] f = new int[n][20];
        int[] b = new int[n];
        int[] e = new int[n];
        int[] w = new int[26];

        int[] t = {0};
        dfs(con, 0, -1, b, e, f, t, all, w);
        f[0][0] = 0;

        for (int i = 1; i < 20; ++i) {
            for (int j = 0; j < n; ++j) {
                f[j][i] = f[f[j][i - 1]][i - 1];
            }
        }

        int[] result = new int[queries.length];
        int index = 0;
        for (int[] q : queries) {
            if (q[0] == q[1]) {
                result[index++] = 0;
                continue;
            }
            int tLca = lca(f, b, e, q[0], q[1]);
            int sum = 0, m = 0;
            for (int i = 0; i < 26; ++i) {
                int temp = all[q[0]][i] + all[q[1]][i] - (all[tLca][i] << 1);
                sum += temp;
                m = Math.max(m, temp);
            }
            result[index++] = sum - m;
        }
        return result;
    }

    private static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    void dfs(List<Pair>[] con, int x, int p, int[] b, int[] e, int[][] f, int[] t, int[][] all, int[] w) {
        all[x] = w.clone();
        f[x][0] = p;
        b[x] = ++t[0];
        for (Pair v : con[x]) {
            if (v.first != p) {
                w[v.second]++;
                dfs(con, v.first, x, b, e, f, t, all, w);
                w[v.second]--;
            }
        }
        e[x] = t[0];
    }

    boolean isAncestor(int[] b, int[] e, int x, int y) {
        return b[x] <= b[y] && e[x] >= e[y];
    }

    int lca(int[][] f, int[] b, int[] e, int x, int y) {
        if (isAncestor(b, e, x, y)) {
            return x;
        }
        if (isAncestor(b, e, y, x)) {
            return y;
        }

        int r = 0;
        for (int i = 19; i >= 0; --i) {
            int temp = f[x][i];
            if (isAncestor(b, e, temp, y)) {
                r = temp;
            } else {
                x = temp;
            }
        }
        return r;
    }
}
