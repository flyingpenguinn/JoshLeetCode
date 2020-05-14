import java.util.Arrays;

public class MincostClimbingStairs {
    int[] dp;

    public int minCostClimbingStairs(int[] cost) {
        dp = new int[cost.length];
        Arrays.fill(dp, -1);
        return Math.min(domin(cost, 0), domin(cost, 1));
    }

    int domin(int[] cost, int i) {
        if (i >= cost.length) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int s1 = cost[i] + domin(cost, i + 1);
        int s2 = cost[i] + domin(cost, i + 2);
        dp[i] = Math.min(s1, s2);
        return dp[i];
    }
}
