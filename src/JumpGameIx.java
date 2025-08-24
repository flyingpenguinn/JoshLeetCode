import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JumpGameIx {


    public int[] maxValue(int[] a) {
        int n = a.length;
        int[][] na = new int[n][2];
        for (int i = 0; i < n; ++i) {
            na[i][0] = a[i];
            na[i][1] = i;
        }
        Arrays.sort(na, (x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            } else {
                return Integer.compare(x[1], y[1]);
            }
        });
        int[] rm = new int[n];
        int rank = 1;
        for (int i = 0; i < n; ++i) {
            rm[na[i][1]] = rank++;
        }
        int maxrank = rm[0];
        int maxv = a[0];
        int s = 0;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int cr = rm[i];
            maxv = Math.max(maxv, a[i]);
            maxrank = Math.max(cr, maxrank);
            if (maxrank == i + 1) {
                for (int j = s; j <= i; ++j) {
                    res[j] = maxv;
                }
                s = i + 1;
                if (s < n) {
                    maxrank = rm[s];
                    maxv = a[s];
                }
            }
        }
        return res;
    }

}


class JumpGameIxMySubmission {
    // aced but...
    private int[] bit;

    public int[] maxValue(int[] a) {
        int n = a.length;
        int[] na = Arrays.copyOf(a, n);
        Arrays.sort(na);
        Map<Integer, Integer> rm = new HashMap<>();
        int rank = 1;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || na[i] != na[i - 1]) {
                rm.put(na[i], rank++);
            }
        }
        int[] res = Arrays.copyOf(a, n);
        while (true) {
            int[] nres = Arrays.copyOf(res, n);
            int[] maxleft = new int[n];

            maxleft[0] = res[0];
            for (int i = 1; i < n; ++i) {
                maxleft[i] = Math.max(maxleft[i - 1], res[i]);
            }

            bit = new int[rank + 1];
            Arrays.fill(bit, -1);
            for (int i = n - 1; i >= 0; --i) {
                int v = a[i];
                int rv = rm.get(v);
                int cv = q(rv - 1);
                int cur = Math.max(maxleft[i], cv);
                nres[i] = cur;
                u(rv, cur);
            }
            bit = new int[rank + 1];
            Arrays.fill(bit, -1);
            for (int i = 0; i < n; ++i) {
                int v = a[i];
                int rv = rm.get(v);
                int rrv = rank - rv;

                int cv = q(rrv);
                nres[i] = Math.max(nres[i], cv);
                u(rrv, nres[i]);
            }
            if (Arrays.equals(res, nres)) {
                break;
            } else {
                res = nres;
            }
        }


        return res;
    }

    void u(int i, int v) {
        while (i < bit.length) {
            bit[i] = Math.max(bit[i], v);
            i += i & (-i);
        }
    }

    int q(int i) {
        int res = 0;
        while (i > 0) {
            res = Math.max(bit[i], res);
            i -= i & (-i);
        }
        return res;

    }
}