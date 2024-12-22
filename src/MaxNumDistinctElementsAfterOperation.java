import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaxNumDistinctElementsAfterOperation {
    public int maxDistinctElements(int[] a, int k) {
        int n = a.length;
        Arrays.sort(a);
        a[0] -= k;
        for (int i = 1; i < n; ++i) {
            int should = Math.max(a[i] - k, a[i - 1] + 1);
            if (should <= a[i]) {
                if (a[i] - should <= k) {
                    a[i] = should;
                } else {
                    a[i] -= k;
                }
            } else {
                if (a[i] + k >= should) {
                    a[i] = should;
                } else {
                    a[i] += k;
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int ai : a) {
            set.add(ai);
        }
        return set.size();
    }
}
