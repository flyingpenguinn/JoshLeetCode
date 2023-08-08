import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinSecondsToEqualizeCircularArray {
    public int minimumSeconds(List<Integer> a) {
        int n = a.size();
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int ai = a.get(i);
            m.computeIfAbsent(ai, k -> new ArrayList<>()).add(i);
        }
        int res = n - 1;
        for (int k : m.keySet()) {
            List<Integer> as = m.get(k);
            as.add(as.get(0) + n);
            int cur = 0;
            int maxgap = 0;
            for (int i = 0; i + 1 < as.size(); ++i) {
                int diff = (as.get(i + 1) - as.get(i)) / 2;
                maxgap = Math.max(maxgap, diff);
            }
            res = Math.min(res, maxgap);
        }
        return res;
    }
}
