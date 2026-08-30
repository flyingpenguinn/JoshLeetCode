public class MaximumNumberOfMatchingIndicesAfterRightShifts {
    public int maximumMatchingIndices(int[] a, int[] b) {
        int n = a.length;
        int res = 0;
        for(int i=0; i<n; ++i){
            int cur = 0;
            for(int j=i; j<i+n; ++j){
                int mj = j%n;
                if(a[mj] == b[j-i]){ ++cur; }
            }
            res = Math.max(res, cur);
        }
        return res;
    }
}
