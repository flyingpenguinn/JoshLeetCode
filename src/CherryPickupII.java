import java.util.Arrays;

public class CherryPickupII {
    private Integer[][][] dp;
    public int cherryPickup(int[][] a) {
        int n = a[0].length;
        int m = a.length;
        dp = new Integer[m][n][n];
        return dopick(a, 0,0, n-1);
    }

    private int[] moves = {-1, 0, 1};
    // move to next cell at the same time
    private int dopick(int[][] a, int i, int j, int k){
        int n = a[0].length;
        int m = a.length;
        if(i==m){
            return 0;
        }
        if(dp[i][j][k]!= null){
            return dp[i][j][k];
        }
        int topick = j==k? a[i][j]: a[i][j]+a[i][k];
        int res = 0;
        for(int mj: moves){
            for(int mk: moves){
                int nj = j+mj;
                int nk = k+mk;
                if(nj>=0 && nj<n && nk>=0 && nk<n){
                    int cur = topick+dopick(a, i+1, nj, nk);
                    res = Math.max(cur, res);
                }
            }
        }
        dp[i][j][k] = res;
        return res;
    }
}
