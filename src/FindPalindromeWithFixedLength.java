import base.ArrayUtils;

import java.util.Arrays;

public class FindPalindromeWithFixedLength {
    private double[] pows = new double[]{1e0, 1e1, 1e2, 1e3, 1e4, 1e5, 1e6, 1e7, 1e8, 1e9};

    private long pow10(int i) {
        return (long) pows[i];
    }

    public long[] kthPalindrome(int[] queries, int intLength) {
        int n = queries.length;
        long[] res = new long[n];
        for (int i = 0; i < n; ++i) {
            res[i] = solve(queries[i], 0, intLength, "");
        }
        return res;
    }

    private long tonumber(String s, int len) {
        String rev = null;
        if (len % 2 == 0) {
            rev = new StringBuilder(s).reverse().toString();
        } else {
            rev = new StringBuilder(s.substring(0, s.length() - 1)).reverse().toString();
        }
        return Long.valueOf(s + rev);
    }

    private long solve(long n, int i, int len, String cur) {
        int t = (len + 1) / 2;
        if (i == t) {
            return n == 1 ? tonumber(cur, len) : -1;
        }
        int count = (t - i);
        int start = i == 0 ? 1 : 0;
        for (int j = start; j <= 9; ++j) {
            long rem = pow10(count - 1);
            if (rem >= n) {
                return solve(n, i + 1, len, cur + j);
            } else {
                n -= rem;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindPalindromeWithFixedLength().kthPalindrome(ArrayUtils.read1d("[90]"), 3)));
    }
}
