public class CountNumberOfWaysPlaceHouses {
    private long mod = (long) (1e9 + 7);
    private Long[][] dp;

    public int countHousePlacements(int n) {
        dp = new Long[n][4];
        return (int) solve(0, 0, n);
    }

    private long solve(int i, int st, int n) {
        if (i == n) {
            return 1L;
        }
        if (dp[i][st] != null) {
            return dp[i][st];
        }
        long res = 0;
        long[] way = new long[4];
        way[0] = solve(i + 1, 0, n);
        if (st == 0) {
            way[1] = solve(i + 1, 1, n);
            way[2] = solve(i + 1, 2, n);
            way[3] = solve(i + 1, 3, n);
        } else if (st == 1) {
            way[2] = solve(i + 1, 2, n);
        } else if (st == 2) {
            way[1] = solve(i + 1, 1, n);
        }
        for (int j = 0; j < 4; ++j) {
            res += way[j];
            res %= mod;
        }
        dp[i][st] = res;
        return res;
    }
}
