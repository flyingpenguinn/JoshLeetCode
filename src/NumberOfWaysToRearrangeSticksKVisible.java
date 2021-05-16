public class NumberOfWaysToRearrangeSticksKVisible {
    // if we do from forward we will have to remember max number so far
    // if we start from the back we dont need to remember the max number, it's visible anyway so baked into the formula
    private Long[][] dp;
    private int mod = 1000000007;
    public int rearrangeSticks(int n, int k) {
        dp = new Long[n+1][k+1];
        return (int) (solve(n, k));
    }

    private long solve(int n, int k){
        if(n==0){
            return k==0? 1: 0;
        }
        if(k<0){
            return 0;
        }
        if(dp[n][k] != null){
            return dp[n][k];
        }
        long way1 = solve(n-1, k-1);
        long way2 = (n-1)*solve(n-1, k);
        long rt = way1+way2;
        rt %= mod;
        dp[n][k] = rt;
        return rt;
    }
}
