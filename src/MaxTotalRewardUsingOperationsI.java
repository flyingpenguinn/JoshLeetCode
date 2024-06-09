import base.ArrayUtils;

import java.util.Arrays;

public class MaxTotalRewardUsingOperationsI {
    public int maxTotalReward(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        dp = new int[n][max];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return solve(a, 0, 0);
    }

    /// cannot be 2005: what if sum is > 2000
    private int max = 5005;
    private int[][] dp;

    private int solve(int[] a, int i, int csum) {
        int n = a.length;
        if (i == n) {
            return csum;
        }
        if (dp[i][csum] != -1) {
            return dp[i][csum];
        }
        int way1 = solve(a, i + 1, csum);
        int way2 = 0;
        if (a[i] > csum) {
            way2 = solve(a, i + 1, Math.min(csum + a[i], max));
        }
        int res = Math.max(way1, way2);
        dp[i][csum] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaxTotalRewardUsingOperationsI().maxTotalReward(ArrayUtils.read1d("1999, 2000")));
    }

}
