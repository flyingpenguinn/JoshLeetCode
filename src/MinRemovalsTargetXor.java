import java.util.Arrays;

public class MinRemovalsTargetXor {
    private int Max = (int) 1e9;
    private int Min = -Max;
    private int[][] dp;

    public int minRemovals(int[] a, int t) {
        int n = a.length;
        int max = 0;
        for (int i = 0; i < n; ++i) {
            max = Math.max(max, a[i]);
        }
        dp = new int[n][2 * max + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        int rt = solve(a, 0, 0, t);
        if (rt >= Max) {
            return -1;
        }
        return rt;
    }


    private int solve(int[] a, int i, int cur, int t) {
        int n = a.length;
        if (i == n) {
            return cur == t ? 0 : Max;
        }
        if (dp[i][cur] != -1) {
            return dp[i][cur];
        }
        int way1 = 1 + solve(a, i + 1, cur, t);
        int way2 = solve(a, i + 1, cur ^ a[i], t);
        int res = Math.min(way1, way2);
        dp[i][cur] = res;
        return res;
    }
}
