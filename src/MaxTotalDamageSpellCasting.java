import java.util.Arrays;

public class MaxTotalDamageSpellCasting {
    public long maximumTotalDamage(int[] a) {
        int n = a.length;
        dp = new long[n];
        Arrays.fill(dp, -1L);
        npos = new int[n];
        Arrays.fill(npos, -2);
        Arrays.sort(a);
        return solve(a, 0);
    }

    private int[] npos;
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
        int j = -1;
        // first >= this num
        if(npos[i] != -2){
            j = npos[i];
        }else{
            j = binary(a, k, n-1, a[i]+3);
            npos[i] = j;
        }
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
