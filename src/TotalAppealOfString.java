import java.util.HashMap;
import java.util.Map;

public class TotalAppealOfString {
    public long appealSum(String s) {
        int n = s.length();
        Map<Integer, Integer> m = new HashMap<>();

        long res = 0;
        long[] dp = new long[n];
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            long cur = 0;
            if (!m.containsKey(cind)) {
                cur = (i == 0 ? 0 : dp[i - 1]) + (i + 1);
            } else {
                int pos = m.get(cind);
                long cur1 = dp[pos];
                long cur2 = dp[i - 1] - dp[pos] + (i - pos);
                cur = cur1 + cur2;
            }
            dp[i] = cur;
            m.put(cind, i);
            res += cur;
        }
        return res;
    }
}
