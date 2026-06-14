import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FreqBalanceSubarray {
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int getLength(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            Map<Integer, Integer> m = new HashMap<>();
            TreeMap<Integer, Integer> cm = new TreeMap<>();
            for (int j = i; j < n; ++j) {
                int v = a[j];
                int oldc = m.getOrDefault(v, 0);
                update(m, v, 1);
                int newc = oldc + 1;
                update(cm, oldc, -1);
                update(cm, newc, 1);
                int clen = j - i + 1;
                if (m.size() == 1) {
                    res = Math.max(res, clen);
                } else {
                    if (cm.size() == 2) {

                        int maxc = cm.lastKey();
                        int nexc = cm.lowerKey(maxc);
                        if (maxc == 2 * nexc) {
                            res = Math.max(res, clen);
                        }
                    }
                }
            }
        }
        return res;
    }
}
