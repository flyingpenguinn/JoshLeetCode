import base.ArrayUtils;

import java.util.Arrays;
// hoffman tree, similar to min time to build blocks
public class FindTimeRequiredToEliminateBacterialStrainsII {
    private long[][] maxr;

    public long minEliminationTime(int[] a, int t) {
        Arrays.sort(a);
        int n = a.length;
        maxr = new long[n][20];
        for (int i = 0; i < n; ++i) {
            maxr[i][0] = a[i];
        }
        for (int len = 1; (1 << len) <= n; ++len) {
            for (int i = 0; i + (1<<len) - 1 < n; ++i) {
                maxr[i][len] = Math.max(maxr[i][len - 1], maxr[i + (1 << (len - 1))][len - 1]);
            }
        }
        dp = new long[n];
        Arrays.fill(dp, -1);
        return solve(a, 0,  t);
    }

    private long getmax(int l, int r) {
        int k = 0;
        while (1 << (k + 1) <= r - l + 1) {
            ++k;
        }
        return Math.max(maxr[l][k], maxr[r - (1 << k) + 1][k]);
    }

    private long[] dp;

    // at i we start from 1 wbc
    private long solve(int[] a, int i, int t) {
        int n = a.length;
        if (i == n) {
            return 0L;
        }
        if(i==n-1){
            return a[i];
        }
        if(dp[i] != -1){
            return dp[i];
        }

        long times = 1;
        long minlater = 0;
        while(true){
            int end = i+(1<<times)-1;
            if(end>=n){
                break;
            }
            long cmax = getmax(i, end-1);
            long later = times * t +cmax + solve(a, end, t);
            minlater = Math.min(minlater, later);
            ++times;
        }
        dp[i] = minlater;
        return minlater;
    }

    public static void main(String[] args) {
        System.out.println(new FindTimeRequiredToEliminateBacterialStrainsII().minEliminationTime(ArrayUtils.read1d("[10,4,5]"), 2));
    }
}
