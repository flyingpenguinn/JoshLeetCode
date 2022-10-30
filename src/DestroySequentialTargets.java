import java.util.HashMap;
import java.util.Map;

public class DestroySequentialTargets {
    public int destroyTargets(int[] a, int space) {
        int n = a.length;

        Map<Integer, Integer> des = new HashMap<>();
        Map<Integer, Integer> rep = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int mod = a[i] % space;
            des.put(mod, des.getOrDefault(mod, 0) + 1);
            if (!rep.containsKey(mod) || a[i] < rep.get(mod)) {
                rep.put(mod, a[i]);
            }
        }
        int maxsize = 0;
        int res = (int) 2e9;
        for (int k : des.keySet()) {
            int count = des.get(k);
            if (count > maxsize) {
                maxsize = count;
                res = rep.get(k);
            } else if (count == maxsize) {
                res = Math.min(res, rep.get(k));
            }
        }
        return res;
    }
}
