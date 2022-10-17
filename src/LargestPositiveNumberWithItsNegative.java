import java.util.HashSet;
import java.util.Set;

public class LargestPositiveNumberWithItsNegative {
    public int findMaxK(int[] a) {
        Set<Integer> set = new HashSet<>();
        for (int ai : a) {
            set.add(ai);
        }
        int max = -1;
        for (int ai : a) {
            if (ai > 0 && set.contains(-ai)) {
                max = Math.max(max, ai);
            }
        }
        return max;
    }
}
