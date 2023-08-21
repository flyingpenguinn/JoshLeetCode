import java.util.Collections;
import java.util.List;

public class MaximizeProfitAtSalesman {
    private Integer[] dp;

    public int maximizeTheProfit(int n, List<List<Integer>> a) {
        dp = new Integer[a.size() + 1];
        Collections.sort(a, (x, y) -> Integer.compare(x.get(0), y.get(0)));
        return solve(a, 0);
    }

    private int solve(List<List<Integer>> a, int i) {
        int an = a.size();
        if (i == an) {
            return 0;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        int way1 = solve(a, i + 1);
        int cur = a.get(i).get(2);
        int end = a.get(i).get(1);
        int pos = binary(a, end + 1, i + 1);
        //   System.out.println(i+" "+pos);
        int way2 = cur + solve(a, pos);
        int res = Math.max(way1, way2);
        dp[i] = res;
        return res;
    }

    private int binary(List<List<Integer>> a, int t, int l) {
        int n = a.size();
        int u = n - 1;

        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid).get(0) >= t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
