import base.ArrayUtils;

public class MaxStrengthOfKDisjointSubarrays {
    // segment choosing problem we should consider "started" or "picked" flag like this
    private Long[][][] dp;
    private long Min = (long) -1e18;

    public long maximumStrength(int[] a, int k) {
        int n = a.length;
        dp = new Long[n][k + 1][2];
        return solve(a, 0, k, 0);
    }

    // started here is key. it is used to indicate whether we conclude current segment or not
    // here sum*k means we are adding each a[i]*k to the result so they are independent
    private long solve(int[] a, int i, int k, int started) {
        int n = a.length;
        if (k == 0) {
            return 0;
        }
        if (i == n) {
            if (k == 1 && started == 1) {
                // we have added the results earlier
                return 0;
            } else {
                return Min;
            }
        }
        if (dp[i][k][started] != null) {
            return dp[i][k][started];
        }
        long way1 = 0;
        if (started == 1) {
            // if we started, we can close off the segment till i-1, and start a new one at i
            way1 = solve(a, i, k - 1, 0);
        } else {
            // if we have not started, we can skip current element
            way1 = solve(a, i + 1, k, 0);
        }
        long way2 = (k % 2 == 1 ? 1L : -1L) * a[i] * k + solve(a, i + 1, k, 1);
        long res = Math.max(way1, way2);
        dp[i][k][started] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaxStrengthOfKDisjointSubarrays().maximumStrength(ArrayUtils.read1d("[1,2,3,1,2]"), 3));
    }
}
