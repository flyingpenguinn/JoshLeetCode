import java.util.HashMap;
import java.util.Map;

public class NumberOfUniqueFlavorsAfterSharingKCandies {
    public int shareCandies(int[] a, int k) {
        int n = a.length;
        Map<Integer,Integer> m = new HashMap<>();
        for(int ai: a){
            update(m, ai, 1);
        }
        if(k==0){
            return m.size();
        }
        int res = 0;
        for(int i=0; i<n; ++i){
            update(m, a[i], -1);
            int head = i-k+1;
            if(head>=0){
                int sz = m.size();
                res = Math.max(res, sz);
                update(m, a[head], 1);
            }
        }
        return res;
    }

    private void update(Map<Integer,Integer> m, int k,int d){
        int nv = m.getOrDefault(k, 0)+d;
        if(nv==0){
            m.remove(k);
        }else{
            m.put(k, nv);
        }
    }
}
