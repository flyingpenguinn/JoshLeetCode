import java.util.HashMap;
import java.util.Map;

public class NumberOfGoodPairsIandII {
    public long numberOfPairs(int[] a, int[] b, int k) {
        int an = a.length;
        int bn = b.length;
        Map<Integer, Integer> bc = new HashMap<>();
        for (int i = 0; i < bn; ++i) {
            int v = b[i] * k;
            bc.put(v, bc.getOrDefault(v, 0) + 1);
        }
        long res = 0;
        for (int i = 0; i < an; ++i) {
            int v = a[i];
            for (int j = 1; j * j <= v; ++j) {
                if (v % j != 0) {
                    continue;
                }
                int cv = v / j;
                res += bc.getOrDefault(cv, 0);
                int other = v / cv;
                if (other != cv) {
                    res += bc.getOrDefault(other, 0);
                }
            }
        }
        return res;
    }
}
