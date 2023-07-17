import java.util.Map;
import java.util.TreeMap;

public class MaxBeautyAfterApplyingOperations {
    public int maximumBeauty(int[] a, int k) {
        int n = a.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < n; ++i) {
            int l = a[i] - k;
            int r = a[i] + k;
            update(tm, l, 1);
            update(tm, r + 1, -1);
        }
        //  System.out.println(tm);
        int cur = 0;
        int res = 1;
        for (int key : tm.keySet()) {
            cur += tm.get(key);
            res = Math.max(cur, res);
        }
        return res;
    }

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        m.put(k, nv);
    }
}
