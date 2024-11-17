import java.util.Map;
import java.util.TreeMap;

public class ZeroArrayTransformationI {
    public boolean isZeroArray(int[] a, int[][] qs) {
        int qn = qs.length;
        Map<Integer, Integer> tm = new TreeMap<>();
        for (int[] q : qs) {
            int s = q[0];
            int e = q[1];
            tm.put(s, tm.getOrDefault(s, 0) + 1);
            tm.put(e + 1, tm.getOrDefault(e + 1, 0) - 1);
        }
        int n = a.length;
        int csum = 0;
        for (int i = 0; i < n; ++i) {
            csum += tm.getOrDefault(i, 0);
            if (csum < a[i]) {
                return false;
            }
        }
        return true;
    }
}
