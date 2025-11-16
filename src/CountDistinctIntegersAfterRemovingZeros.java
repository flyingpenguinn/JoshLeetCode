public class CountDistinctIntegersAfterRemovingZeros {
    public long countDistinct(long n) {
        String sn = String.valueOf(n);
        dp = new Long[sn.length()][sn.length()][2];
        return solve(sn, 0, 0, 0);
    }

    private Long[][][] dp;

    private long solve(String sn, int i, int sm, int started) {
        int n = sn.length();
        if (i == n) {
            return started == 1 ? 1 : 0;
        }
        if (dp[i][sm][started] != null) {
            return dp[i][sm][started];
        }
        long res = 0;
        int sind = sn.charAt(i) - '0';
        int cstart = started == 1 ? 1 : 0;
        for (int j = cstart; j <= 9; ++j) {
            if (sm == 0 && j > sind) {
                break;
            }
            int nsm = sm;
            if (j < sind) {
                nsm = 1;
            }
            int nstarted = started;
            if (j > 0) {
                nstarted = 1;
            }
            long later = solve(sn, i + 1, nsm, nstarted);
            res += later;
        }
        dp[i][sm][started] = res;
        return res;
    }
}
