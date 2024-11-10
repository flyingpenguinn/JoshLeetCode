import java.util.Arrays;

public class CountKReducibleNumbersLessThanN {
    // digital dp!
    private int[] reducible;
    private int limit = 900;
    private long Mod = (long) (1e9 + 7);
    private long[][][] dp;

    public int countKReducibleNumbers(String s, int k) {
        char[] c = s.toCharArray();
        int n = c.length;
        dp = new long[n][2][n + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 2; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        reducible = new int[limit + 10];
        reducible[1] = 1;
        for (int i = 2; i <= limit; ++i) {
            int cur = i;
            int ck = k - 1;
            while (cur > 1 && ck > 0) {
                int count = Integer.bitCount(cur);
                cur = count;
                --ck;
            }
            if (cur == 1) {
                reducible[i] = 1;
            }
        }

        long rt = solve(c, 0, 0, 0);
        return (int) rt;
    }

    private long solve(char[] c, int i, int sm, int bits) {
        int n = c.length;
        if (i == n) {
            return sm == 0 ? 0 : reducible[bits];
        }
        if (dp[i][sm][bits] != -1) {
            return dp[i][sm][bits];
        }
        int nsm = sm;
        if (sm == 0 && c[i] == '1') {
            nsm = 1;
        }
        long way1 = solve(c, i + 1, nsm, bits);
        long way2 = 0;
        if (sm == 0 && c[i] == '1') {
            way2 = solve(c, i + 1, sm, bits + 1);
        } else if (sm == 1) {
            way2 = solve(c, i + 1, sm, bits + 1);
        }
        long res = way1 + way2;
        res %= Mod;
        dp[i][sm][bits] = res;
        return res;
    }
}
