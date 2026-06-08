public class MaxTotalValueOfCoveredIndex {
    // we can also look at consecutive 1 blocks, if l...r then get max numbers from l-1...r
    private Long[][] dp;

    public long maxTotal(int[] a, String s) {
        int n = s.length();
        dp = new Long[n][2];
        return solve(a, s, 0, 1);
    }

    // j: the override if set to 0
    private long solve(int[] a, String s, int i, int j) {
        int n = s.length();
        if (i == n) {
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        long cv = a[i];
        long csign = s.charAt(i) - '0';
        csign *= j;
        long way1 = cv * csign + solve(a, s, i + 1, 1);
        long way2 = 0;
        if (i + 1 < n && s.charAt(i + 1) - '0' == 1) {
            way2 = cv + solve(a, s, i + 1, 0);
        }
        long res = Math.max(way1, way2);
        dp[i][j] = res;
        return res;
    }
}
