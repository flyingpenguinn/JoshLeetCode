import java.util.Arrays;

public class NumberOfConnectedComponentsInUndirectedGraph {
    public int countComponents(int n, int[][] es) {
        int[] pa = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            pa[i] = i;
            size[i] = 1;
        }
        for (int[] e : es) {
            union(pa, size, e[0], e[1]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (pa[i] == i) {
                res++;
            }
        }
        return res;
    }

    private void union(int[] pa, int[] size, int i, int j) {
        int pi = find(pa, i);
        int pj = find(pa, j);
        if (pi == pj) {
            return;
        }
        if (size[pi] < size[pj]) {
            pa[pi] = pj;
            size[pj] += size[pi];
        } else {
            pa[pj] = pi;
            size[pi] += size[pj];
        }
    }

    private int find(int[] pa, int i) {
        if (pa[i] == i) {
            return i;
        }
        int rt = find(pa, pa[i]);
        pa[i] = rt;
        return rt;
    }
}
