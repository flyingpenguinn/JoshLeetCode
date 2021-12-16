import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class MaxDistanceInArrays {
    // a trick...
    public int maxDistance(List<List<Integer>> a) {
        int n = a.size();
        int maxv = a.get(0).get(a.get(0).size()-1);
        int minv = a.get(0).get(0);
        int res = 0;
        for(int i=1; i<n; ++i){
            int cur1 = maxv-a.get(i).get(0);
            int cur2 = a.get(i).get(a.get(i).size()-1)-minv;
            res = Math.max(res, Math.max(cur1, cur2));
            maxv = Math.max(maxv, a.get(i).get(a.get(i).size()-1));
            minv = Math.min(minv, a.get(i).get(0));
        }
        return res;
    }
}
