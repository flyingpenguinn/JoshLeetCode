import java.util.*;

public class FindOriginalArrayFromDoubledArray {
    // similar to array of doubled pairs
    public int[] findOriginalArray(int[] a) {
        int n = a.length;
        if(n%2==1){
            return new int[0];
        }
        Map<Integer, Integer> m = new HashMap<>();
        Arrays.sort(a);
        for(int i=0; i<n; ++i){
            m.put(a[i], m.getOrDefault(a[i], 0)+1);
        }
        int[] res = new int[n/2];
        int ri = 0;
        for(int i=0; i<n; ++i){
            int ccount = m.getOrDefault(a[i], 0);
            if(ccount==0){
                continue;
            }else{
                res[ri++] = a[i];
                // subtract first in case it's a 0
                m.put(a[i], ccount-1);
                int dcount= m.getOrDefault(a[i]*2, 0);
                if(dcount==0) {
                    return new int[0];
                }else{
                    m.put(a[i]*2, dcount-1);
                }
            }
        }
        return res;
    }
}
