import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestSquareStreak {
    public int longestSquareStreak(int[] a) {
        int n = a.length;
        Map<Integer, Integer> dp = new HashMap<>();
        Arrays.sort(a);
        int res = -1;
        for (int i = 0; i < n; ++i) {
            int sqrt = (int) Math.sqrt(a[i]);
            int nv = 1;
            if (sqrt * sqrt == a[i]) {
                if (dp.containsKey(sqrt)) {
                    nv = dp.get(sqrt) + 1;
                }
                if (nv >= 2) {
                    res = Math.max(res, nv);
                }
            }
            dp.put(a[i], nv);
        }
        return res;
    }
}
