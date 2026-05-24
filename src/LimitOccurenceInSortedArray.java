import java.util.ArrayList;
import java.util.List;

public class LimitOccurenceInSortedArray {
    public int[] limitOccurrences(int[] a, int k) {
        int n = a.length;
        List<Integer> l = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && a[i] == a[j]) {
                ++j;
            }
            int len = Math.min(j - i, k);
            for (int p = 0; p < len; ++p) {
                l.add(a[i]);
            }
            i = j;
        }
        int[] rres = new int[l.size()];
        for (i = 0; i < l.size(); ++i) {
            rres[i] = l.get(i);
        }
        return rres;
    }
}
