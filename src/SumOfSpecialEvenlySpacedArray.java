import java.util.HashSet;
import java.util.Set;

public class SumOfSpecialEvenlySpacedArray {
    // step related think about sqrt because n/y == sqrtn after we preprocess <=sqrt and deal with >sqrt directly
    private int cutoff = (int)Math.sqrt(50000)+1;
    private int mod = 1000000007;

    public int[] solve(int[] a, int[][] qs) {
        int n = a.length;
        int maxj = 0;
        for(int i=0; i<qs.length;i++){
            maxj = Math.max(maxj, qs[i][1]);
        }
        // from i...n-1, step len j
        long [][] dp = new long [n][cutoff];
        for(int i=n-1; i>=0; i--){
            for(int j=1; j<cutoff;j++){
                if(i+j < n){
                    dp[i][j] = dp[i+j][j]+ a[i];
                    dp[i][j] %= mod;
                }else{
                    dp[i][j] = a[i];
                }
            }
        }
        int[] res = new int[qs.length];
        for(int ri = 0; ri<qs.length; ri++){
            int[] q = qs[ri];
            int i = q[0];
            int j = q[1];
            if(j<cutoff){
                res[ri] = (int)(dp[i][j]);
            }else{
                long sum = 0;
                for(int k=i; k<n; k+= j){
                    sum += a[k];
                    sum %= mod;
                }
                res[ri] = (int)sum;
            }
        }
        return res;
    }
}
