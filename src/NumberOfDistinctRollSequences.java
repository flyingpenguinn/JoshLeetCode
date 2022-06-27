public class NumberOfDistinctRollSequences {
    private Long[][][] dp;
    private long mod = (long) (1e9 + 7);

    public int distinctSequences(int n) {
        dp = new Long[n][7][7];
        return (int) solve(0, 0, 0, n);
    }

    private long solve(int i, int p1, int p2, int n) {
        if (i == n) {
            return 1L;
        }
        if (dp[i][p1][p2] != null) {
            return dp[i][p1][p2];
        }
        long res = 0;
        for (int j = 1; j <= 6; ++j) {
            boolean rule1 = (p1 == 0 || gcd(j, p1) == 1);
            boolean rule2 = (p1 != j && p2 != j);
            if (rule1 && rule2) {
                res += solve(i + 1, j, p1, n);
                res %= mod;
            }
        }
        dp[i][p1][p2] = res;
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
