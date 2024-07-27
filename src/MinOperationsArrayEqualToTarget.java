public class MinOperationsArrayEqualToTarget {
    // extension of lc 1563
    public long minimumOperations(int[] s, int[] t) {
        int n = s.length;
        long[] a = new long[n];
        for (int i = 0; i < n; ++i) {
            a[i] = t[i] - s[i];
        }
        long last = 0;
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                last = a[i];
                continue;
            }
            if (a[i] * last >= 0) {
                if (a[i] > 0 && a[i] > last) {
                    res += a[i] - last;
                } else if (a[i] < 0 && a[i] < last) {
                    res += last - a[i];
                }
            } else {
                res += Math.abs(a[i]);
            }

            last = a[i];
        }
        return res;
    }
}
