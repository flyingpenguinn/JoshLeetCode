import java.util.HashMap;
import java.util.Map;

public class ReplaceElementsInArray {
    private Map<Integer, Integer> m = new HashMap<>();

    public int[] arrayChange(int[] a, int[][] operations) {
        int n = a.length;
        for (int i = 0; i < a.length; ++i) {
            m.put(a[i], i);
        }
        for (int[] op : operations) {
            int v1 = op[0];
            int v2 = op[1];
            int i = m.get(v1);
            a[i] = v2;
            m.put(v2, i);
        }
        return a;
    }
}
