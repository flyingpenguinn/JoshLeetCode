import java.util.HashMap;
import java.util.Map;

public class MinTotalCostToMakeArraysUnequal {
    // find the initial bad set s. then try to add more elements to it if there is a dominant element
    // the 1/2 rule for swapping
    private int update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
            return 0;
        } else {
            m.put(k, nv);
            return nv;
        }
    }

    public long minimumTotalCost(int[] a, int[] b) {
        int n = a.length;
        Map<Integer, Integer> f = new HashMap<>();
        int diffs = 0;
        int maxf = 0;
        int maxfkey = 0;
        long sum = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] == b[i]) {
                int nv = update(f, a[i], 1);
                if (nv > maxf) {
                    maxf = nv;
                    maxfkey = a[i];
                }
                sum += i;
                ++diffs;
            }
        }
        if (maxf * 2 <= diffs) {
            return sum;
        }
        int needed = 2 * maxf - diffs;
        for (int i = 0; i < n && needed > 0; ++i) {
            int v = a[i];
            if (a[i] == b[i]) {
                continue;
            }

            if (a[i] == maxfkey || b[i] == maxfkey) {
                continue;
            }
            --needed;
            sum += i;
        }
        if (needed == 0) {
            return sum;
        } else {
            return -1;
        }
    }
}
