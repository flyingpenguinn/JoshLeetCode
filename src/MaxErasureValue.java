import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxErasureValue {
    // all positive so we can do two pointer...when start is in good position we know it's the max we can get for subarray ending at i
    public int maximumUniqueSubarray(int[] a) {
        int n = a.length;
        int[] psum = new int[n];
        for (int i = 0; i < n; i++) {
            psum[i] = (i == 0 ? 0 : psum[i - 1]) + a[i];
        }
        Set<Integer> seen = new HashSet<>();
        int start = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (seen.contains(a[i])) {
                seen.remove(a[start++]);
            }
            seen.add(a[i]);
            res = Math.max(res, psum[i] - (start == 0 ? 0 : psum[start - 1]));
        }
        return res;
    }
}
