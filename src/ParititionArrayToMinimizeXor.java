import java.util.Arrays;

public class ParititionArrayToMinimizeXor {
    private long Max = Integer.MAX_VALUE;


    private int[] suffix;
    private int[][] dp;

    public int minXor(int[] a, int k) {
        int n = a.length;
        suffix = new int[n];
        int xor = 0;
        for (int i = n - 1; i >= 0; --i) {
            xor ^= a[i];
            suffix[i] = xor;
        }
        dp = new int[n][k + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return solve(a, 0, k);
    }

    private int solve(int[] a, int i, int k) {
        int n = a.length;
        if (k == 1) {
            return suffix[i];
        }
        if (dp[i][k] != -1) {
            return dp[i][k];
        }
        int xor = 0;
        int res = (int) Max;
        for (int j = i; j < n - 1; ++j) {
            xor ^= a[j];
            int later = solve(a, j + 1, k - 1);
            int cur = Math.max(xor, later);
            res = Math.min(res, cur);
        }
        dp[i][k] = res;
        return res;
    }
}
