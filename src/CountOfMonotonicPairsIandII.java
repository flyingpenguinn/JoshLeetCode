import java.util.Arrays;

public class CountOfMonotonicPairsIandII {
    private int lim = 1000;
    private int Max = 10000;
    private long Mod = (long)(1e9+7);
    private long[][] dp;

    public int countOfPairs(int[] a) {
        int n = a.length;
        dp = new long[n+1][lim+10];
        long[] dp1sum = new long[lim+10];
        for(int j=0; j<dp[0].length; ++j){
            dp[n][j] = 1;
            dp1sum[j] = (j==0?0:dp1sum[j-1]) + dp[n][j];
        }
        for(int i=n-1; i>=0; --i){
            long[] cursum = new long[lim+10];
            for(int pre=0; pre<=lim; ++pre){
                int preother = i==0? Max: a[i-1]-pre;
                int start = Math.max(pre, a[i]-preother);
                int end = a[i];
                if(start<=end){ dp[i][pre]= dp1sum[end] - (start==0?0:dp1sum[start-1]); }
                dp[i][pre] %= Mod;
                if(dp[i][pre]<0){ dp[i][pre] += Mod; }
                cursum[pre] = (pre==0?0:cursum[pre-1])+dp[i][pre];
                cursum[pre] %= Mod;
            }
            dp1sum = cursum;
        }
        return (int) dp[0][0];
    }

    public int countOfPairs2(int[] a) {
        int n = a.length;
        dp = new long[n][lim+10];
        for(int i=0; i<n; ++i){
            Arrays.fill(dp[i], -1);
        }
        long rt = solve(a, 0, 0);
        return (int)rt;
    }

    private long solve(int[] a, int i, int pre){
        int n = a.length;
        if(i==n){
            return 1L;
        }
        if(dp[i][pre] != -1){
            return dp[i][pre];
        }
        int preother = i==0? Max: a[i-1]-pre;
        long res = 0;
        int start = Math.max(pre, a[i]-preother);
        int end = a[i];
        for(int j=start;  j<=end; ++j){
            long later = solve(a, i+1, j);
            res += later;
            res %= Mod;
        }
        dp[i][pre] = res;
        return res;
    }
}
