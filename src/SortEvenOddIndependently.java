import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortEvenOddIndependently {
    public int[] sortEvenOdd(int[] a) {
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            if (i % 2 == 0) {
                evens.add(a[i]);
            } else {
                odds.add(a[i]);
            }
        }
        Collections.sort(evens);
        Collections.sort(odds, Collections.reverseOrder());
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % 2 == 0) {
                res[i] = evens.get(i / 2);
            } else {
                res[i] = odds.get((i - 1) / 2);
            }
        }
        return res;
    }
}
