/*
LC#246
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false
 */
public class StrobogrammaticNumber {
    // just a fancier palindrome with a special mapping
    public boolean isStrobogrammatic(String s) {
        int[] m = {0, 1, -2, -3, -4, -5, 9, -7, 8, 6};
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (m[s.charAt(i) - '0'] != (s.charAt(j) - '0') ||
                    m[s.charAt(j) - '0'] != (s.charAt(i) - '0')) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
