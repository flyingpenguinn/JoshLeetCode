public class FindSumOfArrayProductOfMagicalSequences {
    // TODO do it myself
    static final int MOD = 1_000_000_007;

    public int magicalSum(int m, int k, int[] nums) {
        int n = nums.length;

        // 1) Precompute factorials & inv-factorials up to m
        long[] fact = new long[m + 1], invFact = new long[m + 1];
        fact[0] = 1;
        for (int i = 1; i <= m; i++) fact[i] = fact[i - 1] * i % MOD;
        invFact[m] = modPow(fact[m], MOD - 2);
        for (int i = m; i >= 1; i--) invFact[i - 1] = invFact[i] * i % MOD;

        // 2) Precompute powNum[i][c] = nums[i]^c mod
        int[][] powNum = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            powNum[i][0] = 1;
            for (int c = 1; c <= m; c++) {
                powNum[i][c] = (int) ((long) powNum[i][c - 1] * nums[i] % MOD);
            }
        }

        // 3) dp[i][used][carry][ones] = sum of weights for choices c_0..c_{i-1}
        //    i in [0..n], used in [0..m], carry in [0..m], ones in [0..k]
        // We'll use a 4D array; ~50*31*31*31 ~1.5M ints => ~6MB
        int[][][][] dp = new int[n + 1][m + 1][m + 1][k + 1];
        dp[0][0][0][0] = 1;

        // 4) fill DP
        for (int i = 0; i < n; i++) {
            for (int used = 0; used <= m; used++) {
                for (int carry = 0; carry <= m; carry++) {
                    for (int ones = 0; ones <= k; ones++) {
                        int base = dp[i][used][carry][ones];
                        if (base == 0) continue;
                        // try c_i = 0..(m - used)
                        for (int c = 0; c + used <= m; c++) {
                            int u2 = used + c;
                            int sum = c + carry;
                            int bit = sum & 1;
                            int o2 = ones + bit;
                            if (o2 > k) break;        // further c only increases used
                            int carry2 = sum >>> 1;
                            // weight = invFact[c] * nums[i]^c
                            long w = base;
                            w = w * invFact[c] % MOD;
                            w = w * powNum[i][c] % MOD;
                            dp[i + 1][u2][carry2][o2] =
                                    (int) ((dp[i + 1][u2][carry2][o2] + w) % MOD);
                        }
                    }
                }
            }
        }

        // 5) collect answer: we need used=m and final popcount = k.
        //    The leftover carry at bit n and above contributes popcount(carry).
        long ans = 0;
        for (int carry = 0; carry <= m; carry++) {
            int extra = Integer.bitCount(carry);
            for (int ones = 0; ones <= k; ones++) {
                if (ones + extra == k) {
                    int ways = dp[n][m][carry][ones];
                    if (ways != 0) {
                        // multiply back the m! factor
                        ans = (ans + (long) ways * fact[m]) % MOD;
                    }
                }
            }
        }
        return (int) ans;
    }

    // fast exponentiation mod
    private long modPow(long x, long e) {
        long res = 1, m = MOD;
        while (e > 0) {
            if ((e & 1) == 1) res = (res * x) % m;
            x = (x * x) % m;
            e >>= 1;
        }
        return res;
    }
}
