import java.util.*;

public class RearrangingFruits {
    // note we can always use the min value as buffer
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int minx = (int) 1e9;
        for (int c : basket1) {
            cnt.put(c, cnt.getOrDefault(c, 0) + 1);
            minx = Math.min(c, minx);
        }
        for (int c : basket2) {
            cnt.put(c, cnt.getOrDefault(c, 0) - 1);
            minx = Math.min(c, minx);
        }
        List<Integer> last = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
            int v = e.getValue();
            if (v % 2 != 0)
                return -1;
            for (int i = 0; i < Math.abs(v) / 2; ++i)
                last.add(e.getKey());
        }
        Collections.sort(last);
        long res = 0;
        for (int i = 0; i < last.size() / 2; ++i) {
            res += Math.min(last.get(i), 2 * minx);
        }
        return res;
    }
}
