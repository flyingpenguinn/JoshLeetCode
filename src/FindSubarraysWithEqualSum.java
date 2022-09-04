import java.util.HashSet;
import java.util.Set;

public class FindSubarraysWithEqualSum {
    public boolean findSubarrays(int[] a) {
        int n = a.length;
        Set<Integer> set = new HashSet<>();
        for(int i=0; i+1<n; ++i){
            int sum = a[i]+a[i+1];
            if(set.contains(sum)){
                return true;
            }
            set.add(sum);
        }
        return false;
    }
}
