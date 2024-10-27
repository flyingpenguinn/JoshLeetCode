import java.util.Arrays;

public class FindNumberOfSubseqWithEqualGcd {
    private long Mod = (long) (1e9 + 7);
    private long[][][] dp;

    public int subsequencePairCount(int[] a) {
        int n = a.length;
        dp = new long[n][201][201];
        for (int i = 0; i < dp.length; ++i) {
            for (int j = 0; j < dp[0].length; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        long rt = solve(a, 0, 0, 0);
        return (int) rt;
    }

    private long solve(int[] a, int i, int g1, int g2) {
        int n = a.length;
        if (i == n) {
            if (g1 > 0 && g2 > 0 && g1 == g2) {
                return 1L;
            } else {
                return 0;
            }
        }
        if (dp[i][g1][g2] != -1) {
            return dp[i][g1][g2];
        }
        long way1 = solve(a, i + 1, g1, g2);
        long way2 = solve(a, i + 1, gcd(g1, a[i]), g2);
        long way3 = solve(a, i + 1, g1, gcd(g2, a[i]));
        long res = way1 + way2 + way3;
        res %= Mod;
        dp[i][g1][g2] = res;
        return res;
    }

    private int gcd(int a, int b) {
        if (b > a) {
            return gcd(b, a);
        }
        return b == 0 ? a : gcd(b, a % b);
    }
}
