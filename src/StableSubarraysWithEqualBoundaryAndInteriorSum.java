import java.util.HashMap;
import java.util.Map;

public class StableSubarraysWithEqualBoundaryAndInteriorSum {
    public long countStableSubarrays(int[] a) {
        int n = a.length;
        long[] sum = new long[n];
        Map<Long, Map<Long, Long>> cnt = new HashMap<>();
        long res = 0;
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + v;
            int head = i - 2;
            if (head >= 0) {
                update(cnt, sum[head], (long) a[head]);
                long target = sum[i - 1] - v;
                Map<Long, Long> counter = cnt.getOrDefault(target, new HashMap<>());
                long cur = counter.getOrDefault(v, 0L);
                res += cur;
            }

        }
        return res;
    }

    private void update(Map<Long, Map<Long, Long>> cnt, long key1, long key2) {
        Map<Long, Long> ext = cnt.getOrDefault(key1, new HashMap<>());
        ext.put(key2, ext.getOrDefault(key2, 0L) + 1);
        cnt.put(key1, ext);

    }
}
