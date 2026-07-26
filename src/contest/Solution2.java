package contest;

import java.util.Map;

public class Solution2 {

    private long Max = (long) 1e18;
    private long Min = -Max;
    private long Mod = (long) (1e9+7);
    private int[][] dirs = {{-1,0}, {1,0}, {0, -1}, {0,1}};

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
