import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class AmountOfNewAreaPaintedEachDay {
    public int[] amountPainted(int[][] ps) {
        int n = ps.length;
        int range = 50001;
        Map<Integer,List<int[]>> m = new HashMap<>();
        for(int i=0; i<n; ++i){
            int s = ps[i][0], e = ps[i][1];
            m.computeIfAbsent(s, k-> new ArrayList<>()).add(new int[]{i, 1});
            m.computeIfAbsent(e, k-> new ArrayList<>()).add(new int[]{i, -1});
        }
        TreeSet<Integer> layers = new TreeSet<>();
        int[] res = new int[n];
        for(int i=0; i<range;++i){
            List<int[]> cur = m.getOrDefault(i, new ArrayList<>());
            for(int[] c: cur){ if(c[1]==1){ layers.add(c[0]); } else{ layers.remove(c[0]); } }
            if(!layers.isEmpty()){ ++res[layers.first()]; }
        }
        return res;
    }
}
