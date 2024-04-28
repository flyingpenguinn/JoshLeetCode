import java.util.Arrays;

public class FindAllPossibleStableArraysI {
    private long Mod = (long) (1e9 + 7);
    private long[][][][] dp;

    public int numberOfStableArrays(int zs, int os, int limit) {
        dp = new long[zs + 1][os + 1][3][limit + 1];
        for (int i = 0; i <= zs; ++i) {
            for (int j = 0; j <= os; ++j) {
                for (int k = 0; k < 3; ++k) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        return (int) solve(zs, os, 2, 0, limit);
    }

    private long solve(int zs, int os, int last, int run, int limit) {
        if (zs == 0 && os == 0) {
            return 1L;
        }
        if (dp[zs][os][last][run] != -1) {
            return dp[zs][os][last][run];
        }
        long way1 = 0;
        if (zs > 0 && (last != 0 || (last == 0 && run + 1 <= limit))) {
            int nrun = last == 0 ? run + 1 : 1;
            way1 = solve(zs - 1, os, 0, nrun, limit);
        }
        long way2 = 0;
        if (os > 0 && (last != 1 || (last == 1 && run + 1 <= limit))) {
            int nrun = last == 1 ? run + 1 : 1;
            way2 = solve(zs, os - 1, 1, nrun, limit);
        }
        long res = way1 + way2;
        res %= Mod;
        dp[zs][os][last][run] = res;
        return res;
    }
}
