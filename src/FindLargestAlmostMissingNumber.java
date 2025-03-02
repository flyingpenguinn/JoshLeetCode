import java.util.HashMap;
import java.util.Map;

public class FindLargestAlmostMissingNumber {
    public int largestInteger(int[] a, int k) {
        Map<Integer,Integer> c = new HashMap<>();
        int n = a.length;
        for(int i=0; i<n; ++i){
            c.put(a[i], c.getOrDefault(a[i], 0)+1);
        }
        int res = -1;
        for(int i=0; i<n; ++i){
            int cnt = c.get(a[i]);
            if(k==n){
                res = Math.max(res, a[i]);
                continue;
            }
            if(cnt>1){
                continue;
            }
            if(k==1 || k==n){
                res = Math.max(res, a[i]);
            }else{
                if(i==0 || i==n-1){
                    res = Math.max(res, a[i]);
                }
            }
        }
        return res;
    }
}
