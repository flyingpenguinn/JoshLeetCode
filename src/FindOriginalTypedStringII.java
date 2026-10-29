import java.util.ArrayList;
import java.util.List;

public class FindOriginalTypedStringII {
    public int possibleStringCount(String word, int k) {
        char[] c = word.toCharArray();
        int i = 0;
        int n = c.length;
        List<Integer> l = new ArrayList<>();
        while (i < n) {
            int j = i;
            while (j < n && c[j] == c[i]) {
                ++j;
            }
            int len = j - i;
            l.add(len);
            i = j;
        }
        long mod = (long) (1e9 + 7);
        if (l.size() >= k) {
            long count = 1;
            for (i = 0; i < l.size(); ++i) {
                count *= l.get(i);
                count %= mod;
            }
            return (int) count;
        } else {
            int ln = l.size();
            long[][] dp = new long[ln + 1][k + 1];
            long[] sum = new long[k + 1];
            for (i = ln; i >= 0; --i) {
                int v = i == ln ? 0 : l.get(i);
                long[] csum = new long[k + 1];
                for (int j = 0; j <= k; ++j) {
                    if (i == ln) {
                        dp[i][j] = j == k ? 1 : 0;
                    } else {
                        int end = j + v;
                        int endextrahits = end - k;
                        end = Math.min(end, k);
                        long cur = sum[end] - sum[j];
                        if (endextrahits > 0) {
                            cur += endextrahits * dp[i + 1][k];
                        }
                        cur %= mod;
                        if (cur < 0) {
                            cur += mod;
                        }
                        dp[i][j] = cur;
                    }
                    csum[j] = (j == 0 ? 0 : csum[j - 1]) + dp[i][j];
                    csum[j] %= mod;
                }
                sum = csum;
            }
            return (int) (dp[0][0]);
        }
    }

    private long mod = (long) (1e9 + 7);
}

