public class NumberOfWaysReachDestinationInGrid {
    public int numberOfWays(int n, int m, int k, int[] s, int[] t) {
        int q1 = 0;
        if (s[0] == t[0]) {
            q1 = 1;
        }
        int q2 = 0;
        if (s[1] == t[1]) {
            q2 = 1;
        }
        dp = new Long[2][2][k + 1];
        return (int) solve(q1, q2, n, m, k);
    }

    private long Mod = (long) (1e9 + 7);
    private Long[][][] dp;

    private long solve(int q1, int q2, int n, int m, int k) {
        if (k == 0) {
            return q1 == 1 && q2 == 1 ? 1 : 0;
        }
        if (dp[q1][q2][k] != null) {
            return dp[q1][q2][k];
        }
        long way1 = 0;
        long way2 = 0;
        if (q1 == 1) {
            way1 = (n - 1) * solve(0, q2, n, m, k - 1);
        } else {
            way1 = solve(1, q2, n, m, k - 1) + (n - 2) * solve(0, q2, n, m, k - 1);
        }
        if (q2 == 1) {
            way2 = (m - 1) * solve(q1, 0, n, m, k - 1);
        } else {
            way2 = solve(q1, 1, n, m, k - 1) + (m - 2) * solve(q1, 0, n, m, k - 1);
        }
        long res = way1 + way2;
        res %= Mod;
        dp[q1][q2][k] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfWaysReachDestinationInGrid().numberOfWays(3,2,2, new int[]{1, 1}, new int[]{2, 2}));
    }
}
