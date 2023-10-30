import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubarrayDistinctElementSumOfSquares {
    public int sumCounts(List<Integer> a) {
        int n = a.size();
        int res = 0;
        for(int i=0; i<n; ++i){
            Set<Integer> set = new HashSet<>();
            for(int j=i; j<n; ++j){
                set.add(a.get(j));
                int cur = set.size();
                res += cur*cur;
            }
        }
        return res;
    }
}
