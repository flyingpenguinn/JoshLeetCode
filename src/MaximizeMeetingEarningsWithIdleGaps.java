import java.util.Arrays;

public class MaximizeMeetingEarningsWithIdleGaps {
    public long maxEarnings(int[][] a) {
        int n = a.length;
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        long[] dp = new long[n + 1];
        long[] calc = new long[n + 1];
        long[] maxcalc = new long[n+1];
        for (int i = n - 1; i >= 0; --i) {
            int pos = binary(a, a[i][1]);
            long prof = a[i][2];
            if (pos < a.length) {
                prof += maxcalc[pos] - a[i][1];
            }
            dp[i] = Math.max(prof, dp[i + 1]);
            calc[i] = dp[i] + a[i][0];
            maxcalc[i] = Math.max(calc[i], maxcalc[i+1]);
        }
        return dp[0];
    }

    private int binary(int[][] a, int t) {
        int n = a.length;
        int l = 0;
        int u = n-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(a[mid][0]>=t){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l;
    }

}
