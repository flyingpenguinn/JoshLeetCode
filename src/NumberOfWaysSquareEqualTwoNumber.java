import java.util.HashMap;
import java.util.Map;

public class NumberOfWaysSquareEqualTwoNumber {
    public int numTriplets(int[] a, int[] b) {
        Map<Long, Integer> m1 = buildMap(a);
        Map<Long, Integer> m2 = buildMap(b);
        return count(m1, b) + count(m2, a);
    }

    private Map<Long, Integer> buildMap(int[] a) {
        Map<Long, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                long multi = 1L * a[i] * a[j];
                m.put(multi, m.getOrDefault(multi, 0) + 1);
            }
        }
        return m;
    }

    private int count(Map<Long, Integer> m, int[] a) {
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            long sq = 1L * a[i] * a[i];
            res += m.getOrDefault(sq, 0);
        }
        return res;
    }
}
