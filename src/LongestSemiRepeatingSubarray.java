import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class LongestSemiRepeatingSubarray {
    public int longestSubarray(int[] a, int k) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        int j = 0;
        int res = 0;
        int morethanones = 0;
        for (int i = 0; i < n; ++i) {
            int nc = m.getOrDefault(a[i], 0) + 1;
            m.put(a[i], nc);
            if (nc == 2) {
                morethanones += 1;
            }
            while (j <= i && morethanones > k) {
                int njc = m.getOrDefault(a[j], 0) - 1;
                if (njc == 1) {
                    --morethanones;
                }
                m.put(a[j], njc);
                ++j;
            }
            int len = i - j + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSemiRepeatingSubarray().longestSubarray(ArrayUtils.read1d("[1,1,1,1,1]"), 0));
    }
}
