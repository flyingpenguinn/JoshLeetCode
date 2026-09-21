public class CountIntersectingIntervalPairsI {
    public int countIntersectingIntervals(int[][] a) {
        int n = a.length;
        int res = 0;
        for(int i=0; i<n; ++i){
            int start = a[i][0];
            int end = a[i][1];
            for(int j=i+1; j<n; ++j){
                int sj = a[j][0];
                int ej = a[j][1];
                if(ej<start || sj>end){
                    continue;
                }
                ++res;
            }
        }
        return res;
    }
}
