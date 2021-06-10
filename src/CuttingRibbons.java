import java.util.Arrays;
import java.util.Collections;

public class CuttingRibbons {

    public int maxLength(int[] a, int k) {
        int l = 1;
        int u = 100000;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(possible(a, k, mid)){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        return u;
    }

    private boolean possible(int[] a, int k, int mid){
        int n = a.length;
        int res = 0;
        for(int i=0; i<n; i++){
            res += a[i]/mid;
            if(res>=k){
                return true;
            }
        }
        return false;
    }

}
