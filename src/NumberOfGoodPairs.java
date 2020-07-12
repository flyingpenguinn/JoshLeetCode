import java.util.HashMap;
import java.util.Map;

public class NumberOfGoodPairs {
    public int numIdenticalPairs(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int r = 0;
        for (int i = 0; i < a.length; i++) {
            r += map.getOrDefault(a[i], 0);
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }
        return r;
    }
}
