import java.util.List;

public class LengthOfLongestSubseqSumToTarget {
    public int lengthOfLongestSubsequence(List<Integer> a, int t) {
        dp = new Integer[a.size()][t + 1];
        int rt = solve(a, 0, t);
        return rt < 0 ? -1 : rt;
    }

    private Integer[][] dp;

    private int solve(List<Integer> a, int i, int t) {
        if (t < 0) {
            return Integer.MIN_VALUE;
        }
        int n = a.size();
        if (i == n) {
            return t == 0 ? 0 : Integer.MIN_VALUE;
        }
        if (dp[i][t] != null) {
            return dp[i][t];
        }
        int way1 = solve(a, i + 1, t);
        int way2 = 1 + solve(a, i + 1, t - a.get(i));
        int res = Math.max(way1, way2);
        dp[i][t] = res;
        return res;
    }
}
