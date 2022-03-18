public class MaximizeTopmostAfterKMoves {
    // we can't really get the k-1th
    public int maximumTop(int[] a, int k) {
        int n = a.length;
        if(k==0){
            return a[0];
        }
        if(n==1){
            return k%2==0? a[0]:-1;
        }
        int max = -1;
        int end = Math.min(n-1, k-2);
        for(int i=0; i<=end; ++i){
            max = Math.max(max, a[i]);
        }
        if(k<n){
            max = Math.max(max, a[k]);
        }
        return max;
    }
}
