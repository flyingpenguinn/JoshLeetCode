public class MinDeletionCostToAvoidRepetition {
    // dont need to dp! we must delete consecutive appearances,
    // the best deletion is to pick so that the remaining one has the max cost
    public int minCost(String s, int[] cost) {
        int n = s.length();
        int i = 0;
        int res = 0;
        while (i < n) {
            int maxCost = cost[i];
            int sumCost = cost[i];
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                maxCost = Math.max(maxCost, cost[j]);
                sumCost += cost[j];
                j++;
            }
            res += sumCost - maxCost;
            i = j;
        }
        return res;
    }
}

class MinCostToRemoveDp {
    // O(26n) but space is a waste...
    private Integer[][] dp;

    public int minCost(String s, int[] cost) {
        dp = new Integer[s.length()][27];
        return domin(0, s, 26, cost);
    }

    private int domin(int i, String s, int last, int[] cost) {
        int n = s.length();
        if (i == n) {
            return 0;
        }
        if (dp[i][last] != null) {
            return dp[i][last];
        }
        char c = s.charAt(i);
        int cind = c - 'a';
        int min = domin(i + 1, s, last, cost) + cost[i];
        if (cind != last) {
            int keep = domin(i + 1, s, cind, cost);
            min = Math.min(min, keep);
        }
        dp[i][last] = min;
        return min;
    }
}