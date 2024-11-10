import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class MaxFreqElementAfterPerformingOpertaionsIandII {
    // only need to consider v, v-x, v+x. then sweep all these values and check a sliding window of v-k, v+k range to see how many elements are there
    public int maxFrequency(int[] a, int ran, int num) {
        Arrays.sort(a);
        Map<Integer, Integer> cm = new HashMap<>();
        TreeSet<Integer> ls = new TreeSet<>();
        int maxv = 0;
        for (int ai : a) {
            ls.add(ai - ran);
            ls.add(ai + ran);
            ls.add(ai);
            cm.put(ai, cm.getOrDefault(ai, 0) + 1);
            maxv = Math.max(maxv, ai);

        }

        int n = a.length;
        int j = 0;
        int k = 0;
        int res = 1;
        for (int v : ls) {
            while (j < n && v - a[j] > ran) {
                ++j;
            }
            while (k < n && a[k] - v <= ran) {
                ++k;
            }
            // j...k-1;
            //   System.out.println("For "+i+" j="+j+" k="+k);
            int len = k - j;
            int same = cm.getOrDefault(v, 0);
            int others = len - same;
            int adjusted = Math.min(others, num);
            int cur = adjusted + same;
            res = Math.max(res, cur);
        }
        return res;
    }
}
