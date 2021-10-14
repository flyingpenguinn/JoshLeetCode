import static java.lang.Math.*;

public class MaxAlternatingSubarraySum {
    // a0-a1+...aj
    // either a2-a3... so we need to - odd results, or we -even and flip the sign, basically - the max even
    public long maximumAlternatingSubarraySum(int[] a) {
        int n = a.length;
        long minodd = 0;
        long maxeven = a[0];
        long res = a[0];
        long csum = a[0];
        for(int i=1; i<n; ++i){
            int sign = i%2==1? -1: 1;
            csum += sign*a[i];
            long way1 = -(csum-maxeven);
            long way2 = csum-minodd;
            if(i%2==0){
                maxeven = max(maxeven, csum);
            }else{
                minodd = min(minodd, csum);
            }
            long cur = max(way1, way2);
            res = max(cur, res);
        }
        return res;
    }
}
