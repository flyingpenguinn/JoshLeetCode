public class Convert1Dto2DArray {
    public int[][] construct2DArray(int[] a, int m, int n) {
        int osize = a.length;
        if(m*n != osize){
            return new int[0][0];
        }
        int[][] res = new int[m][n];
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                res[i][j] = a[i*n+j];
            }
        }
        return res;
    }
}
