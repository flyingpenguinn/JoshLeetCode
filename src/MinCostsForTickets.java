import java.util.Arrays;

public class MinCostsForTickets {
    private int[] dp;

    public int mincostTickets(int[] days, int[] costs) {
        dp = new int[days.length];
        Arrays.fill(dp, -1);
        return domin(0, days, costs);
    }

    private int domin(int i, int[] days, int[] costs) {
        if (i == days.length) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int way0 = domin(i + 1, days, costs) + costs[0];
        // can use binary search here too
        int e1 = i;
        while (e1 < days.length && days[e1] <= days[i] + 6) {
            e1++;
        }
        int way1 = domin(e1, days, costs) + costs[1];
        int e2 = i;
        while (e2 < days.length && days[e2] <= days[i] + 29) {
            e2++;
        }
        int way2 = domin(e2, days, costs) + costs[2];
        dp[i] = Math.min(way0, Math.min(way1, way2));
        return dp[i];
    }
}
