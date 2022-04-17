public class MaxTrailingZerosInCorneredPath {
    public int maxTrailingZeros(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][][][] dp = new int[m][n][4][2];

        for (int j = 0; j < n; ++j) {
            int twos = 0;
            int fives = 0;
            for (int i = 0; i < m; ++i) {
                if (a[i][j] % 2 == 0) {
                    twos += drill(a[i][j], 2);
                }
                if (a[i][j] % 5 == 0) {
                    fives += drill(a[i][j], 5);
                }
                dp[i][j][0][0] = twos;
                dp[i][j][0][1] = fives;
            }
        }
        for (int j = 0; j < n; ++j) {
            int twos = 0;
            int fives = 0;
            for (int i = m - 1; i >= 0; --i) {
                if (a[i][j] % 2 == 0) {
                    twos += drill(a[i][j], 2);
                    ;
                }
                if (a[i][j] % 5 == 0) {
                    fives += drill(a[i][j], 5);
                }
                dp[i][j][1][0] = twos;
                dp[i][j][1][1] = fives;
            }
        }
        for (int i = 0; i < m; ++i) {
            int twos = 0;
            int fives = 0;
            for (int j = 0; j < n; ++j) {
                if (a[i][j] % 2 == 0) {
                    twos += drill(a[i][j], 2);
                    ;
                }
                if (a[i][j] % 5 == 0) {
                    fives += drill(a[i][j], 5);
                }
                dp[i][j][2][0] = twos;
                dp[i][j][2][1] = fives;
            }
        }
        for (int i = 0; i < m; ++i) {
            int twos = 0;
            int fives = 0;
            for (int j = n - 1; j >= 0; --j) {
                if (a[i][j] % 2 == 0) {
                    twos += drill(a[i][j], 2);
                    ;
                }
                if (a[i][j] % 5 == 0) {
                    fives += drill(a[i][j], 5);
                }
                dp[i][j][3][0] = twos;
                dp[i][j][3][1] = fives;
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // up then left
                int way12 = (i == 0 ? 0 : dp[i - 1][j][0][0]) + dp[i][j][2][0];
                int way15 = (i == 0 ? 0 : dp[i - 1][j][0][1]) + dp[i][j][2][1];
                int way1 = Math.min(way12, way15);

                // up then right
                int way22 = (i == 0 ? 0 : dp[i - 1][j][0][0]) + dp[i][j][3][0];
                int way25 = (i == 0 ? 0 : dp[i - 1][j][0][1]) + dp[i][j][3][1];
                int way2 = Math.min(way22, way25);

                // down then left
                int way32 = (i + 1 == m ? 0 : dp[i + 1][j][1][0]) + dp[i][j][2][0];
                int way35 = (i + 1 == m ? 0 : dp[i + 1][j][1][1]) + dp[i][j][2][1];
                int way3 = Math.min(way32, way35);

                // down then right
                int way42 = (i + 1 == m ? 0 : dp[i + 1][j][1][0]) + dp[i][j][3][0];
                int way45 = (i + 1 == m ? 0 : dp[i + 1][j][1][1]) + dp[i][j][3][1];
                int way4 = Math.min(way42, way45);
                int cur = Math.max(way1, Math.max(way2, Math.max(way3, way4)));
                res = Math.max(res, cur);
            }
        }
        return res;
    }

    private int drill(int i, int j) {
        int res = 0;
        while (i % j == 0) {
            ++res;
            i /= j;
        }
        return res;
    }
}
