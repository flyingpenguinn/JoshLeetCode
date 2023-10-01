import java.util.HashMap;
import java.util.Map;

public class MinOperationsToMakeArrayEmpty {
    public int minOperations(int[] a) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int ai : a) {
            m.put(ai, m.getOrDefault(ai, 0) + 1);
        }
        int res = 0;
        for (int k : m.keySet()) {
            int v = m.get(k);
            if (v % 3 == 0) {
                res += v / 3;
            } else if (v % 3 == 2) {
                res += v / 3 + 1;
            } else {
                if (v == 1) {
                    return -1;
                }
                res += (v - 4) / 3 + 2;
            }
        }
        return res;
    }
}
