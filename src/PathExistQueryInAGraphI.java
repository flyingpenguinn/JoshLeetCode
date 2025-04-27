import java.util.Arrays;

public class PathExistQueryInAGraphI {
    private int[] pa;

    private int find(int i) {
        if (pa[i] == -1) {
            return i;
        }
        int rt = find(pa[i]);
        pa[i] = rt;
        return rt;
    }

    private void union(int i, int j) {
        int ai = find(i);
        int aj = find(j);
        if (ai == aj) {
            return;
        }
        pa[ai] = aj;
    }

    public boolean[] pathExistenceQueries(int n, int[] a, int maxDiff, int[][] qs) {
        int start = a[0];
        pa = new int[n];
        Arrays.fill(pa, -1);
        int end = start + maxDiff;
        int startindex = 0;
        int endindex = 0;
        for (int i = 1; i < n; ++i) {
            int cs = a[i];
            int ce = cs + maxDiff;
            if (cs > end) {
                for (int j = startindex; j + 1 <= endindex; ++j) {
                    union(j, j + 1);
                }
                start = cs;
                end = ce;
                startindex = i;
                endindex = i;
            } else {
                end = Math.max(end, ce);
                endindex = i;
            }
        }
        for (int j = startindex; j + 1 <= endindex; ++j) {
            union(j, j + 1);
        }
        boolean[] res = new boolean[qs.length];
        int ri = 0;
        for (int qi = 0; qi < qs.length; ++qi) {
            int v1 = qs[qi][0];
            int v2 = qs[qi][1];
            if (find(v1) == find(v2)) {
                res[qi] = true;
            }
        }
        return res;
    }
}
