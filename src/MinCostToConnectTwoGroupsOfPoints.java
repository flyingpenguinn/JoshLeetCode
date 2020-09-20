import java.util.List;

public class MinCostToConnectTwoGroupsOfPoints {
    // iterate on group 1 with the status kept for group2
    // once we are done with group 1, remaining points in 2 will be connected to 1 with the smallest cost ever possible
    private Integer[][] dp;

    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size();
        int n = cost.get(0).size();
        dp = new Integer[m][1 << n];
        return domin(0, 0, cost);
    }

    private int Max = 10000000;

    private int domin(int i, int st, List<List<Integer>> c) {
        //    System.out.println(i+" "+st);
        int m = c.size();
        int n = c.get(0).size();
        if (i == m) {
            if (st != ((1 << n) - 1)) {
                int res = 0;
                for (int j = 0; j < n; j++) {
                    if (((st >> j) & 1) == 0) {

                        int min = Max;
                        for (int k = 0; k < m; k++) {
                            min = Math.min(min, c.get(k).get(j));
                        }
                        res += min;
                    }
                }
                return res;
            } else {
                return 0;
            }
        }
        if (dp[i][st] != null) {
            return dp[i][st];
        }
        int min = Max;
        for (int j = 0; j < n; j++) {
            int nst = st | (1 << j);
            int cur = c.get(i).get(j) + domin(i + 1, nst, c);
            min = Math.min(min, cur);
        }
        dp[i][st] = min;
        return min;
    }
}
