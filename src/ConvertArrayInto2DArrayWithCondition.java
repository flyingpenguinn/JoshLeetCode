import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConvertArrayInto2DArrayWithCondition {
    public List<List<Integer>> findMatrix(int[] a) {
        List<List<Integer>> res = new ArrayList<>();
        int n = a.length;
        int rem = n;
        Set<Integer> added = new HashSet<>();
        while(rem>0){
            List<Integer> cur = new ArrayList<>();
            Set<Integer> seen = new HashSet<>();
            for(int i=0; i<n; ++i){
                if(added.contains(i)){
                    continue;
                }
                if(seen.contains(a[i])){
                    continue;
                }
                seen.add(a[i]);
                cur.add(a[i]);
                added.add(i);
                --rem;
            }
            res.add(cur);
        }
        return res;
    }
}
