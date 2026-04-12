package contest;

import java.util.Map;

public class Solution2 {
    private int Max = (int) 1e9;
    private int Min = -Max;
    private long Mod = (long) (1e9+7);

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
