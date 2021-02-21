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
