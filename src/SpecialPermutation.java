import java.util.Arrays;

public class SpecialPermutation {
    private Long[][][] dp;
    private long Mod = (long) (1e9 + 7);

    public int specialPerm(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        dp = new Long[n][n + 1][1 << n];
        return (int) solve(a, 0, n, 0);
    }

    private long solve(int[] a, int i, int pre, int st) {
        int n = a.length;
        if (i == n) {
            return 1L;
        }
        if (dp[i][pre][st] != null) {
            return dp[i][pre][st];
        }
        long res = 0;
        for (int k = 0; k < n; ++k) {
            if (((st >> k) & 1) == 1) {
                continue;
            }
            if ((pre == n) || (a[k] % a[pre] == 0 || a[pre] % a[k] == 0)) {
                int nst = (st | (1 << k));
                long cur = solve(a, i + 1, k, nst);
                res += cur;
                res %= Mod;
            }
        }
        dp[i][pre][st] = res;
        return res;
    }
}
