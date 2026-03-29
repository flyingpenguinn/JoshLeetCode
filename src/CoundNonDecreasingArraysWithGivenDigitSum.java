public class CoundNonDecreasingArraysWithGivenDigitSum {
    private long Mod = (long) (1e9 + 7);
    private static int[] g;
    public int countArrays(int[] d) {
        int n = d.length;
        int Max = 5001;
        if(g==null) {
            g = new int[Max + 1];
            for (int j = 0; j < Max; ++j) {
                g[j] = getdigsum(j);
            }
        }
        long[][] dp = new long[n + 1][Max+1];
        for (int j = 0; j < Max; ++j) {
            dp[n][j] = 1;
        }
        for (int i = n - 1; i >= 0; --i) {
            long suf = 0;
            for (int j = Max; j >= 0; --j) {
                if (d[i] == g[j]) {
                    suf += dp[i + 1][j];
                    suf %= Mod;
                }
                dp[i][j] = suf;
            }
        }
        return (int) dp[0][0];
    }

    private static int getdigsum(int n) {
        int res = 0;
        while (n > 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }
}
