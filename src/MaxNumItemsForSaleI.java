import java.util.Arrays;

// 3946. Daily 2026-11-26: knapsack pick / not pick; first purchase of item i also brings free[i] items.
public class MaxNumItemsForSaleI {
    private int[] free;
    public int maximumSaleItems(int[][] a, int budget) {
        int n = a.length;
        free = new int[n];
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                if(i!=j && a[j][0]%a[i][0]==0){
                    ++free[i];
                }
            }
        }
        dp = new int[n][budget+1][2];
        for(int i=0; i<n; ++i){
            for(int j=0; j<=budget; ++j){
                Arrays.fill(dp[i][j], -1);
            }
        }
        return solve(a, 0, budget, 1);
    }

    private int Min = -(int)1e9;
    private int[][][] dp;
    private int solve(int[][] a, int i, int b, int first){
        int n = a.length;
        if(b<0){
            return Min;
        }
        if(i==n){
            return 0;
        }
        if(dp[i][b][first] != -1){
            return dp[i][b][first];
        }
        int way1 = 0;
        if(first==1){
            way1 = 1+free[i]+solve(a, i, b-a[i][1], 0);
        }else{
            way1 = 1+solve(a, i, b-a[i][1], 0);
        }
        int way2 = solve(a, i+1, b, 1);
        int res = Math.max(way1, way2);
        dp[i][b][first] = res;
        return res;
    }
}
