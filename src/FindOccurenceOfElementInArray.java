import java.util.ArrayList;
import java.util.List;

public class FindOccurenceOfElementInArray {
    public int[] occurrencesOfElement(int[] a, int[] qs, int x) {
        List<Integer> list = new ArrayList<>();
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            if (a[i] == x) {
                list.add(i);
            }
        }
        int[] res = new int[qs.length];
        for (int i = 0; i < qs.length; ++i) {
            int idx = qs[i] - 1;
            if (idx >= list.size()) {
                res[i] = -1;
            } else {
                res[i] = list.get(idx);
            }
        }
        return res;
    }
}
