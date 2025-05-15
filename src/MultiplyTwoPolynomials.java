import base.ArrayUtils;

import java.sql.Array;
import java.util.Arrays;

public class MultiplyTwoPolynomials {
    // Karatsuba algo divide and conquer, n^3/2 complexity
    public long[] multiply(int[] a, int[] b) {
        int an = a.length;
        int bn = b.length;
        int n = 1;
        // pad to 2^n
        int maxn = Math.max(an, bn);
        while (n < maxn) {
            n *= 2;
        }
        long[] pa = new long[n];
        for (int i = 0; i < n; ++i) {
            if (i < an) {
                pa[i] = a[i];
            }
        }
        long[] pb = new long[n];
        for (int i = 0; i < n; ++i) {
            if (i < bn) {
                pb[i] = b[i];
            }
        }
        long[] res = solve(pa, pb);
        int resn = an -1 + bn -1;
        return Arrays.copyOfRange(res, 0, resn + 1);
    }

    private long[] solve(long[] a, long[] b) {
        // same length
        int n = a.length;
        if (n <=128) {
            long[] res = new long[2*n];
            for(int i=0; i<n; ++i){
                for(int j=0; j<n; ++j){
                    res[i+j] += a[i]*b[j];
                }
            }
            return res;
        }
        int half = n / 2;
        long[] alow = Arrays.copyOfRange(a, 0, half);
        long[] ahigh = Arrays.copyOfRange(a, half, n);
        long[] blow = Arrays.copyOfRange(b, 0, half);
        long[] bhigh = Arrays.copyOfRange(b, half, n);
        long[] p0 = solve(alow, blow);
        long[] p2 = solve(ahigh, bhigh);
        long[] asum = new long[half];
        for (int i = 0; i < half; ++i) {
            asum[i] = alow[i] + ahigh[i];
        }
        long[] bsum = new long[half];
        for (int i = 0; i < half; ++i) {
            bsum[i] = blow[i] + bhigh[i];
        }
        long[] p1 = solve(asum, bsum);
        for (int i = 0; i < n; ++i) {
            p1[i] -= p0[i];
            p1[i] -= p2[i];
        }
        long[] res = new long[2 * n];
        for (int i = 0; i < n; ++i) {
            res[i] += p0[i];
        }
        for (int i = 0; i < n; ++i) {
            res[i + half] += p1[i];
        }
        for (int i = 0; i < n; ++i) {
            res[i + 2 * half] += p2[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MultiplyTwoPolynomials().multiply(ArrayUtils.read1d("[3,2,5]"), ArrayUtils.read1d("[1,4]"))));
    }
}
