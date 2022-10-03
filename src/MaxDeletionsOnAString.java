import java.util.Arrays;

public class MaxDeletionsOnAString {
    private int[][] lcs;
    private int[] dp;

    public int deleteString(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        lcs = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (sc[i] == sc[j]) {
                    lcs[i][j] = (i == n - 1 || j == n - 1 ? 0 : lcs[i + 1][j + 1]) + 1;
                }
            }
        }
        dp = new int[n];
        Arrays.fill(dp, -1);

        return solve(s, 0);
    }

    private int solve(String s, int i) {
        int n = s.length();
        int res = 1;
        if (dp[i] != -1) {
            return dp[i];
        }
        for (int len = 1; i + 2 * len - 1 < n; ++len) {
            int j = i + len;
            if (lcs[i][j] >= len) {
                int cur = 1 + solve(s, i + len);
                res = Math.max(res, cur);
            }
        }
        dp[i] = res;
        return res;
    }
}
