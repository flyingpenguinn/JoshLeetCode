import java.util.HashMap;
import java.util.Map;

public class MaxLinearStockScore {
    public long maxScore(int[] a) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        long[] dp = new long[n];

        long res = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i] - i;
            if (m.containsKey(v)) {
                int pre = m.get(v);
                //  System.out.println("at "+i+" found "+v+" "+pre);
                dp[i] = dp[pre] + a[i];
            }
            dp[i] = Math.max(dp[i], a[i]);
            res = Math.max(dp[i], res);
            m.put(v, i);
        }
        return res;
    }
}
