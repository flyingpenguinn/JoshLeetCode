import java.util.HashMap;
import java.util.Map;

public class CountNumberOfGoodSubarrays {
    public long countGood(int[] a, int k) {
        int n = a.length;
        Map<Integer, Long> m = new HashMap<>();
        long res = 0;
        long cur = 0;
        int j = 0;
        for (int i = 0; i < n; ++i) {
            long ov = m.getOrDefault(a[i], 0L);
            long nv = ov + 1;
            m.put(a[i], nv);
            cur = cur - count(ov) + count(nv);
            while (cur >= k && j <= i) {
                res += n - i;
                long ojv = m.getOrDefault(a[j], 0L);
                long njv = ojv - 1;
                m.put(a[j], njv);
                cur = cur - count(ojv) + count(njv);
                ++j;
            }
        }
        return res;
    }

    private long count(long n) {
        return n == 0 ? 0 : n * (n - 1) / 2;
    }
}
