import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostStudentsOnASingleBeach {
    public int maxStudentsOnBench(int[][] a) {
        int n = a.length;
        Map<Integer, Set<Integer>> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int s = a[i][0];
            int b = a[i][1];
            m.computeIfAbsent(b, k -> new HashSet<>()).add(s);
        }
        int res = 0;
        for (int k : m.keySet()) {
            res = Math.max(res, m.get(k).size());
        }
        return res;
    }
}
