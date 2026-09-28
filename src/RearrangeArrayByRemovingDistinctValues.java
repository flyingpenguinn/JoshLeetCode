import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RearrangeArrayByRemovingDistinctValues {
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int[] rearrangeArray(int[] a) {
        Map<Integer,Integer> ma = new HashMap<>();
        for(int ai: a){
            update(ma, ai, 1);
        }
        List<Integer> res = new ArrayList<>();
        while(!ma.isEmpty()){
            Map<Integer,Integer> nm = new HashMap<>(ma);
            List<Integer> cur = new ArrayList<>();
            for(int k: ma.keySet()){
                update(nm, k, -1);
                cur.add(k);
            }
            Collections.sort(cur);
            ma = nm;
            res.addAll(cur);
        }
        int[] rr = new int[res.size()];
        for(int i=0; i<rr.length; ++i){
            rr[i] = res.get(i);
        }
        return rr;
    }
}
