import java.util.HashMap;
import java.util.Map;

public class TupleWithSameProduct {
    // two pairs generate 8 different combinations...
    public int tupleSameProduct(int[] a) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int p = a[i] * a[j];
                int cur = m.getOrDefault(p, 0);
                m.put(p, cur + 1);
                res += 8 * cur;
                // each p gives 8: ab, cd, and abcd sequence: ab first or cd first
            }
        }
        return res;


    }
}
