public class MinIncrementOperations {
    public long minIncrementOperations(int[] a, int k) {
        long[] la = new long[a.length];
        for (int i = 0; i < a.length; ++i) {
            la[i] = a[i];
        }
        dp = new Long[a.length][3];
        return solve(la, 0, 2, k);
    }

    private Long[][] dp;

    private long solve(long[] a, int i, int j, long k) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        long res = 0;
        if (a[i] > k) {
            res = solve(a, i + 1, 2, k);
        } else {
            if (j == 0) {
                res = (k - a[i]) + solve(a, i + 1, 2, k);
            } else {
                long way1 = solve(a, i + 1, j - 1, k);
                long way2 = (k - a[i]) + solve(a, i + 1, 2, k);
                res = Math.min(way1, way2);
            }
        }
        dp[i][j] = res;
        return res;
    }
}
