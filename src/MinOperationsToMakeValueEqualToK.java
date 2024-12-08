import java.util.HashMap;
import java.util.Map;

public class MinOperationsToMakeValueEqualToK {
    public int minOperations(int[] a, int k) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        for (int ai : a) {
            if (ai < k) {
                return -1;
            }
            if (ai == k) {
                continue;
            }
            m.put(ai, m.getOrDefault(ai, 0) + 1);
        }
        return m.size();
    }
}
