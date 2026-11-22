import java.util.Arrays;

public class MinCostToDeleteArrayBySubarrays {
    // Weekly 529 Q4, solved ~10 min after the contest ended (joined 15 min late, so within 90 min of actual time).
    // Rewrite: only the endpoints of each deleted subarray are paid. a[0] and a[n-1] are always paid; any even-size subset
    // of the interior can be extra endpoints -> answer = a[0] + a[n-1] + min even-size subset sum of a[1..n-2] (empty allowed).
    // dp(i, parity) over the interior. Brute-force checked (all deletion orders, n <= 7).
    public long minCost(int[] a) {
        long psum = 0;
        long maxprev0 = 0;
        long maxprev1 = 0;
        int n = a.length;
        dp = new long[n][2];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        long bestmin = solve(a, 1, n - 1, 0);
        long minv = Math.min(0, bestmin);
        long res = a[0] + a[n - 1] + minv;
        return res;
    }

    long Max = (long) 1e18;
    long[][] dp;

    private long solve(int[] a, int i, int end, int parity) {
        int n = a.length;
        if (i == end) {
            return parity == 0 ? 0 : Max;
        }
        if (dp[i][parity] != -1) {
            return dp[i][parity];
        }
        long way1 = a[i] + solve(a, i + 1, end, parity ^ 1);
        long way2 = solve(a, i + 1, end, parity);
        long res = Math.min(way1, way2);
        dp[i][parity] = res;
        return res;
    }
}
