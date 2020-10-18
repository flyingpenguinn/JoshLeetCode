import java.util.Arrays;

public class BestTeamWithNoConflicts {
    // not a heap problem... more like longest increasing subsequence!
    public int bestTeamScore(int[] s, int[] a) {
        int n = s.length;
        int[][] ps = new int[n][2];
        for (int i = 0; i < n; i++) {
            ps[i][0] = s[i];
            ps[i][1] = a[i];
        }
        Arrays.sort(ps, (x, y) -> x[1] != y[1] ? Integer.compare(y[1], x[1]) : Integer.compare(y[0], x[0]));
        // big age first. also big salary first so that we dont need to worry about same age
        int max = 0;
        int[] dp = new int[n];
        // enumerate youngest
        for (int i = 0; i < n; i++) {
            int score = ps[i][0];
            int cmax = score;
            int limit = score;
            for (int j = i - 1; j >= 0; j--) {
                if (ps[j][0] >= limit) {
                    int cur = score + dp[j];
                    cmax = Math.max(cur, cmax);
                }
            }
            dp[i] = cmax;
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
