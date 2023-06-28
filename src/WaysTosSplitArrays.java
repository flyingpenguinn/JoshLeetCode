public class WaysTosSplitArrays {
    // sum of dp[last one] + dp[...] + dp[i-1]
    private int Mod = (int)(1e9+7);
    public int numberOfGoodSubarraySplits(int[] a) {
        int n = a.length;
        int[] lastone = new int [n];
        int last = -1;
        for(int i=0; i<n; ++i){
            lastone[i] = last;
            if(a[i]==1){
                last = i;
            }
        }
        long[] sum = new long[n];
        long[] dp = new long[n];
        for(int i=0; i<n; ++i){
            long cur = 0;
            if(a[i]==1){
                int j = lastone[i];
                cur = (i==0?0: sum[i-1]) - (j-1<0?0:sum[j-1]);
                if(cur==0){
                    cur = 1;
                }
                cur %= Mod;
                if(cur<0){
                    cur += Mod;
                }
            }else{
                cur = i==0?0: dp[i-1];
            }
            dp[i] = cur;
            sum[i] = (i==0?0: sum[i-1]) + cur;
        }
        return (int)dp[n-1];
    }
}
