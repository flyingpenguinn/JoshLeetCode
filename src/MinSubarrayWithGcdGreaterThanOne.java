import java.util.HashMap;

public class MinSubarrayWithGcdGreaterThanOne {
    // given the restraints can also do dp
    public int minimumSplits(int[] a) {
        int n = a.length;
        int gcd = a[0];
        int res = 1;
        for(int i=1; i<n; ++i){
            int ngcd = gcd(gcd, a[i]);
            if(ngcd==1){
                gcd = a[i];
                ++res;
            }else{
                gcd = ngcd;
            }
        }
        return res;
    }

    private int gcd(int i, int j){
        return i<j? gcd(j, i): (j==0? i: gcd(j, i%j));
    }
}
