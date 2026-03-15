import java.util.HashMap;
import java.util.Map;

public class MinCostToEqualUsingSwaps {
    public int minCost(int[] a, int[] b) {
        int n = a.length;
        Map<Integer,Integer> ma = new HashMap<>();
        Map<Integer,Integer> mb = new HashMap<>();
        for(int i=0; i<n; ++i){
            ma.put(a[i], ma.getOrDefault(a[i], 0)+1);
        }
        int res = 0;
        for(int i=0; i<n; ++i){
            mb.put(b[i], mb.getOrDefault(b[i], 0)+1);
        }
        for(int ka: ma.keySet()){
            int ca = ma.get(ka);
            int cb = mb.getOrDefault(ka, 0);
            int diff = Math.abs(ca-cb);
            if(diff%2==1){
                return -1;
            }
            int swap = diff/2;
            res += swap;
        }
        for(int kb: mb.keySet()){
            if(ma.containsKey(kb)){
                continue;
            }
            int ca = ma.getOrDefault(kb, 0);
            int cb = mb.get(kb);
            int diff = Math.abs(ca-cb);
            if(diff%2==1){
                return -1;
            }
            int swap = diff/2;
            res += swap;
        }
        return res/2;
    }
}
