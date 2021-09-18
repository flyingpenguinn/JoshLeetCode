import java.util.Arrays;

public class MaxEarningsFromTaxi {
    private Long[] dp;
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (x, y)-> Integer.compare(x[0], y[0]));
        dp = new Long[rides.length];
        return solve(0, n, rides);
    }

    private long solve(int i, int n, int[][] rides){
        if(i==rides.length){
            return 0;
        }
        if(dp[i] != null){
            return dp[i];
        }
        long nopick = solve(i+1, n, rides);
        int laterindex = binarysearch(rides, rides[i][1]);
        long profit = rides[i][1]- rides[i][0]+rides[i][2];
        long pick = profit +solve(laterindex, n, rides);
        long rt = Math.max(pick, nopick);
        dp[i] = rt;
        return rt;
    }

    private int binarysearch(int[][] a, int t){
        int l = 0;
        int u = a.length-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(a[mid][0]>=t){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l;
    }
}
