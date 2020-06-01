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
    // typical digital dp

    int[][] dp;

    public int atMostNGivenDigitSet(String[] d, int n) {
        String sn = String.valueOf(n);
        int len = sn.length();
        dp = new int[len][2];
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], -1);
        }
        List<Integer> list = new ArrayList<>();
        for (String di : d) {
            list.add(Integer.valueOf(di));
        }
        int r = 0;
        for (int i = 1; i < len; i++) {
            r += (int) Math.pow(list.size(), i);
        }
        int rt = docount(0, 1, sn, list);
        return r + rt;
    }

    // st==0, <
    // st==1, ==
    private int docount(int i, int st, String sn, List<Integer> list) {
        int n = sn.length();
        if (i == n) {
            return 1;
        }
        if (dp[i][st] != -1) {
            return dp[i][st];
        }
        int sind = sn.charAt(i) - '0';
        int cur = 0;
        for (int j = 0; j < list.size(); j++) {
            int tind = list.get(j);
            if (sind > tind) {
                cur += docount(i + 1, 0, sn, list);
            } else if (sind == tind) {
                cur += docount(i + 1, st, sn, list);
            } else {
                if (st == 0) {
                    // if >, can only go through when previous parts are <
                    cur += docount(i + 1, 0, sn, list);
                }
            }
        }
        dp[i][st] = cur;
        return cur;
    }
}
