import java.util.Arrays;

public class MinIncrementsForTargetMultiplesInArray {
    private int Max = (int) 1e9;
    private long[][] dp;

    public int minimumIncrements(int[] a, int[] t) {
        int tn = t.length;
        int n = a.length;
        dp = new long[n][(1 << tn)];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return (int) solve(a, t, 0, (1 << tn) - 1);
    }

    private long solve(int[] a, int[] t, int i, int set) {
        int n = a.length;
        int tn = t.length;
        if (i == n) {
            return set == 0 ? 0 : Max;
        }
        if (dp[i][set] != -1) {
            return dp[i][set];
        }
        long res = solve(a, t, i + 1, set);
        for (int sub = set; sub >= 1; sub = (sub - 1) & set) {
            long lcm = 1;
            for (int j = 0; j < tn; ++j) {
                if (((sub >> j) & 1) == 1) {
                    lcm = getlcm(lcm, t[j]);
                }
            }

            if (lcm < a[i]) {
                long oldlcm = lcm;
                long times = a[i] / lcm;
                lcm *= times;
                if (lcm < a[i]) {
                    lcm += oldlcm;
                }
            }
            long delta = lcm - a[i];
            long later = solve(a, t, i + 1, set - sub);
            long cur = delta + later;
            res = Math.min(res, cur);
        }
        dp[i][set] = res;
        return res;
    }

    private long getlcm(long a, long b) {
        long g = gcd(a, b);
        return a * b / g;
    }

    private long gcd(long a, long b) {
        if (a < b) {
            return gcd(b, a);
        }
        return b == 0 ? a : gcd(b, a % b);
    }
}
