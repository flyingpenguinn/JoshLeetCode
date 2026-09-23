public class CountEvenlyGoodIntegers {
    public long countEvenlyGoodIntegers(long l, long r) {
        return count(r) - count(l - 1);
    }

    private Long[][][][] dp;

    private long count(long num) {
        String str = String.valueOf(num);
        int n = str.length();

        dp = new Long[n][2][2][2];
        long res = solve(0, 0, 0, 0, str.toCharArray());
        return res;


    }

    private long solve(int i, int sm, int ecount, int picked, char[] str) {
        int n = str.length;
        long res = 0;
        if (i == n) {
            return picked == 1 && ecount % 2 == 0 ? 1 : 0;
        }
        if (dp[i][sm][ecount][picked] != null) {
            return dp[i][sm][ecount][picked];
        }
        int cind = str[i] - '0';
        int start = i == 0 ? 1 : 0;
        for (int d = 0; d <= 9; ++d) {
            if (sm == 0 && d > cind) {
                break;
            }
            int nsm = sm;
            if (d < cind) {
                nsm = 1;
            }
            int necount = ecount + (d % 2 == 0 ? 1 : 0);
            necount %= 2;
            int npicked = picked;
            if (d > 0) {
                npicked = 1;
            }
            if (npicked == 0) {
                necount = 0;
            }
            long cur = solve(i + 1, nsm, necount, npicked, str);
            res += cur;
        }
        dp[i][sm][ecount][picked] = res;
        return res;
    }
}
