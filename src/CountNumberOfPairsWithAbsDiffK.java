import java.util.HashMap;
import java.util.Map;

public class CountNumberOfPairsWithAbsDiffK {
    public int countKDifference(int[] a, int k) {
        Map<Integer,Integer> m = new HashMap<>();
        for(int ai: a){
            m.put(ai, m.getOrDefault(ai, 0)+1);
        }
        int res = 0;
        for(int mv: m.keySet()){
            int v1 = m.get(mv);
            int v2 = m.getOrDefault(mv+k, 0);
            res += v1*v2;
        }
        return res;
    }
}
