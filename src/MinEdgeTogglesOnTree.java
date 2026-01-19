import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinEdgeTogglesOnTree {
    // TODO
    private int[] h, to, nx, id, d;
    private boolean[] use;
    private boolean ok;

    private int dfs(int u, int p, int pe) {
        int x = 0;
        for (int e = h[u]; e != -1; e = nx[e]) {
            int v = to[e];
            if (v == p) {
                continue;
            }
            x ^= dfs(v, u, id[e]);
            if (!ok) {
                return 0;
            }
        }
        if (u == 0) {
            if ((d[0] ^ x) != 0) {
                ok = false;
            }
            return 0;
        }
        int v = d[u] ^ x;
        if (v == 1) {
            use[pe] = true;
        }
        return v;
    }

    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        d = new int[n];
        int xo = 0;
        for (int i = 0; i < n; ++i) {
            d[i] = (start.charAt(i) - '0') ^ (target.charAt(i) - '0');
            xo ^= d[i];
        }
        if (xo != 0) {
            return Collections.singletonList(-1);
        }

        int m = n - 1;
        h = new int[n];
        Arrays.fill(h, -1);
        to = new int[m * 2];
        nx = new int[m * 2];
        id = new int[m * 2];

        int it = 0;
        for (int i = 0; i < m; ++i) {
            int a = edges[i][0], b = edges[i][1];

            to[it] = b;
            id[it] = i;
            nx[it] = h[a];
            h[a] = it;
            it++;

            to[it] = a;
            id[it] = i;
            nx[it] = h[b];
            h[b] = it;
            it++;
        }

        use = new boolean[m];
        ok = true;
        dfs(0, -1, -1);

        if (!ok) {
            return Collections.singletonList(-1);
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            if (use[i]) {
                res.add(i);
            }
        }
        return res;
    }
}
