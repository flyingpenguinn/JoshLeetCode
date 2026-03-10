public class FindAllPossbileStableArraysII {

    int MOD = 1_000_000_007;

    public int numberOfStableArrays(int n, int m, int k) {
        // ways using exactly n zeros, m ones, and ending in 0 or 1
        long[][][] dp = new long[n + 1][m + 1][2];
        for (int i = 0; i < n; ++i) {
            dp[i][0][0] = 1;
        }
        for (int j = 0; j < m; ++j) {
            dp[0][j][1] = 1;
        }
        long[][] sum0Col = new long[n + 1][m + 1];
        long[][] sum1Row = new long[n + 1][m + 1];
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                if (i == 0) {
                    dp[i][j][0] = 0;
                } else if (j == 0) {
                    dp[i][j][0] = (i >= 1 && i <= k) ? 1 : 0;
                } else {
                    dp[i][j][0] = sum1Row[i - 1][j] - (i - k - 1 < 0 ? 0 : sum1Row[i - k - 1][j]);
                }
                dp[i][j][0] %= MOD;
                if (dp[i][j][0] < 0) {
                    dp[i][j][0] += MOD;
                }
                if (i == 0) {
                    dp[i][j][1] = (j >= 1 && j <= k) ? 1 : 0;
                } else if (j == 0) {
                    dp[i][j][1] = 0;
                } else {
                    dp[i][j][1] = sum0Col[i][j - 1] - (j - k - 1 < 0 ? 0 : sum0Col[i][j - k - 1]);
                }
                dp[i][j][1] %= MOD;
                if (dp[i][j][1] < 0) {
                    dp[i][j][1] += MOD;
                }
                sum0Col[i][j] = (j == 0 ? 0 : sum0Col[i][j - 1]) + dp[i][j][0];
                sum0Col[i][j] %= MOD;
                sum1Row[i][j] = (i == 0 ? 0 : sum1Row[i - 1][j]) + dp[i][j][1];
                sum1Row[i][j] %= MOD;
            }
        }
        long res = dp[n][m][0] + dp[n][m][1];
        res %= MOD;
        return (int) res;
    }

    static void main() {
        System.out.println(new FindAllPossbileStableArraysII().numberOfStableArrays(1, 1, 2));
        System.out.println(new FindAllPossbileStableArraysII().numberOfStableArrays(1, 2, 1));
    }
}
