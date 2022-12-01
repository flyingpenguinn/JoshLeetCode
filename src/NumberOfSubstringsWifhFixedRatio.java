import java.util.HashMap;
import java.util.Map;

public class NumberOfSubstringsWifhFixedRatio {
    public long fixedRatio(String s, long a, long b) {
        int n = s.length();
        Map<Long, Long> m = new HashMap<>();
        long c = a + b;
        m.put(b, 1L);
        long res = 0;
        long psum = 0;
        for (int i = 0; i < n; ++i) {
            int cind = (s.charAt(i) - '0');
            psum += cind;
            long lookup = c * psum - b * i;
            res += m.getOrDefault(lookup, 0L);
            m.put(lookup, m.getOrDefault(lookup, 0L) + 1L);
        }
        return res;
    }
}
