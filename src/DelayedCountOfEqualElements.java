import java.util.HashMap;
import java.util.Map;

public class DelayedCountOfEqualElements {
    private void update(Map<Integer, Integer> cnt, int k, int d) {
        int nv = cnt.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            cnt.remove(k);
        } else {
            cnt.put(k, nv);
        }
    }

    public int[] delayedCount(int[] a, int k) {
        int n = a.length;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            update(cnt, a[i], 1);
        }
        int j = 0;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            while (j < n && j <= i + k) {
                update(cnt, a[j], -1);
                ++j;
            }
            int cur = cnt.getOrDefault(a[i], 0);
            res[i] = cur;
        }
        return res;
    }
}
