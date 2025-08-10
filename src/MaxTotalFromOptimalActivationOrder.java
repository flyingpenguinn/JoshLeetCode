import java.util.*;

public class MaxTotalFromOptimalActivationOrder {
    // pick k for lim = k
    public long maxTotal(int[] v, int[] l) {
        int n = v.length;
        Map<Long, PriorityQueue<Long>> m = new HashMap<>();
        Map<Long, Long> sum = new HashMap<>();
        for(int i=0; i<n; ++i){
            long lv = l[i];
            long vv = v[i];
            m.computeIfAbsent(lv, k->  new PriorityQueue<>()).offer(vv);
            sum.put(lv, sum.getOrDefault(lv, 0L)+vv);
            if(m.get(lv).size()>lv){
                long csum = sum.get(lv);
                csum -= m.get(lv).poll();
                sum.put(lv, csum);
            }
        }
        long res = 0;
        for(long k: sum.keySet()){
            res += sum.getOrDefault(k, 0L);
        }
        return res;
    }
}
