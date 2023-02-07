import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MaxWinFromTwoSegments {
    public int maximizeWin(int[] a, int k) {
        int n = a.length;
        int[] dp = new int[n]; // max value for one segment
        int j = 0;
        int res = 0;
        for(int i=0; i<n; ++i){
            while(j<=i && a[i]-a[j] > k){
                ++j;
            }
            int cur = i-j+1;
            res = Math.max(res, cur + (j==0?0:dp[j-1]));
            dp[i] = Math.max( (i==0?0:dp[i-1]), cur);
        }
        return res;
    }
}
