import java.util.HashMap;
import java.util.Map;

public class MinConsecutiveCardsToPickup {
    private int Max = (int) 1e9;

    public int minimumCardPickup(int[] a) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        int res = Max;
        for (int i = 0; i < n; ++i) {
            if (!m.containsKey(a[i])) {
                m.put(a[i], i);
            } else {
                int last = m.get(a[i]);
                int cur = i - last + 1;
                res = Math.min(res, cur);
            }
            m.put(a[i], i);
        }
        return res >= Max ? -1 : res;
    }
}
