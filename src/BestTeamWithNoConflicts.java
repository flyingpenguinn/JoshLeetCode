import java.util.Arrays;

public class BestTeamWithNoConflicts {
    // not a heap problem... not a binary search.. not a graph....once we sort by age, it is longest increasing subsequence!
    public int bestTeamScore(int[] s, int[] a) {
        int n = s.length;
        int[][] ps = new int[n][2];
        for (int i = 0; i < n; i++) {
            ps[i][0] = a[i];
            ps[i][1] = s[i];
        }
        Arrays.sort(ps, (x, y) -> x[0] != y[0] ? Integer.compare(x[0], y[0]) : Integer.compare(x[1], y[1]));
        // younger age first. for big age, we can pick any person score <= current person, that person is guaranteed to be younger
        int max = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int score = ps[i][1];
            dp[i] = score;
            for (int j = i - 1; j >= 0; j--) {
                if (ps[j][1] <= score) {
                    dp[i] = Math.max(dp[i], score + dp[j]);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
