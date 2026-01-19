import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinOpsReachTargetArray {
    public int minOperations(int[] a, int[] b) {
        int n = a.length;
        Map<Integer, List<Integer>> ma = new HashMap<>();
        Map<Integer, List<Integer>> mb = new HashMap<>();
        for(int i=0; i<n; ++i){
            int va = a[i];
            int vb = b[i];
            if(va == vb){
                continue;
            }
            ma.computeIfAbsent(va, p-> new ArrayList<>()).add(i);
            mb.computeIfAbsent(vb, p-> new ArrayList<>()).add(i);
        }
        int res = 0;
        for(int ka: ma.keySet()){
            List<Integer> vas = ma.get(ka);
            List<Integer> vbs = mb.getOrDefault(ka, new ArrayList<>());
            if(vas.equals(vbs)){
                continue;
            }
            ++res;
        }
        return res;
    }
}
