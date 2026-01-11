import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxBitwiseAndAfterAddOperations {
    private long cost(int x, int mask) {
        long y = (long) x;
        for (int b = 30; b >= 0; --b) {
            long bit = 1L << b;
            if ((mask & bit) != 0L) {
                if ((y & bit) == 0L) {
                    long low = y & (bit - 1L);
                    y += bit - low;
                }
            }
        }
        return y - (long) x;
    }

    public int maximumAND(int[] a, int k, int m) {
        int n = a.length;
        int res = 0;
        for (int j = 30; j >= 0; --j) {
            int next = res | (1 << j);
            List<Long> cs = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                long ccost = cost(a[i], next);
                cs.add(ccost);
            }
            Collections.sort(cs);
            long csum = 0;
            for (int i = 0; i < m && i < cs.size(); ++i) {
                csum += cs.get(i);
            }
            if (csum <= k) {
                res = next;
            }
        }
        return res;
    }
}
