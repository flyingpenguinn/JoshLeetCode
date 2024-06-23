import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class MinAvgOfSmallestAndLargestElements {
    public double minimumAverage(int[] a) {
        int n = a.length;
        TreeSet<Double> set = new TreeSet<>();
        TreeMap<Integer, Integer> ms = new TreeMap<>();

        for (int i = 0; i < n; ++i) {
            update(ms, a[i], 1);
        }

        int times = n / 2;

        while (times > 0) {
            int min = ms.firstKey();
            int max = ms.lastKey();
            set.add((0.0 + min + max) / 2.0);
            update(ms, min, -1);
            update(ms, max, -1);
            --times;
        }

        return set.first();
    }

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
