import java.util.ArrayList;
import java.util.List;

/*
LC#564
Given an integer n, find the closest integer (not including itself), which is a palindrome.

The 'closest' is defined as absolute difference minimized between two integers.

Example 1:
Input: "123"
Output: "121"
Note:
The input n is a positive integer represented by string, whose length will not exceed 18.
If there is a tie, return the smaller one as answer.
 */
public class FindClosestPalindrome {
    // +1, -1, or no change to middle number
    // also 9999, 10001 like numbers, find the best
    // beware of overlfow and -1 representation
    public String nearestPalindromic(String num) {
        int n = num.length();
        // 1234 -> 12, 123 -> 12
        int llen = (n % 2 == 0) ? n / 2 : n / 2 + 1;
        String left = num.substring(0, llen);
        long lnum = Long.parseLong(left);
        List<Long> checks = new ArrayList<>();
        checks.add(palin(lnum, n));
        checks.add(palin(lnum + 1, n));
        checks.add(palin(lnum - 1, n));
        checks.add((long) Math.pow(10, n) + 1);
        checks.add((long) Math.pow(10, n - 1) - 1);

        long realnum = Long.parseLong(num);
        long mindiff = Long.MAX_VALUE;
        long res = Long.MAX_VALUE;

        for (long ci : checks) {
            long diff = Math.abs(ci - realnum);
            if (diff != 0 && diff < mindiff) {
                mindiff = diff;
                res = ci;
            } else if (diff == mindiff && ci < res) {
                res = ci;
            }
        }
        return Long.toString(res);
    }

    private long palin(long left, int n) {
        String lstring = Long.toString(left);
        StringBuilder res = new StringBuilder(String.join("", "0".repeat(n)));
        for (int i = 0; i < lstring.length(); i++) {
            res.setCharAt(i, lstring.charAt(i));
        }
        int i = 0;
        int j = n - 1;
        while (i <= j) {
            res.setCharAt(j--, res.charAt(i++));
        }
        return Long.parseLong(res.toString());
    }


    public static void main(String[] args) {
        System.out.println(new FindClosestPalindrome().nearestPalindromic("999999999999999999"));
    }
}
