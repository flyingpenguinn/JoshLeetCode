import java.util.HashSet;
import java.util.Set;

public class DistinctDifferenceArray {
    public int[] distinctDifferenceArray(int[] a) {
        int n = a.length;
        int[] right = new int[n];
        Set<Integer> set = new HashSet<>();
        for (int i = n - 1; i >= 0; --i) {
            right[i] = set.size();
            set.add(a[i]);
        }
        set.clear();
        int[] diff = new int[n];
        for (int i = 0; i < n; ++i) {
            set.add(a[i]);
            int left = set.size();
            diff[i] = left - right[i];
        }
        return diff;
    }
}
