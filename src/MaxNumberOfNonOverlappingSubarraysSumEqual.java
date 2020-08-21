import java.util.HashMap;
import java.util.Map;

public class MaxNumberOfNonOverlappingSubarraysSumEqual {
    // subarray sum + interval problem. in interval problem we always pick the smallest end
    public int maxNonOverlapping(int[] a, int t) {
        int sum = 0;
        int end = -1;
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, -1); // dont forget this in all subarray problems
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            Integer pre = m.get(sum - t);
            if (pre != null && pre >= end) {  // >=end because it starts from pre+1 anyway
                res++;
                end = i;
            }
            m.put(sum, i);
        }
        return res;
    }
}
