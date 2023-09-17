import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountPairsOfPointIWithDistK {
    private long mask = (long) 1e8;

    public int countPairs(List<List<Integer>> a, int k) {
        int n = a.size();
        Map<Long, Integer> m = new HashMap<>();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int x = a.get(i).get(0);
            int y = a.get(i).get(1);
            for (int k1 = 0; k1 <= 100; ++k1) {
                int k2 = k - k1;
                int fx = x ^ k1;
                int fy = y ^ k2;
                long key = fx * mask + fy;
                int cur = m.getOrDefault(key, 0);
                res += cur;
            }
            long code = x * mask + y;
            m.put(code, m.getOrDefault(code, 0) + 1);
        }
        return res;
    }
}
