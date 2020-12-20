import java.util.HashMap;
import java.util.Map;

public class MaxErasureValue {
    // all positive so we can do two pointer...when we move low we know any smaller high won't do it
    public int maximumUniqueSubarray(int[] a) {
        int low = 0;
        int high = -1;
        Map<Integer, Integer> m = new HashMap<>();
        int sum = 0;
        int max = 0;
        while (true) {
            if (m.size() == high - low + 1) {
                max = Math.max(max, sum);
                high++;
                if (high == a.length) {
                    break;
                }
                update(m, a[high], 1);
                sum += a[high];

            } else {
                update(m, a[low], -1);
                sum -= a[low];
                low++;
            }
        }
        return max;
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
