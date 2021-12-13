import java.util.Arrays;

public class SubsequenceOfSizeKOfLargestEvenSum {
    public long largestEvenSum(int[] a, int k) {
        Arrays.sort(a);
        int n = a.length;
        long res = 0;
        long smallestodd = (long) (1e12+1);
        long smallesteven = (long) 1e12;
        for(int i=n-1; i>=n-k;--i){
            long v = a[i];
            res += v;
            if(a[i]%2==1){
                smallestodd = Math.min(smallestodd, v);
            }else{
                smallesteven = Math.min(smallesteven, v);
            }
        }
        if(res%2==0){
            return res;
        }else{
            long rawres = res;
            for(int i=n-k-1; i>=0; --i){
                long cur = 0;
                if((smallestodd-a[i])%2==1){
                    cur = rawres - (smallestodd-a[i]);
                }else if((smallesteven-a[i])%2==1){
                    cur = rawres - (smallesteven-a[i]);
                }
                if(res%2==1){
                    res = cur;
                }else {
                    res = Math.max(res, cur);
                }
            }
        }
        return res%2==0? Math.max(-1L, res): -1;
    }
}
