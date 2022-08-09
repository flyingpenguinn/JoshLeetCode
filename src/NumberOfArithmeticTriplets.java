import java.util.HashSet;
import java.util.Set;

public class NumberOfArithmeticTriplets {
    public int arithmeticTriplets(int[] a, int diff) {
        int n = a.length;
        Set<Integer> lm = new HashSet<>();
        lm.add(a[0]);
        Set<Integer> rm = new HashSet<>();
        for (int i = n - 1; i >= 2; --i) {
            rm.add(a[i]);
        }
        int res = 0;
        for (int i = 1; i + 1 < n; ++i) {
            if (lm.contains(a[i] - diff) && rm.contains(a[i] + diff)) {
                ++res;
            }
            rm.remove(a[i + 1]);
            lm.add(a[i]);
        }
        return res;
    }
}
