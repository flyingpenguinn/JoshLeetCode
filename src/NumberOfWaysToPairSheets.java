import base.ArrayUtils;

import java.util.Arrays;

public class NumberOfWaysToPairSheets {
    // TODO wtf
    private static final long MOD = 1000000007L;

    public int numberOfWays(int n, int[] limit) {
        int m = limit.length;
        int[] a = limit.clone();
        Arrays.sort(a);

        long[] p = new long[2 * m + 10];
        int ps = 0;

        ps = add(p, ps, 1L, n);
        ps = add(p, ps, (long) n, n);

        ps = add(p, ps, (long) (n / 2) + 1L, n);

        for (int i = 0; i < m; i++) {
            long L = (long) a[i];
            ps = add(p, ps, L + 1L, n);
            ps = add(p, ps, (long) n - L, n);
        }

        Arrays.sort(p, 0, ps);

        long[] q = new long[ps];
        int qs = 0;
        for (int i = 0; i < ps; i++) {
            if (i == 0 || p[i] != p[i - 1]) {
                q[qs++] = p[i];
            }
        }

        long ans = 0L;

        for (int i = 0; i + 1 < qs; i++) {
            long s = q[i];
            long e = q[i + 1];

            long ss = s;
            long ee = e;

            if (ss < 1L) {
                ss = 1L;
            }
            if (ee > (long) n) {
                ee = (long) n;
            }
            if (ss >= ee) {
                continue;
            }

            long x = ss;
            if (x >= (long) n) {
                continue;
            }

            long g1 = ge(a, x);
            long g2 = ge(a, (long) n - x);
            long g3 = ge(a, Math.max(x, (long) n - x));

            long val = (g1 % MOD) * (g2 % MOD) % MOD;
            val = (val - (g3 % MOD)) % MOD;
            if (val < 0) {
                val += MOD;
            }

            long len = ee - ss;
            ans = (ans + (val * (len % MOD)) % MOD) % MOD;
        }

        return (int) ans;
    }

    private int add(long[] p, int ps, long v, int n) {
        if (v >= 1L && v <= (long) n) {
            p[ps++] = v;
        }
        return ps;
    }

    private long ge(int[] a, long t) {
        if (t <= 0L) {
            return (long) a.length;
        }
        if (t > (long) Integer.MAX_VALUE) {
            return 0L;
        }
        int x = (int) t;
        int i = lb(a, x);
        return (long) a.length - (long) i;
    }

    private int lb(int[] a, int x) {
        int l = 0;
        int r = a.length;
        while (l < r) {
            int mid = l + ((r - l) >>> 1);
            if (a[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    static void main() {
        System.out.println(new NumberOfWaysToPairSheets().numberOfWays(9, ArrayUtils.read1d("6, 87")));
        System.out.println(new NumberOfWaysToPairSheets().numberOfWays(9, ArrayUtils.read1d("89, 75")));
        System.out.println(new NumberOfWaysToPairSheets().numberOfWays(4, ArrayUtils.read1d("3,1,2")));
        System.out.println(new NumberOfWaysToPairSheets().numberOfWays(3, ArrayUtils.read1d("1,2")));
        System.out.println(new NumberOfWaysToPairSheets().numberOfWays(3, ArrayUtils.read1d("2,2")));
    }
}
