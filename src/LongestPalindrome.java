import java.util.HashMap;
import java.util.Map;

/*
LC#409
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        int[] c = new int[256];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)]++;
        }
        boolean odd = false;
        int r = 0;
        for (int i = 0; i < 256; i++) {
            if (c[i] % 2 == 0) {
                r += c[i];
            } else {
                odd = true;
                // odd can also contribute!
                r += c[i] - 1;
            }
        }
        return odd ? r + 1 : r;
    }
}
