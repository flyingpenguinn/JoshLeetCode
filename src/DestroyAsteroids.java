import java.util.Map;
import java.util.TreeMap;

public class DestroyAsteroids {
    public boolean asteroidsDestroyed(int mass, int[] a) {
        long cur = mass;
        TreeMap<Long, Integer> m = new TreeMap<>();
        for (int ai : a) {
            update(m, ai, 1);
        }
        while (!m.isEmpty()) {
            Long next = m.floorKey(cur);
            if (next == null) {
                return false;
            }
            cur += next;
            update(m, next, -1);
        }
        return true;
    }

    private void update(Map<Long, Integer> m, long k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
