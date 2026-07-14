public class NumberOfWaysToEarnPoints {
    public int waysToReachTarget(int t, int[][] a) {
        // sliding window bounded knapsack optimization based on mod
        int n = a.length;
        long[][] dp = new long[n + 1][t + 1];
        dp[0][0] = 1;
        long mod = (long) 1e9 + 7;

        for (int i = 1; i <= n; ++i) {
            int ccnt = a[i - 1][0];
            int cscore = a[i - 1][1];
            for (int r = 0; r < cscore && r <= t; ++r) {
                long sum = 0;
                for (int j = r; j <= t; j += cscore) {
                    sum += dp[i - 1][j];
                    sum %= mod;
                    int head = j - (ccnt + 1) * cscore;
                    if (head >= 0) {
                        sum -= dp[i - 1][head];
                        sum %= mod;
                        if (sum < 0) {
                            sum += mod;
                        }
                    }
                    dp[i][j] = sum;
                }
            }

        }
        return (int) dp[n][t];
    }
}
