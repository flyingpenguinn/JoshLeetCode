import java.util.HashMap;
import java.util.Map;

public class CountNumberOfBeautifulSubarrays {
    // actually their xor = 0
    public long beautifulSubarrays(int[] a) {
        int n = a.length;
        Map<Integer,Long> m = new HashMap<>();
        m.put(0, 1L);
        int xor = 0;
        long res = 0;
        for(int i=0; i<n; ++i){
            xor ^= a[i];
            long cur = m.getOrDefault(xor, 0L);
            res += cur;
            m.put(xor, cur +1);
        }
        return res;
    }
}
