package base;

import java.util.Arrays;

public class UnionFind {
    static class UF {
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
}
