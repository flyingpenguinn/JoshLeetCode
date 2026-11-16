public class FindMaxLenOfValidSubseqIII {
    public int maximumLength(int[] a, int k) {
        int n= a.length;
        int[] dp = new int[k];
        int res = 0;
        for(int i=0; i<n; ++i){
            int v = a[i]%k;
            int t = (k-v)%k;
            dp[v] = Math.max(dp[v], dp[t]+1);
            res = Math.max(res, dp[v]);
        }
        return res;
    }
}
