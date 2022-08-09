import java.util.HashMap;
import java.util.Map;

public class CountNumberOfBadPairs {
    public long countBadPairs(int[] a) {
        int n = a.length;
        Map<Integer, Long> m = new HashMap<>();
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int cur = a[i] - i;
            long cr = m.getOrDefault(cur, 0L);
            res += cr;
            m.put(cur, m.getOrDefault(cur, 0L) + 1);
        }
        long all = 1L * n * (n - 1) / 2;
        return all - res;
    }
}
