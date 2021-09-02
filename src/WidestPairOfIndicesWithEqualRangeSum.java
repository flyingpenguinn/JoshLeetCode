import java.util.HashMap;
import java.util.Map;

public class WidestPairOfIndicesWithEqualRangeSum {
    // find the j that caused same sumb-suma delta
    public int widestPairOfIndices(int[] a, int[] b) {
        int n = a.length;
        int[] sa = new int[n];
        int[] sb = new int[n];
        sa[0] = a[0];
        sb[0] = b[0];
        for(int i=1; i<n; i++){
            sa[i] = sa[i-1]+a[i];
            sb[i] = sb[i-1]+b[i];
        }
        Map<Integer,Integer> m = new HashMap<>();
        m.put(0, -1);
        int res = 0;
        for(int i=0; i<n; i++){
            int delta = sb[i]-sa[i];
            if(m.containsKey(delta)){
                res = Math.max(res, i-m.get(delta));
            }
            m.putIfAbsent(delta, i);
        }
        return res;
    }
}
