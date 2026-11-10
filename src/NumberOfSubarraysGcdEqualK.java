import java.util.HashMap;
import java.util.Map;

public class NumberOfSubarraysGcdEqualK {
    // fix the right end: gcd of subarrays ending here has at most log(V) distinct values
    // carry (gcd value -> count) forward, merging equal values
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    private int gcd(int a, int b){
        return b==0?a: gcd(b, a%b);
    }

    public int subarrayGCD(int[] a, int k) {
        int n = a.length;
        Map<Integer,Integer> cnt = new HashMap<>();
        int res = 0;
        for(int i=0; i<n; ++i){
            int v = a[i];
            Map<Integer,Integer> ncnt = new HashMap<>();
            for(int key: cnt.keySet()){
                int ng = gcd(key, v);
                int oldcount = cnt.get(key);
                update(ncnt, ng, oldcount);
            }
            update(ncnt, v, 1);
            int ccnt = ncnt.getOrDefault(k, 0);
            res += ccnt;
            cnt = ncnt;
        }
        return res;
    }
}
