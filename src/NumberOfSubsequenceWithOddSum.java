import java.util.Arrays;

public class NumberOfSubsequenceWithOddSum {
    public int subsequenceCount(int[] a) {
        int n = a.length;
        dp = new long[n][2];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return (int) solve(a, 0, 0);
    }

    private long[][] dp;
    private long Mod = (long) (1e9 + 7);

    private long solve(int[] a, int i, int j) {
        int n = a.length;
        if (i == n) {
            return j;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        long way1 = solve(a, i + 1, j);
        long way2 = solve(a, i + 1, (j + a[i]) % 2);
        long res = way1 + way2;
        res %= Mod;
        dp[i][j] = res;
        return res;
    }
}
