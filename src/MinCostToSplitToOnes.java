import java.util.Arrays;

public class MinCostToSplitToOnes {
    private int[] dp;

    public int minCost(int n) {
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(n);
    }

    private int solve(int n) {
        if (n == 1) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        if (dp[n] != -1) {
            return dp[n];
        }
        for (int i = 1; i <= n - 1; ++i) {
            int a = i;
            int b = n - i;
            int ca = solve(a);
            int cb = solve(b);
            int cc = a * b;
            int cres = ca + cb + cc;
            res = Math.min(res, cres);
        }
        dp[n] = res;
        return res;
    }
}
