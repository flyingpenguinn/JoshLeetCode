import java.util.HashMap;
import java.util.Map;

public class CheckArrayFormationThroughConcatenation {
    // because it's all different, use ps[i][0] as indicator
    public boolean canFormArray(int[] a, int[][] ps) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int j = 0; j < ps.length; j++) {
            m.put(ps[j][0], j);
        }
        int i = 0;
        while (i < a.length) {
            if (!m.containsKey(a[i])) {
                return false;
            }
            int j = m.get(a[i]);
            int k = 0;
            while (i < a.length && k < ps[j].length && a[i] == ps[j][k]) {
                i++;
                k++;
            }
        }
        return true;
    }
}
