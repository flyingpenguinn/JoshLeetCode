public class MaxScoreFromPerformingMultipleOperations {
    // k== i+j! so we dont need another dimension, 2-d dp
    private Integer[][] dp;

    public int maximumScore(int[] a, int[] m) {
        dp = new Integer[m.length][m.length];
        return dom(a, 0, 0, m);
    }

    private int dom(int[] a, int i, int j, int[] m) {

        int rj = a.length - 1 - j;
        int k = i + j;
        if (k == m.length) {
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int way1 = a[i] * m[k] + dom(a, i + 1, j, m);
        int way2 = a[rj] * m[k] + dom(a, i, j + 1, m);
        int rt = Math.max(way1, way2);
        dp[i][j] = rt;
        return rt;
    }
}


class MaxScoreFromPerforming2 {
    // alternatively, dp using classic space saving technique and note i can never be bigger than k!
    public int maximumScore(int[] a, int[] b) {
        int[][] dp = new int[a.length + 1][2];
        for (int k = b.length - 1; k >= 0; --k) {
            for (int i = Math.min(a.length - 1, k); i >= 0; --i) {
                int j = a.length - 1 - (k - i);
                int way1 = a[i] * b[k] + dp[i + 1][(k + 1) % 2];
                int way2 = a[j] * b[k] + dp[i][(k + 1) % 2];
                dp[i][(k % 2)] = Math.max(way1, way2);
            }
        }
        return dp[0][0];
    }
}