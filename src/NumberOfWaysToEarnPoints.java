public class NumberOfWaysToEarnPoints {
    private long mod = (long) (1e9 + 7);

    public int waysToReachTarget(int t, int[][] a) {
        int n = a.length;
        // up to i, score t
        long[][] dp = new long[n + 1][t + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; ++i) {
            int cnt = a[i - 1][0];
            int score = a[i - 1][1];
            for (int r = 0; r < score; ++r) {
                long modsum = 0;
                for (int j = r; j <= t; j += score) {
                    modsum += dp[i - 1][j];
                    int head = j - (cnt + 1) * score;
                    if (head >= 0) {
                        modsum -= dp[i - 1][head];
                    }
                    modsum %= mod;
                    if (modsum < 0) {
                        modsum += mod;
                    }
                    dp[i][j] = modsum;

                }
            }
        }
        return (int) dp[n][t];
    }
}
