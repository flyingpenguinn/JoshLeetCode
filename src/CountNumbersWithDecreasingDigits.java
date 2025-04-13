import java.math.BigInteger;
import java.util.Arrays;

public class CountNumbersWithDecreasingDigits {
    private long Mod = (long) (1e9 + 7);

    public int countNumbers(String l, String r, int b) {
        long rt = solve(r, b) - solve(minusone(l), b);
        rt %= Mod;
        if (rt < 0) {
            rt += Mod;
        }
        return (int) rt;
    }

    private long[][][] dp;

    private long solve(String num, int b) {
        if (num.equals("-1")) {
            return 0;
        }
        BigInteger base10 = new BigInteger(num, 10);

        String converted = base10.toString(b);
        int n = converted.length();
        dp = new long[n][10][2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 10; ++j) {

                Arrays.fill(dp[i][j], -1);

            }
        }
        long rt = dodp(0, 0, 1, converted, b);
        return rt;
    }

    private long dodp(int i, int prev, int tight, String t, int base) {
        int n = t.length();
        if (i == n) {
            return 1;
        }
        if (dp[i][prev][tight] != -1) {
            return dp[i][prev][tight];
        }
        int tv = t.charAt(i) - '0';
        int start = prev;
        int end = tight == 1 ? tv : (base - 1);
        long res = 0;
        for (int k = start; k <= end; ++k) {
            int ntight = (tight == 1 && k == tv) ? 1 : 0;

            long later = dodp(i + 1, k, ntight, t, base);
            res += later;
            res %= Mod;
        }
        dp[i][prev][tight] = res;
        return res;
    }

    private String minusone(String l) {
        if (l.equals("0")) {
            return "-1";
        }
        int ln = l.length();
        char[] res = new char[ln];
        int carry = 1;
        for (int i = ln - 1; i >= 0; --i) {
            int v = l.charAt(i) - '0';
            v -= carry;
            carry = 0;
            if (v < 0) {
                v += 10;
                carry = 1;
            }
            res[i] = (char) ('0' + v);
        }
        return new String(res);
    }
}
