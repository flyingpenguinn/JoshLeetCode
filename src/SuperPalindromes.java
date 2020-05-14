/*
LC#906
Let's say a positive integer is a superpalindrome if it is a palindrome, and it is also the square of a palindrome.

Now, given two positive integers L and R (represented as strings), return the number of superpalindromes in the inclusive range [L, R].



Example 1:

Input: L = "4", R = "1000"
Output: 4
Explanation: 4, 9, 121, and 484 are superpalindromes.
Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.


Note:

1 <= len(L) <= 18
1 <= len(R) <= 18
L and R are strings representing integers in the range [1, 10^18).
int(L) <= int(R)
 */
public class SuperPalindromes {

    // concact numbers to form palindromes, then check their squares to see if they are palindromes
    public int superpalindromesInRange(String l, String r) {
        int count = 0;
        long ll = Long.valueOf(l);
        long lr = Long.valueOf(r);
        // sqrt(10^19) = 3,162,277,660
        // so we will concact numbers up to this one. the biggest is the first half of it 31622
        int sqrtlr = (int) Math.sqrt(lr);
        String strlimit = String.valueOf(sqrtlr);
        int limit = Integer.valueOf(strlimit.substring(0, strlimit.length() / 2 + 1));

        for (int num = 1; num <= limit; num++) {
            String cur = String.valueOf(num);
            String reverse = new StringBuilder(cur).reverse().toString();
            String palin1 = cur + reverse; // 12 -> 1221
            String palin2 = cur + reverse.substring(1); // 12 -> 121
            long p1 = Long.valueOf(palin1);
            long p1sqaure = p1 * p1;
            if (isGood(ll, lr, p1sqaure)) {
                //   System.out.println(p1sqaure);
                count++;
            }
            long p2 = Long.valueOf(palin2);
            long p2square = p2 * p2;
            if (isGood(ll, lr, p2square)) {
                //      System.out.println(p2square);
                count++;
            }
        }
        return count;
    }

    private boolean isGood(long ll, long lr, long p1sqaure) {
        return p1sqaure >= ll && p1sqaure <= lr && isPalin(String.valueOf(p1sqaure));
    }

    private boolean isPalin(String s) {
        int l = 0;
        int u = s.length() - 1;
        while (l < u) {
            if (s.charAt(l++) != s.charAt(u--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new SuperPalindromes().superpalindromesInRange("1", "999999999999999999"));
    }
}
