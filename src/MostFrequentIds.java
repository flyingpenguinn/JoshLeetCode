import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MostFrequentIds {
    private Map<Long, Long> am = new HashMap<>();
    private TreeMap<Long, Long> fm = new TreeMap<>();

    private void update(Map<Long, Long> m, long k, int d) {
        long nv = m.getOrDefault(k, 0L) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public long[] mostFrequentIDs(int[] a, int[] f) {
        int n = a.length;
        long[] res = new long[n];
        for (int i = 0; i < n; ++i) {
            long k = a[i];
            long d = f[i];
            long cv = am.getOrDefault(k, 0L);
            if (cv > 0) {
                update(fm, cv, -1);
            }
            long nv = cv + d;
            if (nv > 0) {
                update(fm, nv, 1);
            }
            am.put(k, nv);
            if (fm.isEmpty()) {
                res[i] = 0;
            } else {
                res[i] = fm.lastKey();
            }
        }
        return res;
    }
}
