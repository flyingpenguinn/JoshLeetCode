public class CountSortedVowelStrings {
    private Integer[][] dp;

    public int countVowelStrings(int n) {
        dp = new Integer[n][6];
        return dfs(0, 0, n);

    }

    private int dfs(int i, int j, int n) {
        if (i == n) {
            return 1;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int res = 0;
        for (int k = 1; k <= 5; k++) {
            if (k >= j) {
                res += dfs(i + 1, k, n);
            }
        }
        dp[i][j] = res;
        return res;
    }
}
