import java.util.Arrays;
import java.util.List;

public class MaxNumberOfEdgesRemoveStillTraversable {
    // try to keep type 3 edges. calculate MST using 3 first then 1 and 2. those 1 and 2 are deletable
    // DSU UF template
    private static class UF {
        public int[] pa;
        public int[] psize;

        public UF(int size) {
            pa = new int[size];
            psize = new int[size];
            Arrays.fill(pa, -1);
            Arrays.fill(psize, 1);
        }

        public int find(int i) {
            if (pa[i] == -1) {
                return i;
            }
            int rt = find(pa[i]);
            pa[i] = rt;
            return rt;
        }

        public boolean union(int i, int j) {
            int ai = find(i);
            int aj = find(j);
            if (ai == aj) {
                return false;
            }
            if (psize[ai] < psize[aj]) {
                pa[ai] = aj;
                psize[aj] += psize[ai];
            } else {
                pa[aj] = ai;
                psize[ai] += psize[aj];
            }
            return true;
        }

    }

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UF ufa = new UF(n + 1);
        UF ufb = new UF(n + 1);
        int res = 0;
        for (int[] e : edges) {
            int type = e[0];
            if (type != 3) {
                continue;
            }
            int u = e[1];
            int v = e[2];
            boolean ra = ufa.union(u, v);
            boolean rb = ufb.union(u, v);
            if (!ra && !rb) {
                ++res;
            }
        }
        for (int[] e : edges) {
            int type = e[0];
            if (type == 3) {
                continue;
            }
            int u = e[1];
            int v = e[2];
            if (type == 1) {
                boolean ra = ufa.union(u, v);
                if (!ra) {
                    ++res;
                }
            } else {
                boolean rb = ufb.union(u, v);
                if (!rb) {
                    ++res;
                }
            }
        }
        int roota = getRootCounts(ufa);
        if (roota > 1) {
            return -1;
        }
        int rootb = getRootCounts(ufb);
        if (rootb > 1) {
            return -1;
        }
        return res;
    }

    private static int getRootCounts(UF ufb) {
        int rootb = 0;
        for (int i = 1; i < ufb.pa.length; ++i) {
            if (ufb.pa[i] == -1) {
                ++rootb;
            }
        }
        return rootb;
    }

    private List<Integer> edge(int[] e) {
        return List.of(e[0], e[1], e[2]);
    }
}
