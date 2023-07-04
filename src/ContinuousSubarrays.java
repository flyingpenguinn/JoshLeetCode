import java.util.Map;
import java.util.TreeMap;

public class ContinuousSubarrays {
    public long continuousSubarrays(int[] a) {
        int n = a.length;
        TreeMap<Integer, Integer> m = new TreeMap<>();
        long res = 0;
        int j = 0;
        for (int i = 0; i < n; ++i) {

            while (!m.isEmpty()
                    && (m.firstKey() < a[i] - 2 || m.lastKey() > a[i] + 2)) {
                update(m, a[j], -1);
                ++j;
            }
            int diff = i - j + 1;
            res += diff;
            update(m, a[i], 1);
        }
        return res;
    }

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
