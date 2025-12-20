import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinOperationsToHaveDistinctElements {
    public int minOperations(int[] a) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        for (int ai : a) {
            m.put(ai, m.getOrDefault(ai, 0) + 1);
        }
        Set<Integer> dupes = new HashSet<>();
        for (int k : m.keySet()) {
            if (m.get(k) > 1) {
                dupes.add(k);
            }
        }
        if (dupes.isEmpty()) {
            return 0;
        }
        int ops = 0;
        for (int i = 0; i < n; i += 3) {
            ++ops;
            for (int j = i; j < i + 3 && j < n; ++j) {
                int cv = m.getOrDefault(a[j], 0);
                int nv = cv - 1;
                if (nv == 1 && dupes.contains(a[j])) {
                    dupes.remove(a[j]);
                }
                if (nv > 0) {
                    m.put(a[j], nv);
                } else {
                    m.remove(a[j]);
                }
            }
            if (dupes.isEmpty()) {
                break;
            }
        }
        return ops;
    }
}
