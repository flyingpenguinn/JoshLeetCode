import java.util.Arrays;

public class MaxScoreUsingExactlyKPairs {
    private long Min = (long) -1e15;
    private long Unset = (long) (-1e16);
    private long[][][] dp;

    public long maxScore(int[] a, int[] b, int k) {
        int n = a.length;
        int m = b.length;
        dp = new long[n][m][k + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                Arrays.fill(dp[i][j], Unset);
            }
        }
        return solve(a, b, 0, 0, k);
    }

    private long solve(int[] a, int[] b, int i, int j, int k) {
        int n = a.length;
        int m = b.length;
        if (k == 0) {
            return 0;
        }
        if (i == n || j == m) {
            return Min;
        }
        if (dp[i][j][k] != Unset) {
            return dp[i][j][k];
        }
        long way1 = solve(a, b, i + 1, j, k);
        long way2 = solve(a, b, i, j + 1, k);
        long v1 = a[i];
        long v2 = b[j];
        long way3 = v1 * v2 + solve(a, b, i + 1, j + 1, k - 1);
        long res = Math.max(way1, Math.max(way2, way3));
        dp[i][j][k] = res;
        return res;
    }
}
