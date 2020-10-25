import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArithmeticSubarray {
    public List<Boolean> checkArithmeticSubarrays(int[] a, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            if (isarith(a, l[i], r[i])) {
                res.add(true);
            } else {
                res.add(false);
            }
        }
        return res;
    }

    private boolean isarith(int[] a, int l, int r) {
        if (r - l + 1 < 2) {
            return false;
        }
        List<Integer> cur = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            cur.add(a[i]);
        }
        Collections.sort(cur);
        int diff = cur.get(1) - cur.get(0);
        for (int i = 2; i < cur.size(); i++) {
            if (cur.get(i) - cur.get(i - 1) != diff) {
                return false;
            }
        }
        return true;
    }
}
