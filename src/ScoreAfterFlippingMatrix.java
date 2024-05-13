public class ScoreAfterFlippingMatrix {

    // flip row if made bigger,flip col if better for over half
    public int matrixScore(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for(int i=0; i<m; ++i){
            if(a[i][0] == 0){
                for(int j=0; j<n; ++j){
                    a[i][j] ^= 1;
                }
            }
        }
        int res = 0;
        for(int j=0; j<n; ++j){
            int ones = 0;
            for(int i=0; i<m; ++i){
                if(a[i][j]==1){
                    ++ones;
                }
            }
            int zeros = m-ones;
            //  cout<<ones<<" "<<zeros<<endl;
            int maxv = Math.max(ones, zeros);
            int bit = n-1-j;
            int cur = maxv*(1<<bit);
            //   cout<<cur<<endl;
            res += cur;
        }
        return res;
    }
}
