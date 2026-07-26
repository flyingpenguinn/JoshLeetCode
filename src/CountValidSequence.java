public class CountValidSequence {
    // java fast combi template
    private static final long mod = (long) (1e9 + 7);

    public int countValidSequences(int n, int k) {
        long[] fact = new long[n + 1];
        long[] invFact = new long[n + 1];

        fact[0] = 1;
        for (int i = 1; i <= n; ++i) {
            fact[i] = fact[i - 1] * i % mod;
        }

        invFact[n] = pow(fact[n], mod - 2);
        for (int i = n; i >= 1; --i) {
            invFact[i - 1] = invFact[i] * i % mod;
        }

        long all = comb(n - 1, k - 1, fact, invFact);
        long odd = 0;

        if ((n - k) % 2 == 0) {
            odd = comb((n + k - 2) / 2, k - 1, fact, invFact);
        }

        return (int) ((all - odd + mod) % mod);
    }

    private long comb(int n, int r, long[] fact, long[] invFact) {
        if (r < 0 || r > n) {
            return 0;
        }

        return fact[n] * invFact[r] % mod * invFact[n - r] % mod;
    }

    private long pow(long a, long b) {
        long res = 1;

        while (b > 0) {
            if ((b & 1) != 0) {
                res = res * a % mod;
            }

            a = a * a % mod;
            b >>= 1;
        }

        return res;
    }

}
