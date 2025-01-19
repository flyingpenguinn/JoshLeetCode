public class ManhattanDistanceOfAllArrangementOfPieces {
    // TODO hard
    static final long MOD = 1000000007;

    // Precomputed factorials and inverses
    static long[] fact;
    static long[] invFact;

    // Precompute factorials up to nMax
    private static void precomputeFactorials(int nMax) {
        if (fact != null && fact.length > nMax) {
            // already big enough
            return;
        }
        fact = new long[nMax + 1];
        invFact = new long[nMax + 1];

        fact[0] = 1L;
        for (int i = 1; i <= nMax; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        // Fermat inverse of fact[nMax] (since MOD is prime)
        invFact[nMax] = modPow(fact[nMax], MOD - 2, MOD);
        for (int i = nMax - 1; i >= 0; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
    }

    // nCk mod
    private static long choose(int n, int k) {
        if (k < 0 || k > n) return 0;
        return fact[n] * ((invFact[k] * invFact[n - k]) % MOD) % MOD;
    }

    // fast exponentiation
    private static long modPow(long base, long exp, long m) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % m;
            base = (base * base) % m;
            exp >>= 1;
        }
        return res;
    }

    // sum_{d=1..X-1} d*(X-d)  in O(1) closed form
    private static long sumDistOneDim(long X) {
        // sum_{d=1..X-1} [ X*d - d^2 ]
        // = X * (1+2+...+(X-1)) - (1^2 + 2^2 + ... + (X-1)^2)
        // = X*( (X-1)*X/2 ) - ( (X-1)*X*(2X-1)/6 )
        // We'll do it mod safely.
        if (X < 2) return 0;
        long x1 = X - 1;
        long s1 = x1 * X / 2;  // sum_{1..X-1} d
        long s2 = x1 * X * (2 * X - 1) / 6;  // sum_{1..X-1} d^2
        return X * s1 - s2;
    }

    public int distanceSum(int m, int n, int k) {
        // M = total cells
        int M = m * n;

        // Edge case: k<2 => problem states k>=2
        // Precompute factorials up to M
        precomputeFactorials(M);

        // 1) sumDist = rowPart + colPart
        // rowPart = sum_{d=1..m-1} d*(m-d)* n^2
        // colPart = sum_{d=1..n-1} d*(n-d)* m^2
        long fm = sumDistOneDim(m) % MOD;  // sum_{d=1..m-1} d*(m-d)
        long fn = sumDistOneDim(n) % MOD;  // sum_{d=1..n-1} d*(n-d)

        long rowPart = fm % MOD;
        rowPart = (rowPart * ((long) n * n % MOD)) % MOD;

        long colPart = fn % MOD;
        colPart = (colPart * ((long) m * m % MOD)) % MOD;

        long sumDist = (rowPart + colPart) % MOD;

        // 2) ways = C(M-2, k-2)
        long ways = choose(M - 2, k - 2);

        // Final answer
        long ans = (sumDist % MOD) * (ways % MOD) % MOD;
        return (int) ans;
    }
}


