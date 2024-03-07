import java.util.ArrayList;
import java.util.List;

public class DistributeElementsIntoTwoArraysI {
    public int[] resultArray(int[] a) {
        int n = a.length;
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        l1.add(a[0]);
        l2.add(a[1]);
        for (int i = 2; i < n; ++i) {
            if (l1.get(l1.size() - 1) > l2.get(l2.size() - 1)) {
                l1.add(a[i]);
            } else {
                l2.add(a[i]);
            }
        }
        int[] res = new int[n];

        int ri = 0;
        for (int i = 0; i < l1.size(); ++i) {
            res[ri++] = l1.get(i);
        }
        for (int i = 0; i < l2.size(); ++i) {
            res[ri++] = l2.get(i);
        }
        return res;
    }
}
