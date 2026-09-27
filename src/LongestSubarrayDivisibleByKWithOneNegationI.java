import java.util.HashSet;
import java.util.Set;

public class LongestSubarrayDivisibleByKWithOneNegationI {
    public int longestSubarray(int[] a, int k) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            long sum = 0;
            Set<Long> seen = new HashSet<>();
            for (int j = i; j < n; ++j) {
                long v = a[j];

                long delta = -2 * v;
                delta %= k;
                if (delta < 0) {
                    delta += k;
                }
                seen.add(delta);
                sum += v;
                long modsum =  (sum%k);
                if (modsum < 0) {
                    modsum += k;
                }
                if(modsum==0){
                    int cur = j-i+1;
                    res = Math.max(res, cur);
                }else{
                    long needed = k-modsum;
                    if(seen.contains(needed)){
                        int cur = j-i+1;
                        res = Math.max(res, cur);
                    }
                }
            }
        }
        return res;
    }
}
