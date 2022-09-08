import java.util.Arrays;

public class MinTimeToKillAllMonsters {
    private long Max = (long) 1e18;
    private Long[] dp;

    public long minimumTime(int[] power) {
        int n = power.length;
        dp = new Long[(1 << n)];
        return solve(0, 0, power);
    }

    private long solve(int defeated, int st, int[] power) {
        int n = power.length;
        if (defeated == n) {
            return 0;
        }
        if (dp[st] != null) {
            return dp[st];
        }
        int gain = defeated + 1;
        long res = Max;
        for (int i = 0; i < n; ++i) {
            if (((st >> i) & 1) == 0) {
                long days = (long) Math.ceil(1.0 * power[i] / gain);
                int nst = (st | (1 << i));
                long later = solve(defeated + 1, nst, power);
                res = Math.min(res, days + later);
            }
        }
        dp[st] = res;
        return res;
    }
}
