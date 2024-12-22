public class CountPathsWtihGivenXor {
    public int countPathsWithXorValue(int[][] a, int t) {
        int m = a.length;
        int n = a[0].length;
        int limit = 16;
        long[][][] dp = new long[m][n][limit];
        long mod = (long) (1e9 + 7);
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                int v = a[i][j];
                if (i == m - 1 && j == n - 1) {
                    dp[i][j][v] = 1;
                } else {
                    for (int k = 0; k < limit; ++k) {
                        int kv = k ^ v;
                        dp[i][j][kv] += (i == m - 1 ? 0 : dp[i + 1][j][k]);
                        dp[i][j][kv] += (j == n - 1 ? 0 : dp[i][j + 1][k]);
                        dp[i][j][kv] %= mod;
                    }
                }
            }
        }

        return (int) dp[0][0][t];
    }
}
