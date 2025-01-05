public class LongestSubsequenceWithDecAdjDiff {
    // dp on value itself
    public int longestSubsequence(int[] a) {
        int n = a.length;
        int limit = 301;
        int[][] dp = new int[limit][limit];
        for (int i = n - 1; i >= 0; --i) {
            int v = a[i];
            for (int j = 1; j < limit; ++j) {
                int abs = Math.abs(j - v);
                dp[v][abs] = Math.max(dp[v][abs], dp[j][abs] + 1);
            }
            for (int j = 1; j < limit; ++j) {
                dp[v][j] = Math.max(dp[v][j - 1], dp[v][j]);
            }
        }
        int res = 0;
        for (int i = 1; i < limit; ++i) {
            for (int j = 1; j < limit; ++j) {
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
