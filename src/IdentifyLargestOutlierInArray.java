import java.util.HashMap;
import java.util.Map;

public class IdentifyLargestOutlierInArray {
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int getLargestOutlier(int[] a) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        int sum = 0;
        int res = -2000;
        for (int ai : a) {
            sum += ai;
            update(m, ai, 1);
        }
        for (int i = 0; i < n; ++i) {
            update(m, a[i], -1);
            int others = sum - a[i];
            if (others % 2 == 0) {
                int half = others / 2;
                if (m.containsKey(half)) {
                    res = Math.max(res, a[i]);
                }
            }
            update(m, a[i], 1);
        }
        return res;
    }
}
