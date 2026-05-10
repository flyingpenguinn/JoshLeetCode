public class ConcatArrayWithReverse {
    public int[] concatWithReverse(int[] a) {
        int n = a.length;
        int[] res = new int[2*n];
        for(int i=0; i<n; ++i){
            res[i] = a[i];
        }
        int ai = 0;
        for(int i=2*n-1; i>=n; --i){
            res[i] = a[ai++];
        }
        return res;
    }
}
