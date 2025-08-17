import java.util.Arrays;

public class MinSumAfterDivisibleSumDeletions {
    private int[] next;

    public long minArraySum(int[] a, int k) {
        int n = a.length;
        next = new int[n];
        int[] mk = new int[k];
        Arrays.fill(mk, -2);
        long psum = 0;
        Arrays.fill(next, -2);
        mk[0] = -1;
        for (int i = 0; i < n; ++i) {
            psum += a[i];
            int mod = (int) (psum % k);
            if (mk[mod] != -2) {
                int pre = mk[mod];
                next[pre + 1] = Math.max(next[pre + 1], i);
            }
            mk[mod] = i;
        }
        dp = new long[n];
        Arrays.fill(dp, -1);
        return solve(a, 0);
    }

    private long Max = (long) 1e15;
    private long[] dp;

    private long solve(int[] a, int i) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        long way1 = a[i] + solve(a, i + 1);
        long way2 = Max;
        if (next[i] != -2) {
            way2 = solve(a, next[i] + 1);
        }
        long res = Math.min(way1, way2);
        dp[i] = res;
        return res;
    }
}
