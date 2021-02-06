public class MaxSubarraySumAfterOneOperation {
    // if we increase current value, all later maxes will be increased
    // maxsubaray = for each i, max[i] - min[i-1] then get the max
    public int maxSumAfterOperation(int[] a) {
        int n = a.length;
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
        }
        //   System.out.println(Arrays.toString(sum));
        int[] min = new int[n];
        for (int i = 0; i < n; i++) {
            min[i] = i == 0 ? Math.min(sum[0], 0) : Math.min(min[i - 1], sum[i]);
        }
        int[] max = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            max[i] = i == n - 1 ? sum[n - 1] : Math.max(max[i + 1], sum[i]);
        }
        //   System.out.println(Arrays.toString(min));
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int delta = a[i] * a[i] - a[i];
            int cur = max[i] + delta - (i == 0 ? 0 : min[i - 1]);
            res = Math.max(res, cur);
        }
        return res;
    }
}

class MaxSubarraySumAfterOneOperationDp {
    public int maxSumAfterOperation(int[] a) {
        int n = a.length;
        // dp[i][0]: maxsubarray sum ending at i, without squaring
        // dp[i][1]: square once
        int[][] dp = new int[n][2];
        dp[0][0] = a[0];
        dp[0][1] = a[0] * a[0];
        int res = Math.max(dp[0][0], dp[0][1]);
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + a[i], a[i]);
            dp[i][1] = Math.max(a[i] * a[i], Math.max(dp[i - 1][0] + a[i] * a[i], dp[i-1][1] + a[i]));
            res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
        }
        return res;
    }
}
