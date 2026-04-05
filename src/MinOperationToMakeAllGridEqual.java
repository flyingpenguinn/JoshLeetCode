public class MinOperationToMakeAllGridEqual {
    // TODO
    public long minOperations(int[][] a, int k) {
        int m = a.length;
        int n = a[0].length;

        long mx = Long.MIN_VALUE;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                mx = Math.max(mx, a[i][j]);
            }
        }

        Long eq = null;
        long lb = mx;

        long[][] ra = new long[m + 1][n + 1];
        long[][] rb = new long[m + 1][n + 1];
        long[][] pa = new long[m][n];
        long[][] pb = new long[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                long ca = ra[i][j];
                long cb = rb[i][j];
                if (i > 0) {
                    ca += pa[i - 1][j];
                    cb += pb[i - 1][j];
                }
                if (j > 0) {
                    ca += pa[i][j - 1];
                    cb += pb[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    ca -= pa[i - 1][j - 1];
                    cb -= pb[i - 1][j - 1];
                }

                if (i + k <= m && j + k <= n) {
                    long xa = 1L - ca;
                    long xb = -a[i][j] - cb;

                    if (xa == 0L) {
                        if (xb < 0L) {
                            return -1;
                        }
                    } else if (xa == 1L) {
                        lb = Math.max(lb, -xb);
                    } else {
                        return -1;
                    }

                    add(ra, i, j, k, xa);
                    add(rb, i, j, k, xb);

                    pa[i][j] = 1L;
                    pb[i][j] = -a[i][j];
                } else {
                    pa[i][j] = ca;
                    pb[i][j] = cb;

                    long p = ca - 1L;
                    long q = cb + a[i][j];

                    if (p == 0L) {
                        if (q != 0L) {
                            return -1;
                        }
                    } else if (p == -1L) {
                        long v = q;
                        if (eq == null) {
                            eq = v;
                        } else if (eq.longValue() != v) {
                            return -1;
                        }
                    } else {
                        return -1;
                    }
                }
            }
        }

        long t;
        if (eq != null) {
            t = eq.longValue();
            if (t < lb) {
                return -1;
            }
        } else {
            t = lb;
        }

        long[][] r = new long[m + 1][n + 1];
        long[][] p = new long[m][n];
        long res = 0L;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                long cur = r[i][j];
                if (i > 0) {
                    cur += p[i - 1][j];
                }
                if (j > 0) {
                    cur += p[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    cur -= p[i - 1][j - 1];
                }

                if (i + k <= m && j + k <= n) {
                    long now = a[i][j] + cur;
                    if (now > t) {
                        return -1;
                    }
                    long x = t - now;
                    res += x;
                    add(r, i, j, k, x);
                    p[i][j] = t - a[i][j];
                } else {
                    p[i][j] = cur;
                    if (a[i][j] + cur != t) {
                        return -1;
                    }
                }
            }
        }

        return res;
    }

    private void add(long[][] d, int i, int j, int k, long v) {
        d[i][j] += v;
        d[i + k][j] -= v;
        d[i][j + k] -= v;
        d[i + k][j + k] += v;
    }
}
