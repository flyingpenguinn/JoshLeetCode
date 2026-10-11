import java.util.Arrays;
import java.util.List;

public class MinTimeToBreakLocksI {
    public int findMinimumTime(List<Integer> a, int k) {
        // push dp. n*2^n
        int n = a.size();
        int[] dp = new int[(1 << n)];
        int Max = (int) 1e9;
        Arrays.fill(dp, Max);
        dp[0] = 0;
        for (int st = 0; st < (1 << n); ++st) {
            int times = Integer.bitCount(st);
            int nx = 1 + times * k;
            for (int i = 0; i < n; ++i) {
                if (((st >> i) & 1) == 0) {
                    int timeadd = Math.ceilDiv(a.get(i), nx);
                    int nst = st | (1 << i);
                    dp[nst] = Math.min(dp[nst], dp[st] + timeadd);
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}
