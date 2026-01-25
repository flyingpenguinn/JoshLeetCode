import java.util.Arrays;

public class FindNthSmallestElementWithKOneBits {
    public long nthSmallest(long n, int k) {
        long l = 1;
        long u = (1L << 50);
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long cnum = calc(Long.toBinaryString(mid).toCharArray(), k);
            if (cnum >= n) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private long[][][] dp;

    private long calc(char[] t, int remones) {
        dp = new long[t.length][2][remones + 1];
        for (int i = 0; i < dp.length; ++i) {
            for (int j = 0; j < dp[i].length; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return solve(0, 0, t, remones);
    }

    private long solve(int i, int alreadysmall, char[] t, int remones) {
        int n = t.length;

        if (remones < 0) {
            return 0;
        }
        if (i == n) {
            return remones == 0 ? 1 : 0;
        }
        if (dp[i][alreadysmall][remones] != -1) {
            return dp[i][alreadysmall][remones];
        }
        int tind = t[i] - '0';

        int newalreadysmall0 = tind == 1 ? 1 : alreadysmall;
        long way0 = solve(i + 1, newalreadysmall0, t, remones);
        long way1 = 0;
        if (alreadysmall == 1 || tind == 1) {
            way1 = solve(i + 1, alreadysmall, t, remones - 1);
        }
        long res = way0 + way1;
        dp[i][alreadysmall][remones] = res;
        return res;

    }
}
