import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LengthOfLongestSubarrayWithAtmostKFreq {
    // tree map is actually redundant- if m[i]>k then we reduce i. we can guarantee anything between j...i are <=k in freq
    private Map<Integer, Integer> m = new HashMap<>();
    private TreeMap<Integer, Integer> tm = new TreeMap<>();

    private int update(Map<Integer, Integer> m, int k, int d) {
        int ov = m.getOrDefault(k, 0);
        int nv = ov + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
        return ov;
    }

    public int maxSubarrayLength(int[] a, int k) {
        int n = a.length;
        int j = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            int ov = update(m, v, 1);
            update(tm, ov, -1);
            update(tm, ov + 1, 1);
            //   System.out.println("i="+i+" m="+m+" tm="+tm);
            while (tm.lastKey() > k) {
                int hv = a[j];
                int hov = update(m, hv, -1);
                update(tm, hov, -1);
                update(tm, hov - 1, 1);

                //  System.out.println("j="+i+" m="+m+" tm="+tm);
                ++j;
            }
            // from j to i good
            int cur = i - j + 1;
            res = Math.max(res, cur);
        }
        return res;
    }
}
