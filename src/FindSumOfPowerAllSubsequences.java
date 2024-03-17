public class FindSumOfPowerAllSubsequences {
    private long Mod = (long) (1e9 + 7);
    private Long[][][] dp;

    public int sumOfPower(int[] a, int k) {
        int n = a.length;
        dp = new Long[n][k + 1][n + 1];
        long rt = solve(a, 0, k, 0);
        return (int) (rt);
    }

    private long solve(int[] a, int i, int k, int picked) {
        int n = a.length;
        if (k < 0) {
            return 0;
        }
        if (i == n) {
            if (k > 0) {
                return 0;
            }
            long others = n - picked;
            long otherscount = pow2(others);
            return otherscount;
        }
        if (dp[i][k][picked] != null) {
            return dp[i][k][picked];
        }
        long way1 = solve(a, i + 1, k, picked);
        long way2 = solve(a, i + 1, k - a[i], picked + 1);
        long rt = way1 + way2;
        rt %= Mod;
        dp[i][k][picked] = rt;
        return rt;
    }

    private long pow2(long n) {
        if (n == 0) {
            return 1;
        }
        long half = pow2(n / 2);
        long res = half * half;
        res %= Mod;
        if (n % 2 == 1) {
            res *= 2;
            res %= Mod;
        }
        return res;
    }
}
