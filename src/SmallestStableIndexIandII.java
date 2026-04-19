public class SmallestStableIndexIandII {
    public int firstStableIndex(int[] a, int k) {
        int n = a.length;
        int[] mins = new int[n];
        mins[n-1] = a[n-1];
        for(int i=n-2; i>=0; --i){
            mins[i] = Math.min(a[i], mins[i+1]);
        }
        int maxv = -1;
        for(int i=0; i<n; ++i){
            maxv = Math.max(maxv, a[i]);
            int score = maxv - mins[i];
            if(score<=k){
                return i;
            }
        }
        return -1;
    }
}
