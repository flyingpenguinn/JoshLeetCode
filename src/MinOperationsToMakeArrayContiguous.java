import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinOperationsToMakeArrayContiguous {
    // actually the same problem as "max len subarray where min and max diff within n-1"
    // here we use n- the result above
    public int minOperations(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        List<Integer> li = new ArrayList<>();
        for(int i=0; i<n; ++i){
            if(i==0 || a[i]!= a[i-1]){
                li.add(a[i]);
            }
        }
        int j = 0;
        int res = 0; // go for the longest subarray
        for(int i=0; i<li.size(); ++i){
            while(li.get(i)-li.get(j)>n-1){
                ++j;
            }
            // a[i]-a[j] <= n-1
            int cur = i-j+1;
            res = Math.max(res, cur);
        }
        return n-res;
    }
}
