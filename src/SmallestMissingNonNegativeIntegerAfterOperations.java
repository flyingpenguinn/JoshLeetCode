import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SmallestMissingNonNegativeIntegerAfterOperations {
    // each number can grow up to raw then raw+t, raw+t+t... etc
    public int findSmallestInteger(int[] a, int t) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            int raw = (a[i] % t + t) % t;
            int dt = m.getOrDefault(raw, 0);
            int cur = raw + dt;
            seen.add(cur);
            m.put(raw, dt + t);
        }
        int i = 0;
        for (; i <= n; ++i) {
            if (!seen.contains(i)) {
                break;
            }
        }
        return i;
    }
}
