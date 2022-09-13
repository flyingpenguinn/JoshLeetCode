import java.util.HashMap;
import java.util.Map;

public class MostFrequentEvevnElement {
    public int mostFrequentEven(int[] a) {
        Map<Integer, Integer> m = new HashMap<>();
        int n = a.length;
        int max = 0;
        int res = -1;
        for (int i = 0; i < n; ++i) {
            if (a[i] % 2 == 0) {
                int nv = m.getOrDefault(a[i], 0) + 1;

                if (nv > max) {
                    max = nv;
                    res = a[i];
                } else if (nv == max && a[i] < res) {
                    res = a[i];
                }
                m.put(a[i], m.getOrDefault(a[i],0)+1);
            }
        }
        return res;
    }
}
