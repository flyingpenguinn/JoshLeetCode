import java.util.HashMap;
import java.util.Map;

public class MostFrequentNumberFollowingKeyInArray {
    public int mostFrequent(int[] a, int key) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        int max = 0;
        int res = 0;
        for (int i = 0; i <= n - 2; ++i) {
            if (a[i] == key) {
                int nv = m.getOrDefault(a[i + 1], 0) + 1;
                if (nv > max) {
                    max = nv;
                    res = a[i + 1];
                }
                m.put(a[i + 1], nv);
            }
        }
        return res;
    }
}
