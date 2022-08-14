public class CountSpecialIntegers {
    // same as "Numbers With Repeated Digits".
    private Integer[][][] dp;

    public int countSpecialNumbers(int num) {
        String str = String.valueOf(num);
        char[] sc = str.toCharArray();
        int n = sc.length;
        dp = new Integer[n][1 << 10][2];
        return solve(sc, 0, 0, 0);
    }

    private int solve(char[] sc, int i, int st, int sm) {
        int n = sc.length;
        if (i == n) {
            return st == 0 ? 0 : 1;
        }
        if (dp[i][st][sm] != null) {
            return dp[i][st][sm];
        }
        int res = st == 0 ? solve(sc, i + 1, st, 1) : 0;
        // note with the above solution, we do not need to iterate on the length of the number
        int start = st == 0 ? 1 : 0;
        if (sm == 1) {
            for (int j = start; j <= 9; ++j) {
                if (((st >> j) & 1) == 0) {
                    int nst = st | (1 << j);
                    res += solve(sc, i + 1, nst, 1);
                }
            }
        } else {
            int end = sc[i] - '0';
            for (int j = start; j <= end; ++j) {
                if (((st >> j) & 1) == 0) {
                    int nst = st | (1 << j);
                    int nsm = (j < end) ? 1 : 0;
                    res += solve(sc, i + 1, nst, nsm);
                }
            }
        }
        dp[i][st][sm] = res;
        return res;
    }
}
