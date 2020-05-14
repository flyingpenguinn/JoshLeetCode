import java.util.Arrays;

public class ParittionArrayMaxSum {
    int[] dp;
    public int maxSumAfterPartitioning(int[] a, int k) {
        dp=new int[a.length];
        Arrays.fill(dp,-1);
        return dopa(a,k,0);
    }

    int dopa(int[] a, int k, int s){
        if(s==a.length){
            return 0;
        }
        if(dp[s]!=-1){
            return dp[s];
        }
        int localmax=-1;
        int max=-1;
        for(int i=0;i<k && s+i<a.length;i++){
            localmax= Math.max(localmax,a[s+i]);
            int cur=localmax*(i+1)+dopa(a,k,s+i+1);
            max= Math.max(cur,max);
        }
        dp[s]=max;
        return max;
    }
}
