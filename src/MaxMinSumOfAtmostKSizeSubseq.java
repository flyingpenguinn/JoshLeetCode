import java.util.Arrays;

public class MaxMinSumOfAtmostKSizeSubseq {
    // precalc combination sums to avoid TLE
    private long res = 0;

    private static long mod = (long) (1e9 + 7);
    private static long[] fact;
    private static long[][] combi;
    private static long[][] combisum;


    private static void init() {
        if (fact == null) {
            fact = new long[100001];
            fact[0] = 1;
            for (int i = 1; i < fact.length; ++i) {
                fact[i] = fact[i - 1] * i;
                fact[i] %= mod;
            }
        }
        if (combi == null) {
            combi = new long[100001][101];
            combisum = new long[100001][101];
            for (int i = 0; i < combi.length; ++i) {
                combi[i][0] = 1;
            }
            for (int i = 1; i < combi.length; ++i) {
                for (int k = 1; k < combi[0].length; ++k) {
                    if (i < k) {
                        combi[i][k] = 0;
                    } else {
                        combi[i][k] = combi[i - 1][k - 1] + combi[i - 1][k];
                    }
                    combi[i][k] %= mod;
                }
            }
            for (int i = 1; i < combi.length; ++i) {
                for (int k = 0; k < combi[0].length; ++k) {
                    combisum[i][k] = (k == 0 ? 0 : combisum[i][k - 1]) + combi[i][k];
                    combisum[i][k] %= mod;
                }
            }
        }
    }

    private long fact(int n) {
        return fact[n];
    }

    public int minMaxSums(int[] a, int k) {
        res = 0;
        int n = a.length;
        init();
        Arrays.sort(a);

        for (int i = 0; i < n; ++i) {
            long before = i + 1;
            calc(a, k, i, before);
            long after = n - i;
            calc(a, k, i, after);
        }
        return (int) res;
    }

    protected void calc(int[] a, long k, int i, long count) {
        if (count <= k) {
            long pow2 = pow(2, count - 1);
            long cmax = pow2 * a[i];
            cmax %= mod;
            res += cmax;
            res %= mod;
        } else {
            long ways = 0;
            ways += combisum[(int) (count - 1)][(int) (k - 1)];

            long cmax = ways * a[i];
            cmax %= mod;
            res += cmax;
            res %= mod;
        }
    }

    private long pow(int a, long b) {
        if (b == 0) {
            return 1L;
        }
        long half = pow(a, b / 2);
        long res = half * half;
        res %= mod;
        if (b % 2 == 1) {
            res *= a;
            res %= mod;
        }
        return res;
    }
}
