import java.util.Arrays;

public class LongestPalinSubseqAfterKOperations {
    public int longestPalindromicSubsequence(String s, int k) {
        char[] c = s.toCharArray();
        int n = c.length;
        dp = new int[n][n][k + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return solve(c, 0, n - 1, k);
    }

    private int[][][] dp;
    private int Min = (int) -1e9;

    private int solve(char[] c, int i, int j, int k) {
        if (k < 0) {
            return Min;
        }
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }
        if (dp[i][j][k] != -1) {
            return dp[i][j][k];
        }
        int ci = c[i] - 'a';
        int cj = c[j] - 'a';
        int bigger = Math.max(ci, cj);
        int smaller = Math.min(ci, cj);
        int dist1 = bigger - smaller;
        int dist2 = 26 - bigger + smaller;
        int dist = Math.min(dist1, dist2);

        int res = 0;
        if (ci == cj) {
            res = 2 + solve(c, i + 1, j - 1, k);
        } else {
            int way1 = 0;
            if (k >= dist) {
                way1 = 2 + solve(c, i + 1, j - 1, k - dist);
            }
            int way2 = solve(c, i + 1, j, k);
            int way3 = solve(c, i, j - 1, k);
            res = Math.max(way1, Math.max(way2, way3));
        }
        dp[i][j][k] = res;
        return res;
    }
}
