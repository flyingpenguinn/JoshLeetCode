import java.util.HashMap;
import java.util.Map;

public class TwoSneakyNumbersOfDigitVile {
    public int[] getSneakyNumbers(int[] a) {
        Map<Integer, Integer> m = new HashMap<>();
        int n = a.length;
        for (int ai : a) {
            m.put(ai, m.getOrDefault(ai, 0) + 1);
        }
        int[] res = new int[2];
        int ri = 0;
        for (int k : m.keySet()) {
            if (m.get(k) == 2) {
                res[ri++] = k;
            }
        }
        return res;
    }
}
