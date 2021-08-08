public class MinTotalSpaceWastedWithKOperations {
    // k operations with choosing a special number for a range. similar to allocateMailBoxes. it's mean number there and max number here
    private int Max = 1000000000;
    private Integer[][] dp;
    public int minSpaceWastedKResizing(int[] a, int k) {
        int n = a.length;
        dp = new Integer[n][n];
        return solve(a, 0, k);
    }

    private int solve(int[] a, int i, int k){

        int n = a.length;
        if(i==n){
            return 0;
        }
        if(k<0){
            return Max;
        }
        if(dp[i][k] != null){
            return dp[i][k];
        }
        int nmax = 0;
        int sum = 0;
        int res = Max;
        for(int j=i; j<n; j++){
            nmax = Math.max(a[j], nmax);
            sum += a[j];
            int after = nmax*(j-i+1);
            int cur = after-sum+ solve(a, j+1, k-1);
            res = Math.min(res, cur);
        }
        dp[i][k] =res;
        return res;
    }
}
