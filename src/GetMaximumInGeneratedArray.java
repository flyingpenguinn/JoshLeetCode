public class GetMaximumInGeneratedArray {
    public int getMaximumGenerated(int n) {
        int[] dp = new int[n+1];
        int res = 0;
        for(int i=0; i<=n; i++){
            if(i<=1){ dp[i] = i; }
            else if(i%2==0){ dp[i] = dp[i/2]; }
            else{ dp[i] = dp[i/2] + dp[(i+1)/2]; }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
