import java.util.HashMap;
import java.util.Map;

public class CountSpecialQuadruples {
    // find a+b == d-c
    // we fix index of b, loop through a
    // as we loop through b in reverse, all the later possible d-c are dynamically maintained.
    // similar to 4sum (FourSumMapBased)
    public int countQuadruplets(int[] a) {
        int n = a.length;
        int res = 0;
        Map<Integer,Integer> later = new HashMap<>();
        add(later, a[n-1]-a[n-2]);
        for(int j=n-3; j>=0; --j){ //b
            for(int i=0; i<j; ++i){
                res += later.getOrDefault(a[i]+a[j], 0);
            }
            // now this b would become c for the next round onward
            // add as possible d-c into the map for look up
            for(int k=j+1; k<n; ++k){
                add(later, a[k]-a[j]);
            }
        }
        return res;
    }

    private void add(Map<Integer,Integer> m, int k){
        m.put(k, m.getOrDefault(k, 0)+1);
    }
}
