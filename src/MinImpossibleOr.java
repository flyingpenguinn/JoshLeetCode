import java.util.HashSet;
import java.util.Set;

public class MinImpossibleOr {
    // must be a pow2 that is impossible
    public int minImpossibleOR(int[] a) {
        int[] accu = new int[32];
        Set<Integer> seen = new HashSet<>();
        int n = a.length;
        for (int ai : a) {
            seen.add(ai);

        }
        for (int j = 0; j < 32; ++j) {
            int cur = (1 << j);
            if (!seen.contains((cur))) {
                return cur;
            }
        }
        return -1;
    }
}
