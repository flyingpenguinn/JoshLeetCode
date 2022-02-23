import java.util.HashMap;
import java.util.Map;

public class CountArrayPairsDivisibleByK {
    public long countPairs(int[] a, int k) {
        int n = a.length;
        Map<Long,Integer> m = new HashMap<>();
        long res = 0;
        for(int i=0; i<n; ++i){
            long gi = gcd(a[i], k);
            for(long gj: m.keySet()){
                if((gi*gj ) %k == 0){
                    res += m.get(gj);
                }
            }
            m.put(gi, m.getOrDefault(gi, 0)+1);
        }
        return res;
    }

    private long gcd(long a, long b){
        return b==0?a: gcd(b, a%b);
    }
}
