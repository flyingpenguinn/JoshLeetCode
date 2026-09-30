import java.util.HashMap;
import java.util.Map;

public class MaximumEqualAdjacentPairsAfterOneReplacement {
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int maxEqualAdjacentPairs(int[] a) {
        int n = a.length;
        int same = 0;
        Map<Integer, Map<Integer, Integer>> m = new HashMap<>();
        for (int i = 0; i + 1 < n; ++i) {
            if (a[i] == a[i + 1]) {
                ++same;
                continue;
            }
            int v1 = a[i];
            int v2 = a[i + 1];
            Map<Integer, Integer> cm1 = m.getOrDefault(v1, new HashMap<>());
            update(cm1, v2, 1);
            m.put(v1, cm1);

            Map<Integer, Integer> cm2 = m.getOrDefault(v2, new HashMap<>());
            update(cm2, v1, 1);
            m.put(v2, cm2);
        }
        int maxc = 0;
        for(int k1: m.keySet()){
            for(int k2: m.get(k1).keySet()){
                int v1 = m.get(k1).get(k2);
                maxc = Math.max(maxc, v1);
            }
        }
        return same + maxc;
    }
}
