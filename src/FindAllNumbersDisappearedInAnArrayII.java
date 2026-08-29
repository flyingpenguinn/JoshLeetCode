import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllNumbersDisappearedInAnArrayII {
    public List<List<Integer>> findDisappearedNumbers(int[] a, int lower, int upper) {
        Arrays.sort(a);
        int n = a.length;
        List<List<Integer>> res = new ArrayList<>();
        int firstlostend = Math.min(a[0]-1, upper);
        if(firstlostend>=lower){
            res.add(List.of(lower, firstlostend));
        }
        for(int i=1; i<n;++i){
            int pre = a[i-1];
            int cur = a[i];
            int lostend = Math.min(cur-1, upper);
            int loststart = Math.max(pre+1, lower);
            if(lostend>=loststart){
                res.add(List.of(loststart, lostend));
            }
            if(cur>=upper){
                break;
            }
        }
        int lastloststart = Math.max(a[n-1]+1, lower);
        if(lastloststart<=upper){
            res.add(List.of(lastloststart, upper));
        }
        return res;
    }
}
