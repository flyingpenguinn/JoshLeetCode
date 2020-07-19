import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindValueOfMysteriousFunctionCloseToTarget {
    // use the feature that & will only make numbers smaller...or at least eq
    // generally two different numbers & together we clear up one bit of them
    private int Max = 1000000000;

    public int closestToTarget(int[] a, int t) {
        List<Integer> filtered = new ArrayList<>();
        int i = 0;
        int n = a.length;
        while (i < n) {
            filtered.add(a[i]);
            int j = i;
            while (j < n && a[j] == a[i]) {
                j++;
            }
            i = j;
        }
        int closest = Max;
        for (i = 0; i < filtered.size(); i++) {
            int ans = filtered.get(i);
            closest = Math.min(closest, Math.abs(ans - t));
            for (int j = i; j < filtered.size(); j++) {
                ans &= filtered.get(j);
                closest = Math.min(closest, Math.abs(ans - t));
                if (closest == 0) {
                    return 0;
                }
                if (ans < t) {
                    // current one is the biggest possible. later ones will only get it smaller
                    break;
                }
            }
            if (ans > t) { // current answer is the smallest possible. later ones will only get bigger
                break;
            }
        }
        return closest;
    }
}

class FindValueOfMysteriousAnotherWay {
    // similar to bitwise or on subarray problem. in this and that problem, but values in cur will never be more than 32 because each
    // new number will just negate one bit of the earlier one
    public int closestToTarget(int[] a, int t) {
        int r = 1000000000;
        Set<Integer> all = new HashSet<>();
        // cur has size <=32 as each number is different. cur means the possible numbers ending at each i-1 at each loop start
        Set<Integer> cur = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            Set<Integer> ncur = new HashSet<>();
            ncur.add(a[i]);
            r = Math.min(r, Math.abs(a[i] - t));
            for (int c : cur) {
                int v = c & a[i];
                r = Math.min(r, Math.abs(v - t));
                ncur.add(c & a[i]);
            }
            all.addAll(ncur);
            cur = ncur;
        }
        return r;
    }
}