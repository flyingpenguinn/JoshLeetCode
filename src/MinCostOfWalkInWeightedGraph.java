import java.util.Arrays;

public class MinCostOfWalkInWeightedGraph {
    private int[] pa;
    private int[] ands;

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        pa = new int[n];
        Arrays.fill(pa, -1);
        ands = new int[n];
        Arrays.fill(ands, -1);
        for (int[] es : edges) {
            int s = es[0];
            int e = es[1];
            int w = es[2];
            union(s, e, w);
        }
        int qn = query.length;
        int[] res = new int[qn];
        for (int i = 0; i < qn; ++i) {
            int v1 = query[i][0];
            int v2 = query[i][1];
            if (v1 == v2) {
                res[i] = 0;
                continue;
            }
            int a1 = find(v1);
            int a2 = find(v2);
            if (a1 == a2) {
                res[i] = ands[a1];
            } else {
                res[i] = -1;
            }
        }
        return res;
    }

    private void union(int v1, int v2, int w) {
        int a1 = find(v1);
        int a2 = find(v2);
        if (a1 == a2) {
            if (ands[a1] == -1) {
                ands[a1] = w;
            } else {
                ands[a1] &= w;
            }
        } else {
            pa[a1] = a2;
            if (ands[a2] == -1) {
                ands[a2] = w;
            } else {
                ands[a2] &= w;
            }
            if (ands[a1] != -1) {
                ands[a2] &= ands[a1];
            }
        }
    }

    private int find(int v) {
        if (pa[v] == -1) {
            return v;
        }
        int rt = find(pa[v]);
        pa[v] = rt;
        return rt;
    }

}
