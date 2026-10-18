import java.util.HashMap;
import java.util.Map;

public class MinTotalCostToMakeArraysUnequal {
    // first see if the bad ones can be paired internally through majority rule
    // then anything not possible we get outside help
    public long minimumTotalCost(int[] a, int[] b) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        int maxbad = 0;
        int maxv = -1;
        long badsum = 0;
        int badsize = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] == b[i]) {
                int nv = m.getOrDefault(a[i], 0) + 1;
                m.put(a[i], nv);
                if (nv > maxbad) {
                    maxbad = nv;
                    maxv = a[i];
                }
                badsum += i;
                ++badsize;
            }
        }
        long res = badsum;
        if (2 * maxbad <= badsize) {
            return res;
        }
        long toborrow = 2L * maxbad - badsize;

        for (int i = 0; toborrow > 0 && i < n; ++i) {
            if (a[i] != b[i] && a[i] != maxv && b[i] != maxv) {
                res += i;
                --toborrow;
            }
        }
        if (toborrow > 0) {
            return -1;
        }
        return res;
    }
}
