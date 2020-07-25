import java.util.HashMap;
import java.util.Map;

public class NumberOfSubarrayWithOddSum {
    public int numOfSubarrays(int[] a) {
        Map<Integer, Long> m = new HashMap<>();
        m.put(0, 1L);
        int sum = 0;
        long r = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (sum % 2 == 0) {
                r += m.getOrDefault(1, 0L);
                m.put(0, m.getOrDefault(0, 0L) + 1);
            } else {
                r += m.getOrDefault(0, 0L);
                m.put(1, m.getOrDefault(1, 0L) + 1);
            }
        }
        return (int) (r % 1000_000_007);
    }
}
