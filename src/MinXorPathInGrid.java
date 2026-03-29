import java.util.Arrays;

public class MinXorPathInGrid {
    private int[][][] dp;
    public int minCost(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int maxv = 0;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                maxv = Math.max(maxv, a[i][j]);
            }
        }
        dp = new int[m][n][(maxv+1)*2];
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return solve(a, 0, 0, 0);
    }

    private int solve(int[][] a, int i, int j, int cur){
        int m = a.length;
        int n = a[0].length;
        if(dp[i][j][cur] != -1){
            return dp[i][j][cur];
        }
        int ncur= cur^ a[i][j];
        int res;
        if(i==m-1 && j==n-1){
            res = ncur ;
        }else if(i==m-1){
            res =  solve(a, i, j+1, ncur);
        }else if(j==n-1){
            res = solve(a, i+1, j, ncur);
        }else{
            int way1 = solve(a, i+1, j, ncur);
            int way2 = solve(a, i, j+1, ncur);
            res = Math.min(way1, way2);
        }
        dp[i][j][cur] = res;
        return res;
    }
}
