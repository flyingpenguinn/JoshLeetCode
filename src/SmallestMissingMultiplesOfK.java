import java.util.HashSet;
import java.util.Set;

public class SmallestMissingMultiplesOfK {
    public int missingMultiple(int[] a, int k) {
        int n = a.length;
        Set<Integer> set = new HashSet<>();
        for (int value : a) {
            set.add(value);
        }
        for (int j = 1; j <= 1000; ++j) {
            if (!set.contains(j * k)) {
                return j * k;
            }
        }
        return -1;
    }
}
