import java.util.HashMap;
import java.util.Map;

public class NumberOfPairsOfInterchangeableRect {
    public long interchangeableRectangles(int[][] a) {
        Map<Double, Long> m = new HashMap<>();
        int n = a.length;
        for(int i=0; i<n; ++i){
            double ratio = a[i][0]*1.0/a[i][1];
            m.put(ratio, m.getOrDefault(ratio, 0L)+1L);
        }
        long res = 0;
        for(Double k : m.keySet()){
            long cnt = m.get(k);
            res += cnt*(cnt-1)/2;
        }
        return res;
    }
}
