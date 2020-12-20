import java.util.Arrays;

public class CheckExistenceOfEdgeLenLimitedPath {
    // sort queries ! as we "allow" bigger edges, it becomes more and more connected
    public boolean[] distanceLimitedPathsExist(int n, int[][] es, int[][] qs) {
        int[] pa = new int[n];
        for (int i = 0; i < n; i++) {
            pa[i] = i;
        }
        int[][] qs2 = new int[qs.length][4];
        for (int i = 0; i < qs.length; i++) {
            for (int j = 0; j < 3; j++) {
                qs2[i][j] = qs[i][j];
            }
            qs2[i][3] = i;
        }
        Arrays.sort(es, (x, y) -> Integer.compare(x[2], y[2]));
        Arrays.sort(qs2, (x, y) -> Integer.compare(x[2], y[2]));
        int ei = 0;
        boolean[] rr = new boolean[qs.length];
        for (int i = 0; i < qs2.length; i++) {
            //as q's limit increases we can allow merging more and more edges
            while (ei < es.length && es[ei][2] < qs2[i][2]) {
                union(pa, es[ei][0], es[ei][1]);
                ei++;
            }
            if (find(pa, qs2[i][0]) == find(pa, qs2[i][1])) {
                rr[qs2[i][3]] = true;
            }
        }
        return rr;
    }

    private int find(int[] pa, int i) {
        if (pa[i] == i) {
            return i;
        }
        int rt = find(pa, pa[i]);
        pa[i] = rt;
        return rt;
    }

    private void union(int[] pa, int i, int j) {
        int pi = find(pa, i);
        int pj = find(pa, j);
        if (pi == pj) {
            return;
        }
        pa[pi] = pj;
        return;
    }
}
