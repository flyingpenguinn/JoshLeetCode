import java.util.ArrayList;
import java.util.List;

public class RotateNonNegativeElements {
    public int[] rotateElements(int[] a, int k) {
        int n = a.length;
        List<Integer> posi = new ArrayList<>();
        List<Integer> negi = new ArrayList<>();
        List<Integer> posn = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (a[i] >= 0) {
                posi.add(i);
                posn.add(a[i]);
            } else {
                negi.add(i);
            }
        }
        if (posn.isEmpty()) {
            return a;
        }
        List<Integer> nposn = new ArrayList<>();
        k %= posn.size();
        for (int i = k; i < posn.size(); ++i) {
            nposn.add(posn.get(i));
        }
        for (int i = 0; i < k; ++i) {
            nposn.add(posn.get(i));
        }
        int[] res = new int[n];
        for (int i = 0; i < posi.size(); ++i) {
            int index = posi.get(i);
            int value = nposn.get(i);
            res[index] = value;
        }
        for (int ni : negi) {
            res[ni] = a[ni];
        }
        return res;
    }
}
