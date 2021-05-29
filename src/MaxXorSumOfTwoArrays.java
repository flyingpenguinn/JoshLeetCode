public class MaxXorSumOfTwoArrays {
    private int Max = 1000000000;

    public int minimumXORSum(int[] a, int[] b) {
        int n = a.length;
        Integer[][] dp = new Integer[n][(1<<n)];
        return solve(0, 0, a, b, dp);
    }

    private int solve(int i, int st, int[] a, int[] b, Integer[][] dp){
        int n = a.length;
        if(i==n){
            return 0;
        }
        if(dp[i][st] != null){
            return dp[i][st];
        }
        int res = Max;
        for(int j=0; j<n;j++){
            if(((st>>j)&1)==1){
                continue;
            }
            int nst = st | (1<<j);
            int cur = (a[i] ^ b[j])+solve(i+1, nst, a, b, dp);
            res = Math.min(res, cur);
        }
        dp[i][st] = res;
        return res;
    }
}
