/*
LC#9
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
Follow up:

Coud you solve it without converting the integer to a string?
 */

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        long rv = 0;
        long ox = x;
        while (x > 0) {
            rv = rv * 10 + (x % 10);
            x /= 10;
        }
        return rv == ox;
    }

    public static void main(String[] args) {
        //  System.out.println(new PalindromeNumberHalf().isPalindrome(121));
        // System.out.println(new PalindromeNumberHalf().isPalindrome(1221));
        System.out.println(new PalindromeNumberHalf().isPalindrome(11));
    }
}

class PalindromeNumberHalf {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        // handle special case about 0 here!. any non 0 thing can't end with 0 because that means it starts with 0
        if (x % 10 == 0) {
            return false;
        }
        long rv = 0;
        while (x > rv) {
            rv = rv * 10 + (x % 10);
            x /= 10;
        }
        // x<= rv weirdo cases like 0 or 21210 excluded above already
        return x == rv || x == rv / 10;
    }


}
