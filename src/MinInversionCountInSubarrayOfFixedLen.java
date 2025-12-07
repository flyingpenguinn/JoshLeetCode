import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinInversionCountInSubarrayOfFixedLen {
    private long[] bit;

    private void u(int i, long d) {
        while (i < bit.length) {
            bit[i] += d;
            i += (i & -i);
        }
    }

    private long q(int i) {
        long res = 0;
        while (i > 0) {
            res += bit[i];
            i -= (i & -i);
        }
        return res;
    }

    public long minInversionCount(int[] a, int k) {
        int n = a.length;
        int[] ca = Arrays.copyOf(a, n);
        Arrays.sort(ca);
        int rank = 1;
        Map<Integer, Integer> m = new HashMap<>();
        int i = 0;
        while (i < n) {
            m.put(ca[i], rank);
            int j = i;
            while (j < n && ca[i] == ca[j]) {
                ++j;
            }
            ++rank;
            i = j;
        }
        bit = new long[rank + 1];
        long cur = 0;
        long res = Long.MAX_VALUE;
        for (i = 0; i < k - 1; ++i) {
            int ri = m.get(a[i]);
            long good = q(ri);
            long rem = i - good;
            u(ri, 1);
            cur += rem;
        }
        for (i = k - 1; i < n; ++i) {
            int ri = m.get(a[i]);
            long good = q(ri);
            long rem = k - 1 - good;
            u(ri, 1);
            cur += rem;
            res = Math.min(res, cur);
            int head = i - k + 1;
            int headrank = m.get(a[head]);
            long released = q(headrank - 1);
            cur -= released;
            u(headrank, -1);
        }
        return res;
    }
}
