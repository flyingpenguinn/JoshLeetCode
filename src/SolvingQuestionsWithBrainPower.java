import java.util.Arrays;

public class SolvingQuestionsWithBrainPower {
    private long[] dp;

    public long mostPoints(int[][] a) {
        int n = a.length;
        dp = new long[n];
        Arrays.fill(dp, -1);
        return solve(a, 0);
    }

    private long solve(int[][] a, int i) {
        int n = a.length;
        if (i >= n) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        long way1 = 0L + a[i][0] + solve(a, i + a[i][1] + 1);
        long way2 = solve(a, i + 1);
        long res = Math.max(way1, way2);
        dp[i] = res;
        return res;
    }
}
