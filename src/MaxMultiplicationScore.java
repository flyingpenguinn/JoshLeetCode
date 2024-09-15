public class MaxMultiplicationScore {
    private long Min = (long) -1e16;

    public long maxScore(int[] a, int[] b) {
        int an = a.length;
        int bn = b.length;
        dp = new Long[an][bn];
        return solve(a, b, 0, 0);
    }

    private Long[][] dp;

    private long solve(int[] a, int[] b, int i, int j) {
        int an = a.length;
        int bn = b.length;
        if (i == an) {
            return 0;
        }
        if (j == bn) {
            return Min;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        long way1 = 1L * a[i] * b[j] + solve(a, b, i + 1, j + 1);
        long way2 = solve(a, b, i, j + 1);
        long res = Math.max(way1, way2);
        dp[i][j] = res;
        return res;
    }
}
