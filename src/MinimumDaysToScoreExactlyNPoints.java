import java.util.Arrays;

public class MinimumDaysToScoreExactlyNPoints {
    private static int[] dp = new int[100001];

    public int minDays(int n) {
        Arrays.fill(dp, -1);
        return solve(n);
    }

    private int solve(int n) {
        if (n == 0) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int res = Integer.MAX_VALUE;
        for (int d = 1; d * (d + 1) / 2 <= n; ++d) {
            int cut = d * (d + 1) / 2;
            int cur = 0;
            if (n == cut) {
                cur = d;
            } else {
                cur = d + 1 + solve(n - cut);
            }
            res = Math.min(res, cur);
        }
        dp[n] = res;
        return res;
    }
}
