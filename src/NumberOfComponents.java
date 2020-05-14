import java.util.Arrays;

public class NumberOfComponents {
    public int countComponents(int n, int[][] edges) {
        DisjointSet djs = new DisjointSet(n);
        for (int[] e : edges) {
            djs.union(e[0], e[1]);
        }
        return djs.sets;
    }


    class DisjointSet {
        int[] p;
        int[] size;
        int sets;

        public DisjointSet(int n) {
            p = new int[n];
            Arrays.fill(p, -1);
            size = new int[n];
            Arrays.fill(size, 1);
            sets = n;
        }

        int find(int i) {
            if (p[i] != -1) {
                int rt = find(p[i]);
                p[i] = rt; // compression
                return rt;
            } else {
                return i;
            }
        }

        void union(int i, int j) {
            int pi = find(i);
            int pj = find(j);
            if (pi == pj) {
                return;
            }
            int si = size[pi];
            int sj = size[pj];
            // by size
            if (si < sj) {
                p[pi] = pj;
                size[pj] += size[pi];
            } else {
                p[pj] = pi;
                size[pi] += size[pj];
            }
            sets--;
        }
    }
}
