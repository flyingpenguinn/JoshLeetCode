import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PathExistenceQueriesInGraphII {
    // binary lifting template. Similar to lca
    private void buildlifting() {
        for (int k = 1; k < BITS; k++) {
            for (int i = 0; i < lift[k].length; i++) {
                lift[k][i] = lift[k - 1][lift[k - 1][i]];
            }
        }
    }

    private int getdist(int v1, int v2, int n) {

        // v1 <= v2
        if (v1 > v2) {
            int tmp = v1;
            v1 = v2;
            v2 = tmp;
        }
        if (v1 == v2) {
            return 0;
        }
        int res = 0;
        for (int k = BITS - 1; k >= 0 && v1 < v2; k--) {
            if (lift[k][v1] < v2) {
                v1 = lift[k][v1];
                res += (1 << k);
            }
        }
        if (lift[0][v1] < n && lift[0][v1] >= v2) {
            return res + 1;
        }
        return -1;
    }

    private int[][] lift;
    private int BITS = 17;

    public int[] pathExistenceQueries(int n, int[] oa, int maxDiff, int[][] queries) {
        int[] a = Arrays.copyOf(oa, n);
        Arrays.sort(a);
        Map<Integer, Integer> rm = new HashMap<>();
        int rank = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || a[i] != a[i - 1]) {
                rm.put(a[i], rank);
                ++rank;
            }
        }
        int[] na = new int[rm.size()];
        int ni = 0;
        for (int k : rm.keySet()) {
            na[ni++] = k;
        }
        Arrays.sort(na);

        int nn = na.length;
        lift = new int[BITS][nn + 1];
        Arrays.fill(lift[0], nn);

        int i = 0;
        int j = 0;
        while (i < nn) {

            int cr = rm.get(na[i]);
            while (j < nn && na[j] <= na[i] + maxDiff) {
                ++j;
            }
            if (j - 1 > i) {
                lift[0][cr] = rm.get(na[j - 1]);
            }
            ++i;
        }
        buildlifting();

        int[] res = new int[queries.length];
        int ri = 0;
        for (int[] q : queries) {
            if (q[0] == q[1]) {
                res[ri++] = 0;
                continue;
            }
            int v1 = Math.min(oa[q[0]], oa[q[1]]);
            int v2 = Math.max(oa[q[0]], oa[q[1]]);
            int r1 = rm.get(v1);
            int r2 = rm.get(v2);

            if (r1 == r2) {
                res[ri++] = 1;
            } else {
                int cur = getdist(r1, r2, nn);
                res[ri++] = cur;
            }

        }
        return res;
    }
}

