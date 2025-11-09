public class MinMovesEqualArrayElements {
    public int minMoves(int[] a) {
        int n = a.length;
        int maxv = 0;
        for(int ai: a){
            maxv = Math.max(ai, maxv);
        }
        int res = 0;
        for(int i=0; i<n; ++i){
            res += maxv-a[i];
        }
        return res;
    }
}
