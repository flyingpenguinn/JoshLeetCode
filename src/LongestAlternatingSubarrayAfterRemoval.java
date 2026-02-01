public class LongestAlternatingSubarrayAfterRemoval {
    private int Min = (int) (-1e9);

    public int longestAlternating(int[] a) {
        int n = a.length;
        int[][][] dp = new int[n][2][2];
        // 0 > 1 <
        for (int i = 0; i < n; ++i) {
            dp[i][0][0] = 1;
            dp[i][0][1] = 1;
            dp[i][1][0] = Min;
            dp[i][1][1] = Min;
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= 1; ++j) {
                if (j == 1) {
                    if (i - 1 >= 0 && a[i] > a[i - 1]) {
                        dp[i][1][0] = Math.max(dp[i][1][0], dp[i - 1][1][1] + 1);
                    } else if (i - 1 >= 0 && a[i] < a[i - 1]) {
                        dp[i][1][1] = Math.max(dp[i][1][1], dp[i - 1][1][0] + 1);
                    }
                } else {
                    if (i - 1 >= 0 && a[i] > a[i - 1]) {
                        dp[i][0][0] = Math.max(dp[i][0][0], dp[i - 1][0][1] + 1);
                    } else if (i - 1 >= 0 && a[i] < a[i - 1]) {
                        dp[i][0][1] = Math.max(dp[i][0][1], dp[i - 1][0][0] + 1);
                    }
                    if (i - 2 >= 0 && a[i] > a[i - 2]) {
                        dp[i][1][0] = Math.max(dp[i][1][0], dp[i - 2][0][1] + 1);
                    } else if (i - 2 >= 0 && a[i] < a[i - 2]) {
                        dp[i][1][1] = Math.max(dp[i][1][1], dp[i - 2][0][0] + 1);
                    }
                }
            }
            res = Math.max(res, dp[i][0][0]);
            res = Math.max(res, dp[i][0][1]);
            res = Math.max(res, dp[i][1][0]);
            res = Math.max(res, dp[i][1][1]);
        }
        return res;
    }
}
