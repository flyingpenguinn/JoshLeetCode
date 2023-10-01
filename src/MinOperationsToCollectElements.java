import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinOperationsToCollectElements {
    public int minOperations(List<Integer> a, int k) {
        int n = a.size();
        Set<Integer> needed = new HashSet<>();
        for (int i = 1; i <= k; ++i) {
            needed.add(i);
        }
        int steps = 0;
        for (int i = n - 1; i >= 0; --i) {
            ++steps;
            needed.remove(a.get(i));
            if (needed.isEmpty()) {
                return steps;
            }
        }
        return -1;
    }
}
