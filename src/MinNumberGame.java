import java.util.Map;
import java.util.TreeMap;

public class MinNumberGame {
    // can also use pq
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int[] numberGame(int[] a) {
        int n = a.length;
        int[] res = new int[n];
        int ri = 0;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int ai : a) {
            update(tm, ai, 1);
        }
        while (!tm.isEmpty()) {
            int min1 = tm.firstKey();
            update(tm, min1, -1);
            int min2 = tm.firstKey();
            update(tm, min2, -1);
            res[ri++] = min2;
            res[ri++] = min1;
        }
        return res;
    }
}
