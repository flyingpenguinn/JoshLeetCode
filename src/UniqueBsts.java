public class UniqueBsts {
    int[] dp;

    public int numTrees(int n) {
        dp = new int[n + 1];
        return doTrees(n);
    }

    public int doTrees(int nt) {
        if (nt == 0) {
            return 1;
        }
        if (dp[nt] != 0) {
            return dp[nt];
        }
        int c = 0;
        for (int i = 1; i <= nt; i++) {
            c += doTrees(i - 1) * doTrees(nt - i);
        }
        dp[nt] = c;
        return c;
    }
}
