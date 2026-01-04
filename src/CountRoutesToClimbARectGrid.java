public class CountRoutesToClimbARectGrid {
    int[][] samerow;
    int[][] prerow;
    long Mod = (long) (1e9 + 7);

    public int numberOfRoutes(String[] a, int d) {
        int m = a.length;
        int n = a[0].length();
        samerow = new int[n][2];
        prerow = new int[n][2];
        for (int j = 0; j < n; ++j) {
            int samemin = Math.max(j - d, 0);
            int samemax = Math.min(j + d, n - 1);
            samerow[j][0] = samemin;
            samerow[j][1] = samemax;
            int prerowmax = -1;
            int prerowmin = n + 1;
            for (int k = 0; k < n; ++k) {
                double edist = Math.sqrt(1.0 + 1.0 * (j - k) * (j - k));
                if (edist <= d) {
                    prerowmin = Math.min(prerowmin, k);
                    prerowmax = Math.max(prerowmax, k);
                }
                prerow[j][0] = prerowmin;
                prerow[j][1] = prerowmax;
            }
        }

        dp = new Long[m][n][2];

        long[] presum = new long[n];
        for (int i = 0; i < m; ++i) {
            long[] sumstay1 = new long[n];
            long[] cursum = new long[n];
            for (int stayed = 1; stayed >= 0; --stayed) {
                for (int j = 0; j < n; ++j) {
                    boolean good = a[i].charAt(j) != '#';
                    long way1 = 0;
                    if (stayed == 0  && good) {
                        int start = samerow[j][0];
                        int end = samerow[j][1];
                        way1 = sumstay1[end] - (start == 0 ? 0 : sumstay1[start - 1]) - dp[i][j][1];
                        way1 %= Mod;
                        if (way1 < 0) {
                            way1 += Mod;
                        }
                    }
                    long way2 = 0;
                    if (i > 0 && good) {
                        int start = prerow[j][0];
                        int end = prerow[j][1];
                        way2 = presum[end] - (start == 0 ? 0 : presum[start - 1]);
                        way2 %= Mod;
                        if (way1 < 0) {
                            way2 += Mod;
                        }
                    }
                    long cres = way1 + way2;
                    if (i == 0 && good) {
                        ++cres;
                    }
                    cres %= Mod;
                    if (stayed == 0) {
                        cursum[j] = (j == 0 ? 0 : cursum[j - 1]) + cres;
                    } else {
                        sumstay1[j] = (j == 0 ? 0 : sumstay1[j - 1]) + cres;
                    }
                    dp[i][j][stayed] = cres;
                }
            }
            for (int j = 0; j < n; ++j) {
                presum[j] = cursum[j];
            }
        }
        long res = 0;
        for (int j = 0; j < n; ++j) {
            res += dp[m - 1][j][0];
            res %= Mod;
        }
        return (int) res;
    }

    private Long[][][] dp;

    // will TLE but can be used to help write the iterative version
    private long solve(String[] a, int i, int j, int stayed) {
        int m = a.length;
        int n = a[0].length();
        if (a[i].charAt(j) == '#') {
            return 0;
        }


        if (dp[i][j][stayed] != null) {
            return dp[i][j][stayed];
        }
        long way1 = 0;
        if (stayed == 0) {
            for (int k = samerow[j][0]; k <= samerow[j][1]; ++k) {
                if (k == j) {
                    continue;
                }
                long later = solve(a, i, k, 1);
                way1 += later;
                way1 %= Mod;
            }
        }
        long way2 = 0;
        if (i > 0) {
            for (int k = prerow[j][0]; k <= prerow[j][1]; ++k) {
                long later = solve(a, i - 1, k, 0);
                way2 += later;
                way2 %= Mod;
            }
        }
        long res = way1 + way2;
        if (i == 0) {
            ++res;
        }
        res %= Mod;
        dp[i][j][stayed] = res;
        return res;
    }
}
