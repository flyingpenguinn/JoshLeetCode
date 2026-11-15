import java.util.*;

public class MaxDifferenceTakeOneOrTwoMod3 {
    // Weekly 528 Q3, AC at 26:31 (~5.5 min), 0 WA. (i, phase)-style memo DP.
    // At each step take 1 or 2 elements; a chunk whose sum % 3 == 0 flips the sign of the rest: sum - solve(rest),
    // otherwise sum + solve(rest).
    private long[] dp;

    public long maxDifference(int[] a) {
        int n = a.length;
        dp = new long[n];
        Arrays.fill(dp, IMP);
        return solve(a, 0);
    }

    private long IMP = (long) 1e18;
    private long Min = (long) -1e18;

    private long solve(int[] a, int i) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i] != IMP) {
            return dp[i];
        }
        long way1 = Min;
        if (a[i] % 3 == 0) {
            way1 = a[i] - solve(a, i + 1);
        } else {
            way1 = a[i] + solve(a, i + 1);
        }
        long way2 = Min;
        if (i + 1 < n) {
            long sum2 = a[i] + a[i + 1];
            if (sum2 % 3 == 0) {
                way2 = sum2 - solve(a, i + 2);
            } else {
                way2 = sum2 + solve(a, i + 2);
            }
        }
        long res = Math.max(way1, way2);
        dp[i] = res;
        return res;
    }
}
