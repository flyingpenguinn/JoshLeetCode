import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SortArrayByIncreasingFreq {
    public int[] frequencySort(int[] a) {
        int n = a.length;
        Map<Integer, Integer> f = new HashMap<>();
        Integer[] res = new Integer[n];
        for (int i = 0; i < n; i++) {
            f.put(a[i], f.getOrDefault(a[i], 0) + 1);
            res[i] = a[i];
        }
        Arrays.sort(res, (x, y) -> {
            int fx = f.getOrDefault(x, 0);
            int fy = f.getOrDefault(y, 0);
            if (fx == fy) {
                return y.compareTo(x);
            } else {
                return Integer.compare(fx, fy);
            }
        });
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = res[i];
        }
        return r;
    }
}
