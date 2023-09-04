import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountOfInterestingSubarrays {
    public long countInterestingSubarrays(List<Integer> a, int modulo, int k) {
        int n = a.size();
        int cnt = 0;
        Map<Long, Long> count = new HashMap<>();
        // empty subarray has mod = 0
        count.put(0L, 1L);
        long res = 0;
        for (int i = 0; i < n; ++i) {
            if (a.get(i) % modulo == k) {
                ++cnt;
            }
            long mod = cnt % modulo;
            long v = mod - k;
            if (v < 0) {
                v += modulo;
            }
            res += count.getOrDefault(v, 0L);
            update(count, mod, 1);
        }
        return res;
    }

    private void update(Map<Long, Long> m, long k, long d) {
        long nv = m.getOrDefault(k, 0L) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
