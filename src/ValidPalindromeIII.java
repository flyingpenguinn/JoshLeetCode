/*

LC#1216
Given a string s and an integer k, find out if the given string is a K-Palindrome or not.

A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.



Example 1:

Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.


Constraints:

1 <= s.length <= 1000
s has only lowercase English letters.
1 <= k <= s.length
 */

public class ValidPalindromeIII {

    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        // i...j needs to delete how many to become palin
        int[][] dp = new int[s.length()][s.length()];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = 0;
                } else {
                    char ci = s.charAt(i);
                    char cj = s.charAt(j);
                    if (len == 2) {
                        dp[i][j] = (ci == cj) ? 0 : 1;
                    } else {
                        if (ci == cj) {
                            dp[i][j] = dp[i + 1][j - 1];
                        } else {
                            dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                        }
                    }
                }
            }
        }
        return dp[0][s.length() - 1] <= k;
    }
}
