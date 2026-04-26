import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxSumAlternatingSubseqWithDistAtleastK {
    private void u(long[] bit, int i, long v) {
        while (i < bit.length) {
            bit[i] = Math.max(bit[i], v);
            i += i & (-i);
        }
    }

    private long q(long[] bit, int i) {
        long res = 0;
        while (i > 0) {
            res = Math.max(res, bit[i]);
            i -= i & (-i);
        }
        return res;
    }

    public long maxAlternatingSum(int[] a, int k) {
        int n = a.length;
        int[] na = Arrays.copyOf(a, n);
        Arrays.sort(na);
        int rank = 1;
        int i = 0;
        Map<Integer, Integer> rm1 = new HashMap<>();
        while (i < n) {
            int j = i;
            while (j < n && na[j] == na[i]) {
                ++j;
            }
            rm1.put(na[i], rank++);
            i = j;
        }
        int biggestrank = rank;
        Map<Integer, Integer> rm0 = new HashMap<>();
        for (int key : rm1.keySet()) {
            int rv = rm1.get(key);
            rm0.put(key, biggestrank - rv);
        }
        long[][] dp = new long[n + 1][2];
        dp[n][0] = 0;
        dp[n][1] = 0;
        long[] bit0 = new long[rank + 1];
        long[] bit1 = new long[rank + 1];
        long res = 0;
        for (i = n - 1; i >= 0; --i) {
            long cv = a[i];
            if (i + k >= n) {
                dp[i][0] = cv;
                dp[i][1] = cv;
            } else {
                int nextv = a[i + k];
                int nextrank0 = rm0.get(nextv);
                int nextrank1 = rm1.get(nextv);
                u(bit0, nextrank0, dp[i + k][1]);
                u(bit1, nextrank1, dp[i + k][0]);
                int cvrank0 = rm0.get(a[i]);
                int cvrank1 = rm1.get(a[i]);
                long benefit1 = q(bit1, cvrank1 - 1);
                dp[i][1] = benefit1 + cv;
                long benefit0 = q(bit0, cvrank0 - 1);
                dp[i][0] = benefit0 + cv;


            }

            res = Math.max(res, dp[i][0]);
            res = Math.max(res, dp[i][1]);
        }
        return res;
    }
}
