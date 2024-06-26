import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
LC#902
We have a sorted set of digits D, a non-empty subset of {'1','2','3','4','5','6','7','8','9'}.  (Note that '0' is not included.)

Now, we write numbers using these digits, using each digit as many times as we want.  For example, if D = {'1','3','5'}, we may write numbers such as '13', '551', '1351315'.

Return the number of positive integers that can be written (using the digits of D) that are less than or equal to N.



Example 1:

Input: D = ["1","3","5","7"], N = 100
Output: 20
Explanation:
The 20 numbers that can be written are:
1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
Example 2:

Input: D = ["1","4","9"], N = 1000000000
Output: 29523
Explanation:
We can write 3 one digit numbers, 9 two digit numbers, 27 three digit numbers,
81 four digit numbers, 243 five digit numbers, 729 six digit numbers,
2187 seven digit numbers, 6561 eight digit numbers, and 19683 nine digit numbers.
In total, this is 29523 integers that can be written using the digits of D.


Note:

D is a subset of digits '1'-'9' in sorted order.
1 <= N <= 10^9
 */

public class NumbersAtmostNGivenDigitalSet {
    public int atMostNGivenDigitSet(String[] a, int n) {
        String sn = String.valueOf(n);
        int res = 0;
        for (int k = 1; k <= sn.length() - 1; k++) {
            res += (int) Math.pow(a.length, k);
        }
        for (int i = 0; i <= sn.length(); i++) {
            // invariant: 0.. i-1 are equal to sn. now checking i
            // the last one is the eq one
            if (i == sn.length()) {
                res++;
                break;
            }
            int digit = sn.charAt(i) - '0';
            int small = 0;
            int eqdigits = 0;
            for (int j = 0; j < a.length; j++) {
                if (Integer.valueOf(a[j]) < digit) {
                    small++;
                } else if (Integer.valueOf(a[j]) == digit) {
                    eqdigits = 1;
                }
            }
            int smaller = small * (int) Math.pow(a.length, sn.length() - 1 - i);
            res += smaller;
            if (eqdigits == 0) {
                break;
            }
        }
        return res;
    }
}


class NumbersAtmostNGivenDigitalSetDp {
    // typical digital dp
    private Integer[][] dp;

    public int atMostNGivenDigitSet(String[] a, int n) {
        String sn = String.valueOf(n);
        int res = 0;
        for (int k = 1; k <= sn.length() - 1; k++) {
            res += (int) Math.pow(a.length, k);
        }
        dp = new Integer[sn.length()][2];
        res += docount(a, 0, 0, sn.length(), sn);
        return res;
    }

    private int docount(String[] a, int i, int allowbigger, int k, String sn) {
        if (i == k) {
            return 1;
        }
        if (i > k) {
            return 0;
        }
        if (dp[i][allowbigger] != null) {
            return dp[i][allowbigger];
        }
        int res = 0;
        for (int j = 0; j < a.length; j++) {
            if (allowbigger == 1) {
                res += docount(a, i + a[j].length(), 1, k, sn);
            } else {
                String stub = sn.substring(i, Math.min(i + a[j].length(), sn.length()));
                int cmp = stub.compareTo(a[j]);
                if (cmp >= 0) {
                    res += docount(a, i + a[j].length(), cmp > 0 ? 1 : 0, k, sn);
                }
            }
        }
        dp[i][allowbigger] = res;
        return res;
    }
}
