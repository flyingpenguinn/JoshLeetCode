public class CountGoodIntegersInRange {
    public long goodIntegers(long l, long r, int k) {
        return solve(r, k) - solve(l - 1, k);
    }

    private Long[][][] dp;

    private long solve(long num, int k) {
        char[] cnum = String.valueOf(num).toCharArray();

        int n = cnum.length;
        dp = new Long[n][2][11];
        return dodp(cnum, 0, 0, 10, k);
    }

    private long dodp(char[] t, int i, int small, int pre, int k) {
        int n = t.length;
        if (i == n) {
            return 1;
        }
        if (dp[i][small][pre] != null) {
            return dp[i][small][pre];
        }
        int ti = t[i] - '0';
        long res = 0;
        for (int dig = 0; dig <= 9; ++dig) {
            if (pre != 10) {
                if (Math.abs(dig - pre) > k) {
                    continue;
                }
            }
            if (small == 0 && dig > ti) {
                continue;
            }
            int nsmall = small;
            if (small == 0 && dig < ti) {
                nsmall = 1;
            }
            int npre = dig;
            if (pre == 10 && dig == 0) {
                npre = pre;
            }
            long later = dodp(t, i + 1, nsmall, npre, k);
            res += later;
        }
        dp[i][small][pre] = res;
        return res;
    }
}
