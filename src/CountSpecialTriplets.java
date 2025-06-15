import java.util.HashMap;
import java.util.Map;

public class CountSpecialTriplets {
    private long Max = (long) 1e9;
    private long Min = -Max;
    private long Mod = (long) (1e9 + 7);

    private void update(Map<Integer, Long> m, int k, long d) {
        long nv = m.getOrDefault(k, 0L) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int specialTriplets(int[] a) {
        int n = a.length;
        Map<Integer, Long> right = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            update(right, a[i], 1);
        }
        Map<Integer, Long> left = new HashMap<>();
        long res = 0;
        for (int i = 0; i < n; ++i) {
            update(right, a[i], -1);
            long lc = left.getOrDefault(a[i] * 2, 0L);
            long rc = right.getOrDefault(a[i] * 2, 0L);
            long cur = lc * rc;
            cur %= Mod;
            res += cur;
            res %= Mod;
            update(left, a[i], 1);
        }
        return (int) res;
    }
}
