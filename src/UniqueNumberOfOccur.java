import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueNumberOfOccur {
    public boolean uniqueOccurrences(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int ai : a) {
            map.put(ai, map.getOrDefault(ai, 0) + 1);
        }
        Set<Integer> seen = new HashSet<>();
        for (int k : map.keySet()) {
            int occ = map.get(k);
            if (seen.contains(occ)) {
                return false;
            }
            seen.add(occ);
        }
        return true;
    }
}
