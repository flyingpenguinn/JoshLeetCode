public class MinimizeDiffBetweenTargetAndChosenValue {
    private int Max = 2000000000;
    private Integer[][] dp;
    public int minimizeTheDifference(int[][] a, int t) {
        int m = a.length;
        int n = a[0].length;
        dp = new Integer[m][5000];
        return solve(a, 0, 0, t);
    }

    int solve(int[][] a, int i, int cur, int t){
        int m = a.length;
        int n = a[0].length;
        if(i==m){
            return Math.abs(cur-t);
        }
        if(dp[i][cur] != null){
            return dp[i][cur];
        }
        int res = Max;
        for(int j=0; j<n; j++){
            int later = solve(a, i+1, cur+a[i][j], t);
            res = Math.min(res, later);
        }
        dp[i][cur] = res;
        return res;
    }
}
