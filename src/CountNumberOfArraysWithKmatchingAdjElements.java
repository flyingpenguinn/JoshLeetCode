public class CountNumberOfArraysWithKmatchingAdjElements {
    /*
    one liner in python fgs
    M =10**9+7

    class Solution:

    def countGoodArrays(self, n:int, m:int, k:int) ->int:
            return m *

    pow(m -1, n -k-1, M) *

    comb(n -1, k) %M

     */

    // combinations template
    private static final int mod = (int) (1e9 + 7);

    public int countGoodArrays(int n, int m, int k) {
        long res = m;
        res = (res * modPow(m - 1, n - k - 1, mod)) % mod;
        res = (res * comb(n - 1, k)) % mod;
        return (int) res;
    }

    private long modPow(long base, int exp, int mod) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    // combinations
    private long comb(int n, int k) {
        if (k > n) return 0;
        long numerator = 1;
        long denominator = 1;

        for (int i = 0; i < k; i++) {
            numerator = (numerator * (n - i)) % mod;
            denominator = (denominator * (i + 1)) % mod;
        }

        return (numerator * modInverse(denominator, mod)) % mod;
    }

    private long modInverse(long a, int mod) {
        return modPow(a, mod - 2, mod);
    }


}
