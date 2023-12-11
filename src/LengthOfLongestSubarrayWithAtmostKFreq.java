import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LengthOfLongestSubarrayWithAtmostKFreq {
    // max freq- tree map is actually redundant- if m[i]>k then we reduce i. we can guarantee anything between j...i are <=k in freq
    private Map<Integer,Integer> m = new HashMap<>();
    public int maxSubarrayLength(int[] a, int k) {
        int n = a.length;
        int j = 0;
        int res = 0;
        for(int i=0; i<n; ++i){
            int v = a[i];
            m.put(v, m.getOrDefault(v, 0)+1);
            while(m.get(v)>k){
                int hv = a[j];
                m.put(hv, m.get(hv)-1);
                ++j;
            }
            int cur = i-j+1;
            res = Math.max(res, cur);
        }
        return res;
    }
}
