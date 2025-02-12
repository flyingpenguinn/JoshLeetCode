import java.util.HashMap;
import java.util.Map;

public class AssignElementsToGroupsWithConstraints {
    private int Min = (int) -1e9;
    private int Max = (int) 1e9;

    public int[] assignElements(int[] gs, int[] es) {
        int gn = gs.length;
        int en = es.length;
        Map<Integer, Integer> em = new HashMap<>();
        for (int i = 0; i < en; ++i) {
            em.putIfAbsent(es[i], i);
        }
        Map<Integer, Integer> prev = new HashMap<>();
        int[] res = new int[gn];
        for (int i = 0; i < gn; ++i) {
            int v = gs[i];
            if (prev.containsKey(v)) {
                res[i] = prev.get(v);
                continue;
            }
            int index = Max;
            for (int p = 1; p * p <= v; ++p) {
                if (v % p != 0) {
                    continue;
                }
                if (em.containsKey(p)) {
                    index = Math.min(index, em.get(p));
                }
                int other = v / p;
                if (em.containsKey(other)) {
                    index = Math.min(index, em.get(other));
                }
            }
            if (index >= Max) {
                res[i] = -1;
            } else {
                res[i] = index;
            }
        }
        return res;
    }

}
