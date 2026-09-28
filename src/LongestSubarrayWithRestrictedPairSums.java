import java.util.HashMap;
import java.util.Map;

public class LongestSubarrayWithRestrictedPairSums {
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int maxSubarray(int[] a) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        int j = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            while (j<i && bad(m, v)) {
                update(m, a[j], -1);
                ++j;
            }
            int cur = i-j+1;
            res = Math.max(res, cur);
            update(m, a[i], 1);

        }
        return res;
    }

    private boolean bad(Map<Integer, Integer> m, int v) {
        for (int k1 : m.keySet()) {
            int c1 = m.get(k1);
            int k2 = v-k1;
            if(m.containsKey(k2)){
                if(k2==k1 && c1==1){
                    continue;
                }
                return true;
            }
            k2 = k1-v;
            if(m.containsKey(k2)){
                if(k2==k1 && c1==1){
                    continue;
                }
                return true;
            }
            
        }
        return false;
    }
}
