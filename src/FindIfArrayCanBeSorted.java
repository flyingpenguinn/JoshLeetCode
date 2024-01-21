import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindIfArrayCanBeSorted {
    public boolean canSortArray(int[] a) {
        int n = a.length;
        int i = 0;
        List<Integer> res = new ArrayList<>();
        while (i < n) {
            int j = i;
            List<Integer> l = new ArrayList<>();
            while (j < n && Integer.bitCount(a[j]) == Integer.bitCount(a[i])) {
                l.add(a[j]);
                ++j;
            }
            Collections.sort(l);
            res.addAll(l);
            i = j;
        }
        for (i = 1; i < n; ++i) {
            if (res.get(i) < res.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
