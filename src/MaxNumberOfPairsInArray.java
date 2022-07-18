import java.util.HashMap;
import java.util.Map;

public class MaxNumberOfPairsInArray {
    public int[] numberOfPairs(int[] a) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int ai : a) {
            m.put(ai, m.getOrDefault(ai, 0) + 1);
        }
        int pairs = 0;
        int left = 0;
        for (int k : m.keySet()) {
            int v = m.get(k);
            pairs += v / 2;
            left += v % 2;
        }
        return new int[]{pairs, left};
    }
}
