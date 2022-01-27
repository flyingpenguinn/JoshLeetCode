import java.util.ArrayList;
import java.util.List;

public class MinNumberOfLinesToCoverPoints {
    private Integer[] dp;
    public int minimumLines(int[][] a) {
        int n = a.length;
        dp = new Integer[(1 << n)];
        return solve(a, (1 << n) - 1);
    }

    private int solve(int[][] a, int st) {
        int n = a.length;
        if (st == 0) {
            return 0;
        }
        if (dp[st] != null) {
            return dp[st];
        }
        int res = n + 1;
        for (int i = st; i >= 1; i = ((i - 1) & st)) {
            if (sameLine(a, i)) {
                int cur = 1 + solve(a, st - i);
                res = Math.min(res, cur);
            }
        }
        dp[st] = res;
        return res;
    }

    private boolean sameLine(int[][] a, int st) {
        int n = a.length;
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (((st >> i) & 1) == 1) {
                points.add(a[i]);
            }
        }
        if (points.size() < 2) {
            return true;
        }
        if (points.get(0)[0] == points.get(1)[0]) {
            for (int i = 2; i < points.size(); ++i) {
                if (points.get(i)[0] != points.get(0)[0]) {
                    return false;
                }
            }
            return true;
        } else {
            double k = calcSlope(points.get(0), points.get(1));
            for (int i = 2; i < points.size(); ++i) {
                double ck = calcSlope(points.get(i - 1), points.get(i));
                if (ck != k) {
                    return false;
                }
            }
            return true;
        }
    }

    private double calcSlope(int[] p1, int[] p2) {
        return 1.0 * (p2[1] - p1[1]) / (p2[0] - p1[0]);
    }
}
