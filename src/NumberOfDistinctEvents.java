public class NumberOfDistinctEvents {
    // n balls into x boxes where no box is empty; x! * sterling of 2nd kind
    long[][] s;
    long[][] c;
    long[] powy;
    long[] fact;
    long mod = (long) (1e9 + 7);

    public int numberOfWays(int n, int x, int y) {
        s = new long[n + 1][x + 1];
        c = new long[x + 1][x + 1];
        fact = new long[x+ 1];
        fact[0] = 1;
        for (long i = 1; i <= x; ++i) {
            fact[(int) i] = fact[(int) (i - 1)] * i;
            fact[(int) i] %= mod;
        }
        s[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            s[n][0] = 0;
            s[n][1] = 1;
        }
        for (int i = 0; i <= x; ++i) {
            s[0][x] = 0;
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= x; ++j) {
                s[i][j] = 1L * j * s[i - 1][j] + s[i - 1][j - 1];
                s[i][j] %= mod;
            }
        }
        for (int i = 0; i <= x; i++) {
            c[i][0] = 1;
        }
        //combi(0,i)==0
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= x; j++) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
        powy = new long[x + 1];
        powy[0] = 1;
        for (int i = 1; i <= x; ++i) {
            long half = powy[i / 2];
            long times = half * half;
            times %= mod;
            if (i % 2 == 1) {
                times *= y;
                times %= mod;
            }
            powy[i] = times;
        }
        long res = 0;
        for (int stages = x; stages >= 1; --stages) {
            long p1 = c[x][stages];
            long p2 = fact[stages] * s[n][stages];
            p2 %= mod;
            long cur = p1 * p2;
            long p3 = powy[stages];
            cur %= mod;
            cur *= p3;
            cur %= mod;
            res += cur;
            res %= mod;
        }
        return (int) res;
    }
}
