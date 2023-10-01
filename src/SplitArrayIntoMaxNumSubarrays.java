public class SplitArrayIntoMaxNumSubarrays {
    public int maxSubarrays(int[] a) {
        int n = a.length;
        int cur = a[0];
        int res = 0;
        for(int i=1; i<n; ++i){
            if(cur==0){
                ++res;
                cur = a[i];
            }else{
                cur &= a[i];
            }
        }
        if(cur==0){
            ++res;
        }
        return Math.max(1, res);
    }
}
