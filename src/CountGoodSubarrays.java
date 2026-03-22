import java.util.HashMap;
import java.util.Map;

public class CountGoodSubarrays {
    private void update(Map<Integer, Long> m, int k, long d) {
        long nv = m.getOrDefault(k, 0L) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public long countGoodSubarrays(int[] a) {
        int n = a.length;

        HashMap<Integer, Long> last = new HashMap<>();
        long[] left = new long[n];
        Map<Integer, Integer> pre = new HashMap<>();
        for (int i = 0; i < n; i++) {
            HashMap<Integer, Long> cur = new HashMap<>();
            update(cur, a[i], 1);
            for (int si : last.keySet()) {
                int nv = si | a[i];
                long lcnt = last.get(si);
                update(cur, nv, lcnt);
            }

            long cnt = cur.getOrDefault(a[i], 0L);
            left[i] = cnt;
            int prediff = pre.getOrDefault(a[i], -1);
            left[i] = Math.min(left[i], i - prediff);
            last = cur;
            pre.put(a[i], i);
        }
        long res = 0;

        last.clear();
        for (int i = n - 1; i >= 0; --i) {
            HashMap<Integer, Long> cur = new HashMap<>();
            update(cur, a[i], 1);
            for (int si : last.keySet()) {
                int nv = si | a[i];
                long lcnt = last.get(si);
                update(cur, nv, lcnt);
            }

            long rcnt = cur.getOrDefault(a[i], 0L);
            long ccnt = left[i] * rcnt;
            res += ccnt;
            last = cur;
        }
        return res;

    }
}
