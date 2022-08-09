import java.util.HashSet;
import java.util.Set;

public class MakeArrayZeroBySubtracting {
    public int minimumOperations(int[] a) {
        int n = a.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            if (a[i] != 0) {
                set.add(a[i]);
            }
        }
        return set.size();
    }
}
