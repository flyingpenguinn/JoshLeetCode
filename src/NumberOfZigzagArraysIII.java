public class NumberOfZigzagArraysIII {
    // TODO lagrange????
    private static final long MOD = 1000000007L;

    public int zigZagArrays(int n, int l, int r) {
        long m = (long) r - l + 1;

        // y[k] = answer when value range size is k
        long[] y = new long[n + 1];

        for (int k = 0; k <= n; k++) {
            y[k] = countSmallRange(n, k);
        }

        if (m <= n) {
            return (int) y[(int) m];
        }

        return (int) lagrange(y, m % MOD);
    }

    // Normal DP for small alphabet size m.
    // Values are 1..m.
    private long countSmallRange(int n, int m) {
        if (m <= 1) return 0;

        long[] up = new long[m + 2];
        long[] down = new long[m + 2];

        // length = 2
        // up[x]   = number of pairs ending at x with previous < x
        // down[x] = number of pairs ending at x with previous > x
        for (int x = 1; x <= m; x++) {
            up[x] = x - 1;
            down[x] = m - x;
        }

        if (n == 2) {
            return (long) m * (m - 1) % MOD;
        }

        for (int len = 3; len <= n; len++) {
            long[] prefUp = new long[m + 2];
            long[] prefDown = new long[m + 2];

            for (int x = 1; x <= m; x++) {
                prefUp[x] = (prefUp[x - 1] + up[x]) % MOD;
                prefDown[x] = (prefDown[x - 1] + down[x]) % MOD;
            }

            long[] newUp = new long[m + 2];
            long[] newDown = new long[m + 2];

            for (int x = 1; x <= m; x++) {
                // last move becomes UP, so previous move must be DOWN
                // previous value must be < x
                newUp[x] = prefDown[x - 1];

                // last move becomes DOWN, so previous move must be UP
                // previous value must be > x
                newDown[x] = (prefUp[m] - prefUp[x] + MOD) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;
        for (int x = 1; x <= m; x++) {
            ans = (ans + up[x] + down[x]) % MOD;
        }

        return ans;
    }

    // Given y[0], y[1], ..., y[n], evaluate polynomial at x.
    private long lagrange(long[] y, long x) {
        int n = y.length - 1;
        long ans = 0;

        for (int i = 0; i <= n; i++) {
            long num = 1;
            long den = 1;

            for (int j = 0; j <= n; j++) {
                if (i == j) continue;

                num = num * ((x - j + MOD) % MOD) % MOD;
                den = den * ((i - j + MOD) % MOD) % MOD;
            }

            long term = y[i] * num % MOD * modInverse(den) % MOD;
            ans = (ans + term) % MOD;
        }

        return ans;
    }

    private long modInverse(long x) {
        return modPow(x, MOD - 2);
    }

    private long modPow(long a, long e) {
        long res = 1;
        while (e > 0) {
            if ((e & 1) == 1) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
            e >>= 1;
        }
        return res;

    }
}
