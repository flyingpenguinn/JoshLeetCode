public class StoneGameViii {
    // transformation of the top down dp below to avoid n^2 loop
    // dp[i] = max(sum[j]-dp[j+1]), j>=i, we then return dp[1]
    // note it's dp[1] because we must take more than 1 stones
    int stoneGameVIII(int[] a) {
        int n = a.length;
        int[] sum = new int[n];
        sum[0] = a[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + a[i];
        }
        int[] dp = new int[n + 1];
        dp[n - 1] = sum[n - 1];
        int curmax = dp[n - 1];
        for (int i = n - 2; i >= 1; i--) {
            dp[i] = Math.max(curmax, sum[i] - dp[i + 1]);
            curmax = dp[i];
        }
        return dp[1];
    }
}


class StoneGameSlow {
    // slow but top down dp
    int Max = 2000000010;
    int Min = -Max;

    int stoneGameVIII(int[] a) {
        int n = a.length;
        int[] sum = new int[n];
        sum[0] = a[0];
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + a[i];
        }
        return solve(sum, 1, dp);

    }

    int solve(int[] a, int i, int[] dp) {
        if (i >= a.length) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int res = Min;
        for (int j = i; j < a.length; j++) {
            // cur - max(the other side - rest of cur) = cur+rest of cur - the other side
            int cur = a[j] - solve(a, j + 1, dp);
            res = Math.max(cur, res);
        }
        dp[i] = res;
        return res;
    }

}
