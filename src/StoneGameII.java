import java.util.Arrays;

public class StoneGameII {
    // get the max diff between a and b
    public int stoneGameII(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][n + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        int sum = 0;
        for (int ai : a) {
            sum += ai;
        }
        int diff = solve(a, 0, 1, dp);
        int got = (sum + diff) / 2;
        return got;
    }

    private int solve(int[] a, int i, int j, int[][] dp) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int end = Math.min(n, i + 2 * j);
        int diff = 0;
        int res = Integer.MIN_VALUE;
        for (int k = i; k < end; k++) {
            int x = k - i + 1;
            diff += a[k];
            // a score - (b score - a score) == diff of a and b
            int later = solve(a, k + 1, Math.max(x, j), dp);
            int cur = diff - later;
            res = Math.max(res, cur);
        }
        dp[i][j] = res;
        return res;
    }
}
