import java.util.HashMap;
import java.util.Map;

public class FindMedianOfUniquenessArray {
    // find median we can use binary search !
    public int medianOfUniquenessArray(int[] a) {
        int n = a.length;
        long l = 1;
        long u = (int) 1e9;
        long all = 1L * n * (n + 1) / 2;
        //  System.out.println(all);
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long count = docount(a, mid);
            //  System.out.println(mid+" "+count);
            if (count >= (all + 1) / 2) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }

    private long docount(int[] a, long t) {
        int n = a.length;
        int j = 0;
        long res = 0;
        Map<Long, Long> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            update(m, a[i], 1);
            while (m.size() > t) {
                update(m, a[j], -1);
                ++j;
            }
            // j...i all good
            long cur = (i - j + 1);
            res += cur;
        }
        return res;
    }

    private void update(Map<Long, Long> m, long k, long d) {
        long nv = m.getOrDefault(k, 0L) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
