import java.util.HashMap;
import java.util.Map;

// either same number of whatevr number k-1 result
public class FindMaxLenOfGoodSubseqIandII {
    public int maximumLength(int[] a, int t) {
        int n = a.length;
        int[][] dp = new int[n][t + 1];
        for (int i = 0; i < n; ++i) {
            dp[i][0] = 1;
        }
        int res = 1;
        for (int k = 0; k <= t; ++k) {
            Map<Integer, Integer> km = new HashMap<>();
            km.put(a[0], 1);
            int maxk1m = -1;
            for (int i = 1; i < n; ++i) {
                if (k > 0) {
                    maxk1m = Math.max(dp[i - 1][k - 1], maxk1m);
                }
                int v = a[i];
                if (km.containsKey(v)) {
                    dp[i][k] = km.get(v) + 1;
                }
                if (k > 0) {
                    dp[i][k] = Math.max(dp[i][k], maxk1m + 1);
                }
                int cv = km.getOrDefault(v, 0);
                if (dp[i][k] > cv) {
                    km.put(v, dp[i][k]);
                }
                res = Math.max(res, dp[i][k]);
            }
        }
        return res;
    }
}
