public class MaxMatrixSum {
    private int Max = 1000000000;
    public long maxMatrixSum(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        long sum = 0;
        int minabs = Max;
        int negs = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(a[i][j]<0){
                    negs++;
                }
                minabs = Math.min(minabs, Math.abs(a[i][j]));
                sum += Math.abs(a[i][j]);
            }
        }
        if(negs %2==1){
            return sum - 2*minabs;  // we cacn transfer all the negativity to the minabs
        }else{
            return sum;
        }
    }
}
