import base.ArrayUtils;

import java.util.*;

public class ShortestPathInAweightedTree {
    private int[] bit;
    private int n;

    private void init(int n) {
        this.n = n;
        bit = new int[n + 1];
    }

    private int q(int i) {
        int res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i & (-i);
        }
        return res;
    }

    private void u(int i, int d) {
        while (i <= n) {
            bit[i] += d;
            i += i & (-i);
        }
    }

    private void rangeu(int l, int u, int d) {
        u(l, d);
        if (u + 1 <= n) {
            u(u + 1, -d);
        }
    }

    private Map<Integer, Integer>[] t;
    private int[] depth;
    private int[] in;
    private int[] out;
    private int time = 0;

    public int[] treeQueries(int n, int[][] edges, int[][] qs) {
        t = new HashMap[n + 1];
        for(int i=1; i<=n; ++i){
            t[i] = new HashMap<>();
        }
        depth = new int[n + 1];
        in = new int[n + 1];
        out = new int[n + 1];
        init(n);
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int w = e[2];
            t[v1].put(v2, w);
            t[v2].put(v1, w);
        }
        dfs(1, 0, -1);
        int qn = qs.length;
        int rn = 0;
        for (int i = 0; i < qn; ++i) {
            if (qs[i][0] == 2) {
                ++rn;
            }
        }
        int[] res = new int[rn];
        int ri = 0;
        for(int i=0; i<qn; ++i){
            if (qs[i][0] == 1) {
                int u = qs[i][1];
                int v = qs[i][2];
                int w = qs[i][3];
                int diff = w - t[u].get(v);
                if (depth[u] > depth[v]) {
                    rangeu(in[u], out[u], diff);
                } else {
                    rangeu(in[v], out[v], diff);
                }
                t[u].put(v, w);
            } else {
                int v = qs[i][1];
                int cur = depth[v] + q(in[v]);
                res[ri++] = cur;
            }
        }
        return res;
    }

    private void dfs(int i, int d, int parent) {

        depth[i] = d;
        in[i] = ++time;
        for (int ne : t[i].keySet()) {
            if (ne == parent) {
                continue;
            }
            dfs(ne, d + t[i].get(ne), i);
        }
        out[i] = time;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ShortestPathInAweightedTree().treeQueries(2, ArrayUtils.read("[[1,2,7]]"), ArrayUtils.read("[[2,2],[1,1,2,4],[2,2]]"))));
        System.out.println(Arrays.toString(new ShortestPathInAweightedTree().treeQueries(3, ArrayUtils.read("[[1,2,2],[1,3,4]]"), ArrayUtils.read("[[2,1],[2,3],[1,1,3,7],[2,2],[2,3]]"))));
    }
}
