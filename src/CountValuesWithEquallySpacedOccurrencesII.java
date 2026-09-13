import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CountValuesWithEquallySpacedOccurrencesII {
    public int countSpecialIntegers(int[] a) {
        int n = a.length;
        Set<Integer> bad = new HashSet<>();
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int ai = a[i];
            List<Integer> list = m.computeIfAbsent(ai, k -> new ArrayList<>());
            if (list.size() >= 2) {
                int delta = list.get(1) - list.get(0);
                int last = list.get(list.size() - 1);
                if (i - last != delta) {
                    bad.add(ai);
                }
            }
            list.add(i);
            m.put(ai, list);
        }
        int res = 0;
        for (int k : m.keySet()) {
            if (bad.contains(k)) {
                continue;
            }
            if (m.get(k).size() < 3) {
                continue;
            }
            ++res;
        }
        return res;
    }
}
