import java.util.HashSet;
import java.util.Set;

public class NumberOfCenteredSubarrays {
    public int centeredSubarrays(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int sum = 0;
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < n; ++j) {
                set.add(a[j]);
                sum += a[j];
                if (set.contains(sum)) {
                    ++res;
                }
            }
        }
        return res;
    }
}
