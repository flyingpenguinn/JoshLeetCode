import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxNumberOfWaysToPartitionArray {
    // as we change each number, we estimate its impact. all sums after it now needs to be adjusted
    public int waysToPartition(int[] a, int k) {
        int n = a.length;
        long sum = 0;
        for (int ai : a) {
            sum += ai;
        }
        Map<Long, Integer> m = new HashMap<>();
        long csum = 0;
        int res = 0;
        // all possible partitions, up to n-2
        for (int i = 0; i < n - 1; ++i) {
            csum += a[i];
            update(m, csum, 1);
            if (sum % 2 == 0 && csum == sum / 2) {
                ++res;
            }
        }
        long psum = 0;
        Map<Long, Integer> pre = new HashMap<>();
        // all possible changes, up to n-1
        for (int i = 0; i < n; ++i) {
            psum += a[i];
            long newsum = sum - a[i] + k;
            if (newsum % 2 == 0) {
                long t = newsum / 2;
                int curres1 = pre.getOrDefault(t, 0);
                int curres2 = m.getOrDefault(t + a[i] - k, 0);
                res = Math.max(curres1 + curres2, res);
            }
            update(m, psum, -1);
            update(pre, psum, 1);
        }
        return res;
    }

    private void update(Map<Long, Integer> m, long k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
