import base.ArrayUtils;
import static java.lang.Math.*;
import java.util.Arrays;

public class MakeArrayNonDecreasingOrIncreasing {

    public int convertArray(int[] a) {
        int n = a.length;
        return min(solve(a, 0, n, 1), solve(a, n-1, -1, -1));
    }

    private int solve(int[] a, int start, int end, int delta){
        int n = a.length;
        int max = 0;
        for(int i=0; i<n; ++i){
            max = max(a[i], max);
        }
        int[][] dp = new int[n][max+1];
        for(int j=0; j<=max; ++j){
            dp[start][j] = abs(a[start]-j);
        }

        for(int i=start+delta; i!= end; i+=delta){
            int curmin = Integer.MAX_VALUE; // assuming delta = -1, this takes rolling min from dp[i-1][j]
            for(int j=0; j<=max; ++j){
                curmin = Math.min(curmin, dp[i-delta][j]); // this is now min from dp[i-1][0] all the way to j
                dp[i][j] = curmin + abs(a[i]-j);
            }
        }
        int res = Integer.MAX_VALUE;
        for(int j=0; j<=max; ++j){
            res = Math.min(res, dp[end-delta][j]);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new MakeArrayNonDecreasingOrIncreasing().convertArray(ArrayUtils.read1d("9,0")));
    }
}
