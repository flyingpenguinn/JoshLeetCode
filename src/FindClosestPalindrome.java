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
    public String nearestPalindromic(String s) {

        long[] r = gen(s);
        return closest(r, s);

    }

    private String closest(long[] r, String s) {
        long sn = Long.valueOf(s);
        long mindiff = Long.MAX_VALUE;
        long minnum = 0;
        for (long ri : r) {
            long diff = Math.abs(ri - sn);
            if (diff == 0) {
                continue;
            }
            if (diff < mindiff) {
                mindiff = diff;
                minnum = ri;
            } else if (diff == mindiff && ri < minnum) {
                minnum = ri;
            }
        }
        return String.valueOf(minnum);
    }

    private long[] gen(String s) {
        int n = s.length();
        long m1 = 0;
        long m2 = 0;
        long m3 = 0;
        if (n % 2 == 1) {
            String h1 = s.substring(0, n / 2);
            int middle = s.charAt(n / 2) - '0';
            m1 = Long.valueOf(h1 + middle + reverses(h1));
            m2 = middle == 0 ? m1 : Long.valueOf(h1 + (middle - 1) + reverses(h1));
            m3 = middle == 9 ? m1 : Long.valueOf(h1 + (middle + 1) + reverses(h1));
        } else {
            String h1 = s.substring(0, n / 2);
            int middle = s.charAt(n / 2 - 1) - '0';
            String hm1 = s.substring(0, n / 2 - 1);
            m1 = Long.valueOf(h1 + reverses(h1));
            m2 = middle == 0 ? m1 : Long.valueOf(hm1 + (middle - 1) + reverses(hm1 + (middle - 1)));
            m3 = middle == 9 ? m1 : Long.valueOf(hm1 + (middle + 1) + reverses(hm1 + (middle + 1)));
        }
        StringBuilder nine = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            nine.append("9");
        }
        long longnine = nine.length() == 0 ? m1 : Long.valueOf(nine.toString());
        StringBuilder one = new StringBuilder("1");
        for (int i = 0; i < n - 1; i++) {
            one.append("0");
        }
        one.append("1");
        long longone = Long.valueOf(one.toString());
        return new long[]{m1, m2, m3, longnine, longone};
    }

    private String reverses(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new FindClosestPalindrome().nearestPalindromic("999999999999999999"));
    }
}
