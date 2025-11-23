import java.util.HashMap;
import java.util.Map;

public class FindMaxBalancedXorSubarrayLen {
    public int maxBalancedSubarray(int[] a) {
        int n = a.length;
        Map<Long, Integer> pre = new HashMap<>();
        long evencount = 0;
        long oddcount = 0;
        long xor = 0;
        long countshift = (long) 1e6;
        long xorshift = (long) 1e7;
        pre.put(countshift, -1);

        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] % 2 == 0) {
                ++evencount;
            } else {
                ++oddcount;
            }
            long diff = evencount - oddcount;
            diff += countshift;
            xor ^= a[i];
            long code = xor * xorshift + diff;
            if (pre.containsKey(code)) {
                int preindex = pre.get(code);
                int cur = i - preindex;
                res = Math.max(res, cur);
            }
            pre.putIfAbsent(code, i);
        }
        return res;
    }
}
