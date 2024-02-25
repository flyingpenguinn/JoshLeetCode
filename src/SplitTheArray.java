import java.util.HashMap;
import java.util.Map;

public class SplitTheArray {
    public boolean isPossibleToSplit(int[] a) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int nv = m.getOrDefault(a[i], 0) + 1;
            if (nv > 2) {
                return false;
            }
            m.put(a[i], nv);
        }
        return true;
    }
}
