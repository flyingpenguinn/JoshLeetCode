public class MaxNonNegativeProductInMatrix {
    private int mod = 1000000007;

    public int maxProductPath(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        long[][][] dp = new long[m][n][2];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                long curmax = 0;
                long curmin = 0;
                if (i == m - 1 && j == n - 1) {
                    curmax = a[i][j];
                    curmin = a[i][j];
                } else if (i == m - 1) {
                    curmax = Math.max(a[i][j] * dp[i][j + 1][0], a[i][j] * dp[i][j + 1][1]);
                    curmin = Math.min(a[i][j] * dp[i][j + 1][0], a[i][j] * dp[i][j + 1][1]);
                } else if (j == n - 1) {
                    curmax = Math.max(a[i][j] * dp[i + 1][j][0], a[i][j] * dp[i + 1][j][1]);
                    curmin = Math.min(a[i][j] * dp[i + 1][j][0], a[i][j] * dp[i + 1][j][1]);
                } else {
                    long max1 = Math.max(a[i][j] * dp[i][j + 1][0], a[i][j] * dp[i][j + 1][1]);
                    long max2 = Math.max(a[i][j] * dp[i + 1][j][0], a[i][j] * dp[i + 1][j][1]);
                    curmax = Math.max(max1, max2);
                    long min1 = Math.min(a[i][j] * dp[i][j + 1][0], a[i][j] * dp[i][j + 1][1]);
                    long min2 = Math.min(a[i][j] * dp[i + 1][j][0], a[i][j] * dp[i + 1][j][1]);
                    curmin = Math.min(min1, min2);
                }
                dp[i][j][0] = curmax;
                dp[i][j][1] = curmin;
            }
        }
        if (dp[0][0][0] >= 0) {
            return (int) (dp[0][0][0] % mod);
        } else {
            return -1;
        }
    }
}
