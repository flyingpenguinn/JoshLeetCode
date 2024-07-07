import java.util.HashMap;
import java.util.Map;

public class NumberOfSubarrayWithAndValueK {
    public long countSubarrays(int[] a, int k) {
        int n = a.length;
        Map<Long, Long> m = new HashMap<>();
        long res = 0;
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            Map<Long, Long> nm = new HashMap<>();
            for (long mk : m.keySet()) {
                long cur = v & mk;
                long cc = m.get(mk);
                if (cur == k) {
                    res += cc;
                }
                nm.put(cur, nm.getOrDefault(cur, 0L) + cc);
            }
            nm.put(v, nm.getOrDefault(v, 0L) + 1);
            if (v == k) {
                ++res;
            }
            m = nm;
        }
        return res;
    }
}
