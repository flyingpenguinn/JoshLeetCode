public class MaxXorScoreSubarrayQuery {
    public int[] maximumSubarrayXor(int[] a, int[][] qs) {
        int n = a.length;

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = a[i];
        }

        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                dp[i][i + len] = dp[i][i + len - 1] ^ dp[i + 1][i + len];
            }
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i + j < n; i++) {
                dp[i][i + j] = Math.max(dp[i][i + j],
                        Math.max(dp[i][i + j - 1], dp[i + 1][i + j]));
            }
        }

        int[] res = new int[qs.length];
        int idx = 0;
        for (int[] q : qs) {
            res[idx++] = dp[q[0]][q[1]];
        }
        return res;
    }
}
