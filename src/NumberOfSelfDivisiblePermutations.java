public class NumberOfSelfDivisiblePermutations {
    private Integer[][] dp;

    public int selfDivisiblePermutationCount(int n) {
        dp = new Integer[n][(1 << (n + 1))];
        return solve(0, n, 0);
    }

    private int solve(int i, int n, int st) {
        if (i == n) {
            return 1;
        }
        if (dp[i][st] != null) {
            return dp[i][st];
        }
        int res = 0;
        for (int j = 1; j <= n; ++j) {
            if (((st >> j) & 1) == 0) {
                if (j % (i + 1) == 0 || (i + 1) % j == 0) {
                    int nst = st | (1 << j);
                    int cur = solve(i + 1, n, nst);
                    res += cur;
                }
            }
        }
        dp[i][st] = res;
        return res;
    }
}
