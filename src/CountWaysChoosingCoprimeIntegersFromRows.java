import java.util.Arrays;

public class CountWaysChoosingCoprimeIntegersFromRows {
    public int countCoprime(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        long res = 0;
        dp = new long[m][160];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dp[i], -1);
        }
        for (int j = 0; j < n; ++j) {
            res += solve(a, 1, a[0][j]);
            res %= Mod;
        }
        return (int) res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long[][] dp;
    private final long Mod = (long) 1e9 + 7;

    private long solve(int[][] a, int i, int cop) {
        int m = a.length;
        int n = a[0].length;
        if (i == m) {
            return cop == 1 ? 1 : 0;
        }
        if (dp[i][cop] != -1) {
            return dp[i][cop];
        }
        long res = 0;
        for (int j = 0; j < n; ++j) {
            final int b = a[i][j];
            int ncop = gcd(cop, b);
            long later = solve(a, i + 1, ncop);
            res += later;
            res %= Mod;
        }
        dp[i][cop] = res;
        return res;
    }
}
