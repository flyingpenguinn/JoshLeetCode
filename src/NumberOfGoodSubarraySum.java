import java.util.HashMap;
import java.util.Map;

public class NumberOfGoodSubarraySum {
    public long maximumSubarraySum(int[] ia, int k) {
        int n = ia.length;
        long[] a = new long[n];
        for (int i = 0; i < n; ++i) {
            a[i] = ia[i];
        }
        long[] sum = new long[n];
        sum[0] = a[0];
        for (int i = 1; i < n; ++i) {
            sum[i] = sum[i - 1] + a[i];
        }
        Map<Long, Long> m = new HashMap<>();
        long res = Long.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            long t1 = a[i] - k;
            if (m.containsKey(t1)) {
                long pre = m.get(t1);
                long cur = sum[i] - pre;
                res = Math.max(res, cur);
            }
            long t2 = a[i] + k;
            if (m.containsKey(t2)) {
                long pre = m.get(t2);
                long cur = sum[i] - pre;
                res = Math.max(res, cur);
            }
            long cpre = i == 0 ? 0 : sum[i - 1];
            if (m.containsKey(a[i])) {
                if (cpre < m.get(a[i])) {
                    m.put(a[i], cpre);
                }
            } else {
                m.put(a[i], cpre);
            }
        }
        return res <= Long.MIN_VALUE ? 0 : res;
    }
}
