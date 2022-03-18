import java.util.ArrayList;
import java.util.List;

public class FindAllKDistantIndexInArray {
    public List<Integer> findKDistantIndices(int[] a, int key, int k) {
        int n = a.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (a[i] != key) {
                continue;
            }
            int base = res.isEmpty() ? 0 : res.get(res.size() - 1) + 1;
            int start = Math.max(base, i - k);
            int end = Math.min(n - 1, i + k);
            for (int p = start; p <= end; ++p) {
                res.add(p);
            }
        }
        return res;
    }
}
