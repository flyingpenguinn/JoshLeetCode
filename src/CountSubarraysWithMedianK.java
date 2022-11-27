import java.util.HashMap;
import java.util.Map;

public class CountSubarraysWithMedianK {
    // median ==k : equal number of nums < and >k (even). or one more num >k (odd)
    // then we can convert the problem to counting subarray sums ==0 or ==1
    public int countSubarrays(int[] a, int k) {
        int n = a.length;
        int[] na = new int[n];
        for (int i = 0; i < n; ++i) {
            na[i] = a[i] - k;
            if (na[i] < 0) {
                na[i] = -1;
            } else if (na[i] > 0) {
                na[i] = 1;
            }
        }
        Map<Long, Integer> om = new HashMap<>();
        Map<Long, Integer> em = new HashMap<>();
        om.put(0L, 1); // -1 is odd
        long sum = 0;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            sum += na[i];
            int cur1 = 0;
            int cur2 = 0;
            if (i % 2 == 0) {
                // even
                cur1 = om.getOrDefault(sum, 0);
                cur2 = em.getOrDefault(sum - 1, 0);
                em.put(sum, em.getOrDefault(sum, 0) + 1);
            } else {
                // odd
                cur1 = em.getOrDefault(sum, 0);
                cur2 = om.getOrDefault(sum - 1, 0);
                om.put(sum, om.getOrDefault(sum, 0) + 1);
            }
            res += cur1;
            res += cur2;
        }
        return (int) res;
    }
}
