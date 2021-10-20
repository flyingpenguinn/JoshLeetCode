import java.util.HashMap;
import java.util.Map;

public class CountNumberOfMaxXors {
    public int countMaxOrSubsets(int[] a) {
        int n = a.length;
        Map<Integer,Integer> m = new HashMap<>();
        int maxorv = Integer.MIN_VALUE;
        for(int st = 0; st<(1<<n); ++st){
            int orv = getOrValues(a, st);
            m.put(orv, m.getOrDefault(orv, 0)+1);
            maxorv = Math.max(orv, maxorv);
        }
        return m.get(maxorv);
    }

    private int getOrValues(int[]a, int st){
        int res = 0;
        for(int i=0; i<a.length; ++i){
            if(((st>>i)&1)==1){
                res |= a[i];
            }
        }
        return res;
    }
}
