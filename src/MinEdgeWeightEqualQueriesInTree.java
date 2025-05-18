import base.ArrayUtils;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class MinEdgeWeightEqualQueriesInTree {
    // binary lifting to get LCA
    /*
    There are at most 26 different edge weights. For each node, we can count the number of edges of each weight from root to the current node.
And then for each query (x, y),
The number of edges of a particular weight = num[x] + num[y] - 2 * num[LCA(x, y)].

     */
    private Map<Integer, Integer>[] t;
    private int[][] lift;
    private int[] level;
    private int[][] cnt;
    private int BITS = 18;
    private int WCNT = 27;

    private int getlca(int v1, int v2) {
        if (level[v1] < level[v2]) {
            int tmp = v1;
            v1 = v2;
            v2 = tmp;
        }
        int diff = level[v1] - level[v2];
        for (int k = 0; k < BITS; ++k) {
            if (((diff >> k) & 1) == 1) {
                v1 = lift[k][v1];
            }
        }
        if (v1 == v2) {
            return v1;
        }
        for (int k = BITS - 1; k >= 0; --k) {
            if (lift[k][v1] != lift[k][v2]) {
                v1 = lift[k][v1];
                v2 = lift[k][v2];
            }
        }
        return lift[0][v1];
    }

    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        t = new HashMap[n];
        lift = new int[BITS][n];
        level = new int[n];
        cnt = new int[n][WCNT];
        for (int i = 0; i < n; ++i) {
            t[i] = new HashMap<Integer, Integer>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int w = e[2];
            t[v1].put(v2, w);
            t[v2].put(v1, w);
        }
        int[] ccnt = new int[WCNT];
        dfs(0, -1, 0, ccnt);
        for (int k = 1; k < BITS; ++k) {
            for (int i = 0; i < n; ++i) {
                lift[k][i] = lift[k - 1][lift[k - 1][i]];
            }
        }
        int qn = queries.length;
        int[] res = new int[qn];
        for (int i = 0; i < qn; ++i) {
            int v1 = queries[i][0];
            int v2 = queries[i][1];
            int lca = getlca(v1, v2);
            int[] cnt1 = cnt[v1];
            int[] cnt2 = cnt[v2];
            int[] cnt3 = cnt[lca];
            int[] allcnt = new int[WCNT];
            for (int k = 0; k < WCNT; ++k) {
                allcnt[k] = cnt1[k] + cnt2[k] - 2 * cnt3[k];
            }
            int allsum = 0;
            int maxocc = 0;
            for (int k = 0; k < WCNT; ++k) {
                if (allcnt[k] == 0) {
                    continue;
                }
                allsum += allcnt[k];
                maxocc = Math.max(maxocc, allcnt[k]);
            }
            int changes = allsum - maxocc;
            res[i] = changes;
        }
        return res;
    }

    private void dfs(int i, int parent, int cl, int[] ccnt) {
        level[i] = cl;
        for (int k = 0; k < WCNT; ++k) {
            cnt[i][k] = ccnt[k];
        }
        for (int ne : t[i].keySet()) {
            if (parent == ne) {
                continue;
            }
            int cw = t[i].get(ne);
            ++ccnt[cw];
            lift[0][ne] = i;
            dfs(ne, i, cl + 1, ccnt);
            --ccnt[cw];
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MinEdgeWeightEqualQueriesInTree().minOperationsQueries(8, ArrayUtils.read("[[1,2,6],[1,3,4],[2,4,6],[2,5,3],[3,6,6],[3,0,8],[7,0,2]]"), ArrayUtils.read("[[4,6],[0,4],[6,5],[7,4]]"))));
    }
}
