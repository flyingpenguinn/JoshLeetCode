import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDifferencesOfTwoArrays {
    public List<List<Integer>> findDifference(int[] a, int[] b) {
        Set<Integer> sb = new HashSet<>();
        for (int bi : b) {
            sb.add(bi);
        }
        Set<Integer> sa = new HashSet<>();
        for (int ai : a) {
            sa.add(ai);
        }
        Set<Integer> ra = new HashSet<>();
        for (int ai : a) {
            if (!sb.contains(ai)) {
                ra.add(ai);
            }
        }
        Set<Integer> rb = new HashSet<>();
        for (int bi : b) {
            if (!sa.contains(bi)) {
                rb.add(bi);
            }
        }
        return List.of(new ArrayList<>(ra), new ArrayList<>(rb));
    }
}
