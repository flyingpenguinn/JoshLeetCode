public class RemoveAllOnesWithRowsAndColumnsFlip {
    private Integer[] dp;
    public int removeOnes(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        dp = new Integer[(1<<(m*n))];
        int st = 0;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(a[i][j]==1){
                    st |= (1<<(i*n+j));
                }
            }
        }
        return solve(st, a);
    }

    private int solve(int st, int[][] a){

        if(st==0){
            return 0;
        }
        if(dp[st]!=null){
            return dp[st];
        }
        int m = a.length;
        int n = a[0].length;
        int res = (int)1e9;

        for(int i=0; i<m*n; ++i){
            int nst = st;
            int ci = i%n;
            int ri = i/n;
            if(((nst>>i)&1)==0){
                continue;
            }
            for(int j=0; j<m*n; ++j){
                if(((nst>>j)&1)==0){
                    continue;
                }
                int cj = j%n;
                int rj = j/n;
                if(ri==rj || ci ==cj){
                    nst ^= (1<<j);
                }
            }
            int cur= 1+solve(nst, a);
            res = Math.min(res, cur);
        }
        dp[st] = res;
        return res;
    }
}
