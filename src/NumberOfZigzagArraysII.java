public class NumberOfZigzagArraysII {
    // TODO matrix multi...
    static final long M = 1000000007L;

    public int zigZagArrays(int n, int l, int r) {

        int L = l, R = r, m = R - L + 1, sz = 2 * m;
        long[][] T = new long[sz][sz];
        for (int y = L; y <= R; ++y) {
            int yu = y - L;
            int yd = m + (y - L);
            for (int z = L; z < y; ++z) {
                int zd = m + (z - L);
                T[zd][yu] = (T[zd][yu] + 1) % M;
            }
            for (int z = y + 1; z <= R; ++z) {
                int zu = z - L;
                T[zu][yd] = (T[zu][yd] + 1) % M;
            }
        }
        long[] v = new long[sz];
        for (int y = L; y <= R; ++y) {
            int u = y - L, d = m + (y - L);
            v[u] = (y - L) % M;
            v[d] = (R - y) % M;
        }
        if (n == 2) {
            long s = 0;
            for (int i = 0; i < sz; ++i) {
                s += v[i];
                if (s >= M) s -= M;
            }
            return (int) (s % M);
        }
        long[][] P = mpow(T, n - 2);
        long[] res = mul(P, v);
        long ans = 0;
        for (int i = 0; i < sz; ++i) {
            ans += res[i];
            ans %= M;
        }
        return (int) ans;
    }

    long[] mul(long[][] A, long[] x) {
        int n = A.length, m = x.length;
        long[] y = new long[n];
        for (int i = 0; i < n; ++i) {
            long s = 0;
            long[] Ai = A[i];
            for (int j = 0; j < m; ++j)
                if (Ai[j] != 0) {
                    s += Ai[j] * x[j] % M;
                    if (s >= (1L << 61)) s %= M;
                }
            y[i] = s % M;
        }
        return y;
    }

    long[][] mmul(long[][] A, long[][] B) {
        int n = A.length, k = B.length, m = B[0].length;
        long[][] C = new long[n][m];
        for (int i = 0; i < n; ++i) {
            long[] Ai = A[i], Ci = C[i];
            for (int t = 0; t < k; ++t) {
                long a = Ai[t];
                if (a == 0) continue;
                long[] Bt = B[t];
                for (int j = 0; j < m; ++j)
                    if (Bt[j] != 0) {
                        Ci[j] = (Ci[j] + a * Bt[j]) % M;
                    }
            }
        }
        return C;
    }

    long[][] mpow(long[][] A, long e) {
        int n = A.length;
        long[][] R = new long[n][n];
        for (int i = 0; i < n; ++i) R[i][i] = 1;
        long[][] B = A;
        while (e > 0) {
            if ((e & 1) == 1) R = mmul(R, B);
            B = mmul(B, B);
            e >>= 1;
        }
        return R;
    }
}
