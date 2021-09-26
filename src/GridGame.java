public class GridGame {
    // note there are only 2 rows
    public long gridGame(int[][] a) {
        int n = a[0].length;
        long sum0 = 0;
        for(int j=0; j<n; ++j){
            sum0 += a[0][j];
        }
        long res = Long.MAX_VALUE;
        long cur0 = 0;
        long cur1 = 0;
        for(int j=0; j<n; ++j){
            cur0 += a[0][j];
            long way0 = sum0-cur0;
            long way1 = cur1;
            long cur = Math.max(way0, way1);
            res = Math.min(cur, res);
            cur1 += a[1][j];
        }
        return res;
    }
}
