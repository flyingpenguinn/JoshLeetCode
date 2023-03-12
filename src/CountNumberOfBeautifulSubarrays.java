import java.util.HashMap;
import java.util.Map;

public class CountNumberOfBeautifulSubarrays {
    public long beautifulSubarrays(int[] a) {
        int n = a.length;
        int[] bits = new int[32];
        Map<Integer, Long> m = new HashMap<>();
        m.put(0, 1L);
        long res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 32; ++j) {
                int cbit = ((a[i] >> j) & 1);
                bits[j] += cbit;
            }
            int cur = 0;
            for (int j = 0; j < 32; ++j) {
                if (bits[j] % 2 == 1) {
                    cur |= (1 << j);
                }
            }
            Long prev = m.getOrDefault(cur, 0L);
            res += prev;
            m.put(cur, prev + 1);
        }
        return res;
    }
}
