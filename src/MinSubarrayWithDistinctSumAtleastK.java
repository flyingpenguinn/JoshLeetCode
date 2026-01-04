import java.util.HashMap;
import java.util.Map;

public class MinSubarrayWithDistinctSumAtleastK {
    private boolean update(Map<Long,Integer> m, long k, int d){
        int nv = m.getOrDefault(k, 0)+d;
        if(nv<=0){
            m.remove(k);
            return true;
        }else{
            m.put(k, nv);
            return false;
        }
    }

    public int minLength(int[] a, int k) {
        int n = a.length;
        Map<Long,Integer> m = new HashMap<>();
        int j = 0;
        long sumdist = 0;
        int Max = (int)1e9;
        int res = Max;
        for(int i=0; i<n; ++i){
            while(j<n && sumdist <k){
                long jv = a[j];
                if(!m.containsKey(jv)){
                    sumdist += jv;
                }
                update(m, jv, 1);
                ++j;
            }
            if(sumdist>=k){
                int len = j-i;
                res = Math.min(res, len);
            }
            long v = a[i];
            boolean removed = update(m, v, -1);
            if(removed){
                sumdist -= v;
            }
        }
        return res>=Max? -1: res;
    }
}
