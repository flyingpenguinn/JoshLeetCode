import java.util.Arrays;

public class ClimbingStairsII {
    public int climbStairs(int n, int[] a) {
        n = a.length;
        dp = new int[n];
        Arrays.fill(dp, -1);
        return solve(a, 0);
    }

    private int[] dp;

    private int solve(int[] a, int i) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int res = Integer.MAX_VALUE;
        for (int j = i + 1; j <= i + 3 && j <= n; ++j) {
            int cur = a[j - 1] + (j - i) * (j - i) + solve(a, j);
            res = Math.min(res, cur);
        }
        dp[i] = res;
        return res;
    }
}
