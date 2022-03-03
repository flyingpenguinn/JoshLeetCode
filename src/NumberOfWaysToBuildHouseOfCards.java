public class NumberOfWaysToBuildHouseOfCards {
    private Integer[][] dp;

    public int houseOfCards(int n) {
        dp = new Integer[n + 1][n + 1];
        return solve(n, 0);
    }

    private int solve(int n, int start) {
        if (n < 2) {
            return 0;
        }
        if (dp[n][start] != null) {
            return dp[n][start];
        }
        int res = n % 3 == 2 ? 1 : 0;
        for (int i = start + 1; i < n; ++i) {
            if (i % 3 == 2 && n - i > i) {
                res += solve(n - i, i);
            }
        }
        dp[n][start] = res;
        return res;
    }
}
