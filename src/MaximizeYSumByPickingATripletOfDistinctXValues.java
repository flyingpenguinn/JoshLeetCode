import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaximizeYSumByPickingATripletOfDistinctXValues {
    public int maxSumDistinctTriplet(int[] x, int[] y) {
        int n = x.length;
        Map<Integer,Integer> xm = new HashMap<>();
        for(int i=0; i<n; ++i){
            int xv = x[i], yv = y[i];
            if(!xm.containsKey(xv)){ xm.put(xv, yv); }
            else if(yv > xm.get(xv)){ xm.put(xv, yv); }
        }
        List<Integer> ys = new ArrayList<>();
        for(int xk: xm.keySet()){ ys.add(xm.get(xk)); }
        if(ys.size()<3){ return -1; }
        Collections.sort(ys, Collections.reverseOrder());
        return ys.get(0) + ys.get(1) + ys.get(2);
    }
}
