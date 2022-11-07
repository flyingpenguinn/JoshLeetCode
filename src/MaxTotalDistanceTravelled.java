import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxTotalDistanceTravelled {
    private long MAX = (long) 2e18;
    private Long[][] dp;

    public long minimumTotalDistance(List<Integer> a, int[][] f) {
        Collections.sort(a);
        Arrays.sort(f, (x, y) -> Integer.compare(x[0], y[0]));
        dp = new Long[a.size()][f.length];
        return solve(a, f, 0, 0);
    }

    private long solve(List<Integer> a, int[][] f, int i, int j) {
        int an = a.size();
        int fn = f.length;
        if (i == an) {
            return 0;
        }
        if (j == fn) {
            return MAX;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int cap = f[j][1];
        int spos = f[j][0];
        long res = solve(a, f, i, j + 1);
        long accu = 0;
        for (int k = i; k < an && k < i + cap; ++k) {
            accu += Math.abs(a.get(k) - spos);
            long cur = accu + solve(a, f, k + 1, j + 1);
            res = Math.min(res, cur);
        }
        dp[i][j] = res;
        return res;
    }
}
