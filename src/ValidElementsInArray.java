import java.util.ArrayList;
import java.util.List;

public class ValidElementsInArray {
    public List<Integer> findValidElements(int[] a) {
        int n = a.length;
        int[] maxright = new int[n];
        maxright[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            maxright[i] = Math.max(maxright[i + 1], a[i]);
        }
        int maxleft = a[0];
        List<Integer> res = new ArrayList<>();
        res.add(a[0]);
        for (int i = 1; i < n - 1; ++i) {
            if (a[i] > maxleft || a[i] > maxright[i + 1]) {
                res.add(a[i]);
            }
            maxleft = Math.max(maxleft, a[i]);
        }
        if (n > 1) {
            res.add(a[n - 1]);
        }
        return res;
    }
}
