import java.util.HashMap;
import java.util.Map;

public class PartitionArrayIntoDistinctGroups {
    public boolean partitionArray(int[] a, int k) {
        int n = a.length;
        if (n % k != 0) {
            return false;
        }
        int groups = n / k;
        Map<Integer, Integer> m = new HashMap<>();
        for (int ai : a) {
            m.put(ai, m.getOrDefault(ai, 0) + 1);
        }
        for (int key : m.keySet()) {
            int v = m.get(key);
            if (v > groups) {
                return false;
            }
        }
        return true;
    }
}
