import java.util.ArrayList;
import java.util.List;

public class MinAdjSwapsToAlternateParity {
    public int minSwaps(int[] a) {
        int n = a.length;
        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (a[i] % 2 == 1) {
                odds.add(i);
            } else {
                evens.add(i);
            }
        }
        int Max = (int) (2e9);
        if (Math.abs(odds.size() - evens.size()) >= 2) {
            return -1;
        }
        int res = Max;
        if (odds.size() >= evens.size()) {
            int res1 = 0;
            int oi = 0;
            for (int i = 0; i < n; i += 2) {
                int diff = Math.abs(odds.get(oi++) - i);
                res1 += diff;
            }
            res = Math.min(res, res1);
        }
        if (evens.size() >= odds.size()) {
            int res2 = 0;
            int oi = 0;
            for (int i = 0; i < n; i += 2) {
                int diff = Math.abs(evens.get(oi++) - i);
                res2 += diff;
            }
            res = Math.min(res, res2);
        }
        return res;
    }
}
