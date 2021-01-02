import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxNumberOfNonOverlappingSubarraysSumEqual {
    // subarray sum + interval problem. in interval problem we always pick the smallest end
    public int maxNonOverlapping(int[] a, int t) {
        Set<Integer> set = new HashSet<>();
        // possible starts
        int n = a.length;
        int sum = 0;
        set.add(0);
        int res = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            if (set.contains(sum - t)) {
                res++;
                set.clear();
                // none of the possible starts we stored would be used later as they are disqualified
            }
            set.add(sum);
        }
        return res;
    }
}
