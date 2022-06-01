public class MaxProfitFromTradingStocks {
    private Integer[][] dp;
    public int maximumProfit(int[] present, int[] future, int budget) {
        dp = new Integer[present.length][budget+1];
        return solve(present, future, 0, budget);
    }

    private int solve(int[] p, int[] f, int i, int j){
        int n = p.length;
        if(i==n){
            return 0;
        }
        if(dp[i][j] != null){
            return dp[i][j];
        }
        int way1 = solve(p,f, i+1, j);
        int way2 = 0;
        if(j-p[i]>=0){
            way2 = f[i]-p[i] +solve(p,f, i+1, j-p[i]);
        }
        int rt = Math.max(way1, way2);
        dp[i][j] = rt;
        return rt;
    }
}
