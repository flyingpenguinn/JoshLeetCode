import java.util.HashMap;
import java.util.Map;

public class MinIndexSumOfCommonElements {
    public int minimumSum(int[] a, int[] b) {
        Map<Integer, Integer> ma = new HashMap<>();
        Map<Integer, Integer> mb = new HashMap<>();
        int n = a.length;
        for (int i = n - 1; i >= 0; --i) {
            ma.put(a[i], i);
            mb.put(b[i], i);
        }
        int Max = (int) 1e9;
        int res = Max;
        for (int ka : ma.keySet()) {
            int va = ma.get(ka);
            if (mb.containsKey(ka)) {
                int cur = va + mb.get(ka);
                res = Math.min(res, cur);
            }
        }
        return res >= Max ? -1 : res;
    }
}
