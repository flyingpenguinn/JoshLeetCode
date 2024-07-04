public class MaxArrayHoppingScoreI {
    // just jump to the max one on the right
    public int maxScore(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        int maxi = n-1;
        int res = 0;
        for(int i=n-2; i>=0; --i){
            int cur = (maxi-i)*a[maxi] + dp[maxi];
            res = Math.max(res, cur);
            dp[i] = cur;
            if(a[i]>a[maxi]){
                maxi = i;
            }
        }
        return res;
    }
}
