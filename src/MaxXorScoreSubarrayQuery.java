import base.ArrayUtils;

import java.util.Arrays;

public class MaxXorScoreSubarrayQuery {
    public int[] maximumSubarrayXor(int[] a, int[][] qs) {
        int n = a.length;

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = a[i];
        }
        for(int len = 2; len<=n; ++len){
            for(int i=0; i<n && i+len-1<n; ++i){
                int j = i+len-1;
                dp[i][j] = dp[i][j-1] ^ dp[i+1][j];
            }
        }
        for(int len = 1; len<=n; ++len){
            for(int i=0; i<n && i+len-1<n; ++i){
                int j = i+len-1;
                dp[i][j] = Math.max(dp[i][j], Math.max((j==0?0:dp[i][j-1]), (i+1==n? 0: dp[i+1][j])));
            }
        }
        int[] res = new int[qs.length];
        for(int i = 0; i<qs.length; ++i){
            int[] q = qs[i];
            int l = q[0];
            int r = q[1];
            res[i] = dp[l][r];
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MaxXorScoreSubarrayQuery().maximumSubarrayXor(ArrayUtils.read1d("[2,8,4,32,16,1]"), ArrayUtils.read("[[0,2],[1,4],[0,5]]"))));
    }
}
