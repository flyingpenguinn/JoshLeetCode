import java.util.Arrays;

public class MinCostsForTickets {
    int [] dp;
    public int mincostTickets(int[] days, int[] costs) {
        dp = new int [days.length];
        Arrays.fill(dp, -1);
        return doMinCost(0,days, costs);
    }

    int[] passes={1,7,30};

    int doMinCost(int index, int[]days, int[] cost){

        if(index== days.length){
            return 0;
        }
        if(dp[index] != -1){
            return dp[index];
        }
        int min= Integer.MAX_VALUE;

        for(int i= 0; i<passes.length; i++){
            int  p= passes[i];
            int cur = cost[i];
            int firstBigger = find(days, days[index]+p-1);
            int later= firstBigger==-1 ? 0 : doMinCost(firstBigger, days, cost);
            min = Math.min(min, cur+later);
        }
        dp[index] = min;
        return min;
    }


    // first that is strictly bigger
    int find(int[] days, int t){
        for(int i = 0; i < days.length; i++){
            if (t < days[i]){
                return i;
            }

        }
        return -1;

    }
}
