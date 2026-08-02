public class CountInfectciousSequences {
    private static long[] fact;
    private static long[] invFact;
    private static long mod = (long) (1e9 + 7);

    public int numberOfSequence(int n, int[] sick) {
        getFacts(n + 1);
        int sn = sick.length;
        long times = 1;
        long all = fact[n - sick.length];
        for (int i = 1; i < sn; ++i) {
            int gap = sick[i] - 1 - sick[i - 1];
            times *= pow(2, gap - 1);
            all *= invFact[gap];
        }
        int gap0 = sick[0];
        all *= invFact[gap0];
        int gapn = n - 1 - sick[sn - 1];
        all *= invFact[gapn];
        long res = all * times;
        res %= mod;
        return (int) res;
    }


    private static void getFacts(int n) {
        if (fact == null) {
            fact = new long[n];
            invFact = new long[n];
        } else {
            return;
        }
        fact[0] = 1;
        for (int i = 1; i < n; ++i) {
            fact[i] = fact[i - 1] * i % mod;
        }

        invFact[n - 1] = pow(fact[n - 1], mod - 2);
        for (int i = n - 1; i >= 1; --i) {
            invFact[i - 1] = invFact[i] * i % mod;
        }

    }


    private static long comb(int n, int r) {
        if (r < 0 || r > n) {
            return 0;
        }

        return fact[n] * invFact[r] % mod * invFact[n - r] % mod;
    }

    private static long pow(long a, long b) {
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

