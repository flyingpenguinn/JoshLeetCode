public class MaxDifFBetweenIncreasingElements {
    public int maximumDifference(int[] a) {
        int n = a.length;
        int res = -1;
        int rmax = a[n-1];
        for(int i=n-2; i>=0; --i){
            int cur = rmax- a[i];
            rmax = Math.max(rmax, a[i]);
            if(cur>0){
                res = Math.max(res, cur);
            }
        }
        return res;
    }
}
