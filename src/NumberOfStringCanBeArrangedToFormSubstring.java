public class NumberOfStringCanBeArrangedToFormSubstring {
    private long Mod = (long) (1e9 + 7);
    private Long[][][][] dp;

    public int stringCount(int n) {
        dp = new Long[n][2][3][2];
        return (int) solve(0, n, 1, 2, 1);
    }

    private long solve(int i, int n, int rl, int re, int rt) {
        if (i == n) {
            return rl == 0 && re == 0 && rt == 0 ? 1 : 0;
        }
        if (dp[i][rl][re][rt] != null) {
            return dp[i][rl][re][rt];
        }
        long way1 = 23 * solve(i + 1, n, rl, re, rt);
        long way2 = solve(i + 1, n, Math.max(rl - 1, 0), re, rt);
        long way3 = solve(i + 1, n, rl, Math.max(re - 1, 0), rt);
        long way4 = solve(i + 1, n, rl, re, Math.max(rt - 1, 0));
        long res = way1 + way2 + way3 + way4;

        res %= Mod;
        dp[i][rl][re][rt] = res;
        return res;
    }
}
