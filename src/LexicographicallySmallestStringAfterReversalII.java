public class LexicographicallySmallestStringAfterReversalII {
    // TODO: binary search and string hash
    public String lexSmallest(String s) {
        int n = s.length();
        char[] a = s.toCharArray();
        char[] r = new StringBuilder(s).reverse().toString().toCharArray();
        long m1 = 1000000007L, m2 = 1000000009L, base = 911382323L;
        long[] p1 = new long[n + 1], p2 = new long[n + 1];
        long[] h1 = new long[n + 1], h2 = new long[n + 1];
        long[] rh1 = new long[n + 1], rh2 = new long[n + 1];
        p1[0] = 1;
        p2[0] = 1;
        for (int i = 0; i < n; ++i) {
            p1[i + 1] = (p1[i] * base) % m1;
            p2[i + 1] = (p2[i] * base) % m2;
            h1[i + 1] = (h1[i] * base + a[i]) % m1;
            h2[i + 1] = (h2[i] * base + a[i]) % m2;
            rh1[i + 1] = (rh1[i] * base + r[i]) % m1;
            rh2[i + 1] = (rh2[i] * base + r[i]) % m2;
        }
        int bt = 0, bi = 0;
        for (int i = 1; i < n; ++i) {
            if (cmp(0, i, bt, bi, a, n, h1, h2, rh1, rh2, p1, p2, m1, m2) < 0) {
                bt = 0;
                bi = i;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (cmp(1, i, bt, bi, a, n, h1, h2, rh1, rh2, p1, p2, m1, m2) < 0) {
                bt = 1;
                bi = i;
            }
        }
        StringBuilder t = new StringBuilder(n);
        if (bt == 0) {
            int i = bi;
            for (int j = i; j >= 0; --j) {
                t.append(a[j]);
            }
            if (i + 1 < n) {
                t.append(a, i + 1, n - i - 1);
            }
        } else {
            int i = bi;
            if (i > 0) {
                t.append(a, 0, i);
            }
            for (int j = n - 1; j >= i; --j) {
                t.append(a[j]);
            }
        }
        return t.toString();
    }

    private int cmp(int t1, int i1, int t2, int i2, char[] a, int n,
                    long[] h1, long[] h2, long[] rh1, long[] rh2, long[] p1, long[] p2, long m1, long m2) {
        int lo = 0, hi = n;
        while (lo < hi) {
            int mid = (lo + hi + 1) >>> 1;
            long[] x = prefHash(t1, i1, mid, n, h1, h2, rh1, rh2, p1, p2, m1, m2);
            long[] y = prefHash(t2, i2, mid, n, h1, h2, rh1, rh2, p1, p2, m1, m2);
            if (x[0] == y[0] && x[1] == y[1]) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        if (lo == n) {
            return 0;
        }
        char c1 = charAt(t1, i1, lo, a, n);
        char c2 = charAt(t2, i2, lo, a, n);
        if (c1 < c2) {
            return -1;
        } else if (c1 > c2) {
            return 1;
        } else {
            return 0;
        }
    }

    private char charAt(int t, int i, int pos, char[] a, int n) {
        if (t == 0) {
            if (pos <= i) {
                return a[i - pos];
            } else {
                return a[pos];
            }
        } else {
            if (pos < i) {
                return a[pos];
            } else {
                int k = pos - i;
                return a[n - 1 - k];
            }
        }
    }

    private long[] prefHash(int t, int i, int len, int n,
                            long[] h1, long[] h2, long[] rh1, long[] rh2, long[] p1, long[] p2, long m1, long m2) {
        if (t == 0) {
            int L1 = Math.min(len, i + 1);
            long[] A = revSegHash(i - L1 + 1, i, n, rh1, rh2, p1, p2, m1, m2);
            if (len == L1) {
                return A;
            } else {
                int L2 = len - L1;
                long[] B = segHash(i + 1, i + L2, h1, h2, p1, p2, m1, m2);
                long C1 = (A[0] * p1[L2] + B[0]) % m1;
                long C2 = (A[1] * p2[L2] + B[1]) % m2;
                return new long[]{C1, C2};
            }
        } else {
            int L1 = Math.min(len, i);
            long[] A = segHash(0, L1 - 1, h1, h2, p1, p2, m1, m2);
            if (len == L1) {
                return A;
            } else {
                int L2 = len - L1;
                long[] B = segHashR(0, L2 - 1, rh1, rh2, p1, p2, m1, m2);
                long C1 = (A[0] * p1[L2] + B[0]) % m1;
                long C2 = (A[1] * p2[L2] + B[1]) % m2;
                return new long[]{C1, C2};
            }
        }
    }

    private long[] segHash(int l, int r, long[] h1, long[] h2, long[] p1, long[] p2, long m1, long m2) {
        if (l > r) {
            return new long[]{0L, 0L};
        }
        long x1 = (h1[r + 1] - (h1[l] * p1[r - l + 1]) % m1 + m1) % m1;
        long x2 = (h2[r + 1] - (h2[l] * p2[r - l + 1]) % m2 + m2) % m2;
        return new long[]{x1, x2};
    }

    private long[] segHashR(int l, int r, long[] rh1, long[] rh2, long[] p1, long[] p2, long m1, long m2) {
        if (l > r) {
            return new long[]{0L, 0L};
        }
        long x1 = (rh1[r + 1] - (rh1[l] * p1[r - l + 1]) % m1 + m1) % m1;
        long x2 = (rh2[r + 1] - (rh2[l] * p2[r - l + 1]) % m2 + m2) % m2;
        return new long[]{x1, x2};
    }

    private long[] revSegHash(int l, int r, int n, long[] rh1, long[] rh2, long[] p1, long[] p2, long m1, long m2) {
        if (l > r) {
            return new long[]{0L, 0L};
        }
        int rl = n - 1 - r;
        int rr = n - 1 - l;
        return segHashR(rl, rr, rh1, rh2, p1, p2, m1, m2);
    }
}

