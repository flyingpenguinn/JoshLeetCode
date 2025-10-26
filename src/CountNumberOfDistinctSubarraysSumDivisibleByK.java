import java.util.HashMap;
import java.util.Map;

public class CountNumberOfDistinctSubarraysSumDivisibleByK {
    // sorted! sorted makes dupe only happening in consecutive equal segment
    public long numGoodSubarrays(int[] a, int k) {
        int n = a.length;
        Map<Long, Long> cnt = new HashMap<>();
        cnt.put(0L, 1L);
        long csum = 0;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            csum += a[i];
            long mod = csum % k;
            res += cnt.getOrDefault(mod, 0L);
            cnt.put(mod, cnt.getOrDefault(mod, 0L) + 1);
        }
        int i = 0;
        while (i < n) {
            int j = i;
            long bsum = 0;
            long blen = 0;

            while (j < n && a[j] == a[i]) {
                ++blen;
                ++j;
            }
            j = i;
            long clen = 0;
            while (j < n && a[j] == a[i]) {
                bsum += a[j];
                ++clen;
                if (bsum % k == 0) {
                    long counts = blen - clen + 1;
                    long dupes = counts - 1;
                    res -= dupes;
                }
                ++j;
            }
            i = j;
        }
        return res;
    }
}
