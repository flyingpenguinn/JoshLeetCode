public class IncrementalEventWeightedCycleQueries {
    private static class UF {
        int[] pa;
        int[] size;
        int[] xor;

        UF(int n) {
            pa = new int[n];
            size = new int[n];
            xor = new int[n];
            for (int i = 0; i < n; ++i) {
                pa[i] = -1;
                size[i] = 1;
            }
        }

        int find(int i) {
            if (pa[i] == -1) {
                return i;
            }
            int fa = pa[i];
            int r = find(fa);
            xor[i] ^= xor[fa];
            pa[i] = r;
            return r;
        }

        boolean union(int a, int b, int w) {
            int ra = find(a);
            int rb = find(b);
            int xa = xor[a];
            int xb = xor[b];
            if (ra == rb) {
                return (xa ^ xb) == w;
            }
            if (size[ra] >= size[rb]) {
                pa[rb] = ra;
                xor[rb] = xa ^ xb ^ w;
                size[ra] += size[rb];
            } else {
                pa[ra] = rb;
                xor[ra] = xa ^ xb ^ w;
                size[rb] += size[ra];
            }

            return true;
        }
    }

    public int numberOfEdgesAdded(int n, int[][] edges) {
        UF uf = new UF(n);
        int res = 0;
        for (int[] e : edges) {
            if (uf.union(e[0], e[1], e[2])) {
                ++res;
            }
        }
        return res;
    }
}
