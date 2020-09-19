import java.util.HashMap;
import java.util.Map;

public class MaxSumDivisibleByP {
    // similar to subarray sum divisible by k. here note we need to find mod == given target, not divisible so that mod == 0
    public int minSubarray(int[] a, int p) {
        int n = a.length;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        long mod = sum % p;
        if (mod == 0) {
            return 0;
        }
        Map<Long, Integer> m = new HashMap<>();
        m.put(0L, -1);
        sum = 0;
        int min = n;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            long cmod = sum % p;
            // (sum2-sum1) % p = mod
            // hence (cmod - mod ) % p is sum1
            long target = cmod - mod;
            if (target < 0) {
                target += p;
            }
            Integer pre = m.get(target);
            if (pre != null) {
                min = Math.min(min, i - pre);
            }
            m.put(cmod, i);
        }
        return min >= n ? -1 : min;
    }

}
