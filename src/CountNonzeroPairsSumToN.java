import java.util.Arrays;

public class CountNonzeroPairsSumToN {
    // TODO digit dp with lots of nuances...
    int[] d;
    long[][][][][][] p;

    public long countNoZeroPairs(long n) {
        long t = n;
        int m = 0;
        while (t > 0) {
            m++;
            t /= 10;
        }
        d = new int[m];
        t = n;
        for (int i = 0; i < m; i++) {
            d[i] = (int) (t % 10);
            t /= 10;
        }

        p = new long[m + 1][2][2][2][2][2];
        for (int i = 0; i <= m; i++) {
            for (int c = 0; c < 2; c++) {
                for (int ea = 0; ea < 2; ea++) {
                    for (int eb = 0; eb < 2; eb++) {
                        for (int sa = 0; sa < 2; sa++) {
                            Arrays.fill(p[i][c][ea][eb][sa], -1L);
                        }
                    }
                }
            }
        }
        return f(0, 0, 0, 0, 0, 0, m);
    }

    private long f(int i, int c, int ea, int eb, int sa, int sb, int m) {
        if (i == m) {
            if (c == 0) {
                if (sa == 1) {
                    if (sb == 1) {
                        return 1L;
                    } else {
                        return 0L;
                    }
                } else {
                    return 0L;
                }
            } else {
                return 0L;
            }
        }
        long v = p[i][c][ea][eb][sa][sb];
        if (v != -1L) {
            return v;
        }
        long res = 0L;
        int nd = d[i];
        for (int da = 0; da <= 9; da++) {
            int nea;
            int nsa;
            if (ea == 1) {
                if (da != 0) {
                    continue;
                }
                nea = 1;
                nsa = sa;
            } else {
                if (da == 0) {
                    nea = 1;
                    nsa = sa;
                } else {
                    nea = 0;
                    nsa = 1;
                }
            }
            for (int db = 0; db <= 9; db++) {
                int neb;
                int nsb;
                if (eb == 1) {
                    if (db != 0) {
                        continue;
                    }
                    neb = 1;
                    nsb = sb;
                } else {
                    if (db == 0) {
                        neb = 1;
                        nsb = sb;
                    } else {
                        neb = 0;
                        nsb = 1;
                    }
                }
                int s = da + db + c;
                if ((s % 10) != nd) {
                    continue;
                }
                int nc = s / 10;
                res += f(i + 1, nc, nea, neb, nsa, nsb, m);
            }
        }
        p[i][c][ea][eb][sa][sb] = res;
        return res;
    }
}
