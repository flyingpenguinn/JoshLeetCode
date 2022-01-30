import java.util.HashMap;
import java.util.Map;

public class KeepMultiplyingFoundNumber {
    public int findFinalValue(int[] a, int original) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int ai : a) {
            update(m, ai, 1);
        }
        int t = original;
        while (true) {
            if (m.containsKey(t)) {
                update(m, t, -1);
                t *= 2;
            } else {
                break;
            }
        }
        return t;
    }

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
