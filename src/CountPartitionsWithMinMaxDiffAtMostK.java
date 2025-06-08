import java.util.Map;
import java.util.TreeMap;

public class CountPartitionsWithMinMaxDiffAtMostK {
    // index start from 1!
    private void update(Map<Integer, Integer> tm, int k, int d) {
        int nv = tm.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            tm.remove(k);
        } else {
            tm.put(k, nv);
        }
    }

    public int countPartitions(int[] a, int k) {
        int n = a.length;
        int j = 1;
        int[] pre = new int[n + 1];
        long Mod = (long) (1e9 + 7);
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 1; i <= n; ++i) {
            update(tm, a[i - 1], 1);
            while (j <= i && tm.lastKey() - tm.firstKey() > k) {
                update(tm, a[j - 1], -1);
                ++j;
            }
            pre[i] = j;
        }
        long[] dp = new long[n + 1];
        long[] dpsum = new long[n + 1];
        dp[0] = 1;
        dpsum[0] = 1;
        for (int i = 1; i <= n; ++i) {
            int prej = pre[i];
            // sum from prej-1... to i-1
            long presum = dpsum[i - 1] - (prej - 2 < 0 ? 0 : dpsum[prej - 2]);
            presum %= Mod;
            if (presum < 0) {
                presum += Mod;
            }
            dp[i] = presum;
            dpsum[i] = dpsum[i - 1] + dp[i];
            dp[i] %= Mod;
        }
        return (int) dp[n];
    }
}
