import java.util.HashMap;
import java.util.Map;

public class CountSubarrayWhereMaxAppearAtlestKTimes {
    // can also do sliding window, freq <= k-1 between j...i then anything [0, j-1] will be good.  note here a[j] must = max
    public long countSubarrays(int[] a, int k) {
        int max = 0;
        for (int ai : a) {
            max = Math.max(max, ai);
        }
        int cmax = 0;
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            if (a[i] == max) {
                ++cmax;
            }
            m.putIfAbsent(cmax, i);
        }
        //  System.out.println(m);
        int cur = 0;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] == max) {
                ++cur;
            }
            int wanted = cur - k + 1;
            if (wanted >= 1) {
                int last = m.get(wanted);
                res += last + 1;
            }
        }
        return res;
    }
}
