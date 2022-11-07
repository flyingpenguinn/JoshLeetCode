import java.util.HashMap;
import java.util.Map;

public class MaxSumDistinctSubarrayLenK {
    public long maximumSubarraySum(int[] a, int k) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        long sum = 0;
        for (int i = 0; i < k - 1; ++i) {
            update(m, a[i], 1);
            sum += a[i];
        }
        long res = 0;
        for (int i = k - 1; i < n; ++i) {
            update(m, a[i], 1);
            //   System.out.println(i+" "+m);
            sum += a[i];
            if (m.size() == k) {
                res = Math.max(res, sum);
            }
            int head = i - k + 1;
            update(m, a[head], -1);
            sum -= a[head];
        }
        return res;
    }

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
