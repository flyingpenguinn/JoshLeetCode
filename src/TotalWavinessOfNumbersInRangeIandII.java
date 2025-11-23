public class TotalWavinessOfNumbersInRangeIandII {
    public long totalWaviness(long num1, long num2) {
        return total(num2) - total(num1 - 1);
    }

    private long total(long num) {
        String sn = String.valueOf(num);
        int n = sn.length();
        dp = new Long[n][2][2][11][11][n + 1];
        return solve(sn.toCharArray(), 0, 0, 0, 10, 10, 0);
    }

    private Long[][][][][][] dp;

    private long solve(char[] num, int i, int small, int started, int v1, int v2, int accu) {
        int n = num.length;
        if (i == n) {
            return accu;
        }
        if (dp[i][small][started][v1][v2][accu] != null) {
            return dp[i][small][started][v1][v2][accu];
        }
        int ncind = num[i] - '0';
        long res = 0;
        for (int dig = 0; dig <= 9; ++dig) {
            if (small == 0 && dig > ncind) {
                break;
            }
            int nsmall = small;
            if (dig < ncind) {
                nsmall = 1;
            }
            int nstarted = started;
            if (dig > 0) {
                nstarted = 1;
            }
            int cpos = 0;
            if (i >= 2 && v1 < 10 && v2 < 10 && v2 < v1 && v2 < dig) {
                cpos = 1;
            } else if (i >= 2 && v1 < 10 && v2 < 10 && v2 > v1 && v2 > dig) {
                cpos = 1;
            }
            int ndig = dig;
            if (nstarted == 0) {
                ndig = 10;
            }
            long cur = solve(num, i + 1, nsmall, nstarted, v2, ndig, accu + cpos);
            res += cur;
        }
        dp[i][small][started][v1][v2][accu] = res;
        return res;
    }
}
