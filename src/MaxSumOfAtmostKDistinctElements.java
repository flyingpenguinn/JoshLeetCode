import java.util.*;

public class MaxSumOfAtmostKDistinctElements {
    public int[] maxKDistinct(int[] a, int k) {
        int n = a.length;
        Arrays.sort(a);
        Set<Integer> seen = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        for (int i = n - 1; i >= 0 && res.size() < k; --i) {
            if (seen.contains(a[i])) {
                continue;
            }
            seen.add(a[i]);
            res.add(a[i]);
        }
        int[] rres = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            rres[i] = res.get(i);
        }
        return rres;
    }
}
