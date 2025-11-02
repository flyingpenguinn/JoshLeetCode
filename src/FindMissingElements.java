import java.util.*;

public class FindMissingElements {
    public List<Integer> findMissingElements(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int start = a[0];
        int end = a[n - 1];
        Set<Integer> seen = new HashSet<>();
        for (int ai : a) {
            seen.add(ai);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = start; i <= end; ++i) {
            if (!seen.contains(i)) {
                res.add(i);
            }
        }
        return res;
    }
}
