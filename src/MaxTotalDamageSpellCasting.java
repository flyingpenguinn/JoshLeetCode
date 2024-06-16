import java.util.Arrays;

public class MaxTotalDamageSpellCasting {
    // if written in bottom up way then we dont need the binary search can have a j as "catch up"
    public long maximumTotalDamage(int[] a) {
        int n = a.length;
        dp = new long[n];
        Arrays.fill(dp, -1L);
        Arrays.sort(a);
        return solve(a, 0);
    }

    private long[] dp;

    private long solve(int[] a, int i){
        int n = a.length;
        if(i==n){
            return 0L;
        }
        if(dp[i] != -1){
            return dp[i];
        }
        int k = i;
        while(k<n && a[k]==a[i]){
            ++k;
        }
        long len = k-i;
        long way1 = solve(a, k);
        long way2 = 0;
        int j = binary(a, k, n-1, a[i]+3);

        way2 = len*a[i] + solve(a, j);
        long res = Math.max(way1, way2);
        dp[i] = res;
        return res;
    }

    private int binary(int[] a, int l, int u, int t){
        while(l<=u){
            int mid = l+(u-l)/2;
            if(a[mid]>=t){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l;
    }
}
