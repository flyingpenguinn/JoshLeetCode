public class MaxScopeOfSplit {
    public long maximumScore(int[] a) {
        int n = a.length;
        long[] minright = new long[n];
        minright[n-1] = a[n-1];
        for(int i=n-2; i>=0; --i){
            minright[i] = Math.min(a[i], minright[i+1]);
        }
        long left = 0;
        long res = (long)-1e15;
        for(int i=0; i<n-1; ++i){
            left += a[i];
            long cur = left - minright[i+1];
            res = Math.max(res, cur);
        }
        return res;
    }
}
