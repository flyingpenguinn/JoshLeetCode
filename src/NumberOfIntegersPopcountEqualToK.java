public class NumberOfIntegersPopcountEqualToK {
    private long Max = (long) (2e15);

    public long popcountDepth(long n, int k) {
        long res = 0;
        if (k == 0) {
            return 1;
        }
        final String sv = Long.toBinaryString(n);
        int bitcount = sv.length();
        for (int i = 1; i <= bitcount; ++i) {
            int popcount = getpop(i);
            if (popcount == k - 1) {
                dp = new Long[bitcount][2][i + 1];
                long cur = getnumberswithsetbits(sv.toCharArray(), 0, bitcount, 0, i);
                if (i == 1) {
                    --cur;
                }
                res += cur;
            }
        }
        return res;
    }

    private Long[][][] dp;

    // how many numbers <=sc has t bits set
    private long getnumberswithsetbits(char[] sc, int i, int n, int small, int t) {
        if (t < 0) {
            return 0;
        }
        if (i == n) {
            return t == 0 ? 1L : 0;
        }
        if (dp[i][small][t] != null) {
            return dp[i][small][t];
        }
        int newsmall0 = sc[i] == '1' ? 1 : small;
        long way0 = getnumberswithsetbits(sc, i + 1, n, newsmall0, t);
        long way1 = 0;
        if (small == 1 || sc[i] == '1') {
            way1 = getnumberswithsetbits(sc, i + 1, n, small, t - 1);
        }
        long res = way0 + way1;
        dp[i][small][t] = res;
        return res;
    }

    private int getpop(int n) {
        int res = 0;
        while (n > 1) {
            n = Integer.bitCount(n);
            ++res;
        }
        return res;
    }
}
