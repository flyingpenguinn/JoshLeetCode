public class StoneGameIv {
    private int[] dp;

    public boolean winnerSquareGame(int n) {
        dp = new int[n + 1];
        return canWin(n) == 1;
    }

    private int canWin(int n) {
        if (n == 0) {
            return 2;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        for (int i = 1; i * i <= n; i++) {
            int later = canWin(n - i * i);
            if (later == 2) {
                dp[n] = 1;
                return 1;
            }
        }
        dp[n] = 2;
        return 2;
    }
}
