import java.util.HashMap;
import java.util.Map;

public class MaximizeBeautyOfGarden {
    // use positive subarray sums
    public int maximumBeauty(int[] a) {
        int n = a.length;
        int[] psum = new int[n];
        int csum = 0;
        Map<Integer, Integer> start = new HashMap<>();
        Map<Integer, Integer> end = new HashMap<>();

        for (int i = 0; i < n; i++) {
            csum += a[i] <= 0 ? 0 : a[i];
            psum[i] = csum;
            if (!start.containsKey(a[i])) {
                start.put(a[i], i);
            }
            end.put(a[i], i);
        }
        int max = Integer.MIN_VALUE;
        for (int sn : start.keySet()) {
            int si = start.get(sn);
            int ei = end.getOrDefault(sn, -1);
            if (ei == -1 || ei == si) {
                continue;
            }
            // si+1... ei-1
            //    System.out.println(sn+" "+si+" "+ei);
            int cur = 2 * sn + psum[ei - 1] - psum[si];
            max = Math.max(cur, max);
        }
        return max;
    }
}
