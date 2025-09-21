public class NumberOfStableSubseq {
    private Long[][][] dp;
    private long mod = (long) (1e9 + 7);

    public int countStableSubsequences(int[] a) {
        int n = a.length;
        dp = new Long[n][3][3];
        return (int) solve(a, 0, 2, 2) - 1;
    }

    private long solve(int[] a, int i, int p1, int p2) {
        int n = a.length;
        if (i == n) {
            return 1;
        }
        if (dp[i][p1][p2] != null) {
            return dp[i][p1][p2];
        }
        int cp = a[i] % 2;
        long way1 = solve(a, i + 1, p1, p2);
        long way2 = 0;
        if (cp != p1 || cp != p2) {
            way2 = solve(a, i + 1, p2, cp);
        }
        long res = way1 + way2;
        res %= mod;
        dp[i][p1][p2] = res;
        return res;
    }
}
